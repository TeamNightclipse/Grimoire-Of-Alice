package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public class EntityGap extends Entity {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
	private static final DataParameter<Byte> COLOR_DATA = EntityDataManager.createKey(EntityGap.class, DataSerializers.BYTE);
	private static final String COLOR = "color";

	private boolean teleportByColor = true;
	private int portalCooldown;
	private EntityPlayer player;
	private ItemStack stack;

	public EntityGap(World world) {
		super(world);
		isImmuneToFire = true;
	}

	public EntityGap(World world, EntityPlayer player, ItemStack stack) {
		super(world);
		setPositionAndAngles(player);
		this.player = player;
		this.stack = stack;
		isImmuneToFire = true;
	}

	public void setPositionAndAngles(EntityPlayer player) {
		Vec3d look = player.getLookVec();
		float distance = 2F;
		double dx = player.posX + look.x * distance;
		double dy = player.posY + look.y * distance + player.getEyeHeight();
		double dz = player.posZ + look.z * distance;
		setPosition(dx, dy, dz);
		setRotation(player.rotationYaw, player.rotationPitch);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote) {
			if(player != null) {
				if(player.isHandActive() && !player.isDead) {
					setPositionAndAngles(player);
				}
				else {
					player.getCooldownTracker().setCooldown(stack.getItem(), 25);
					reduceStack(player, stack);
					stack = ItemStack.EMPTY;
					player = null;
				}
			}
			if(portalCooldown == 0) {
				List<Entity> inRange = world.getEntitiesInAABBexcluding(this, getEntityBoundingBox(), entity -> entity instanceof EntityLivingBase);
				if(!inRange.isEmpty()) {
					teleport((EntityLivingBase) inRange.get(0));
				}
			}
			if(portalCooldown > 0) {
				--portalCooldown;
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	private void reduceStack(EntityPlayer player, ItemStack toRemove) {
		if(player.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			IItemHandler handler = player.getCapability(ITEM_HANDLER_CAPABILITY, null);
			for(int i = 0; i < handler.getSlots(); i++) {
				if(toRemove.isItemEqual(handler.getStackInSlot(i))) {
					handler.extractItem(i, 1, false);
					break;
				}
			}
		}
	}

	private void teleport(EntityLivingBase base) {
		Vec3d vec3d = getLookVec();

		@SuppressWarnings("unchecked")
		List<EntityGap> list = (List<EntityGap>) (List<?>) world.getEntitiesInAABBexcluding(this, getEntityBoundingBox().grow(
				ConfigHandler.grimoireOfAlice.features.gapRange), entity -> entity instanceof EntityGap);

		EntityGap gap = null;
		if(teleportByColor) {
			gap = getColor() == EnumDyeColor.WHITE ? getClosestGap(list) : getClosestGapColorMatch(list);
		}

		if(gap != null) {
			gap.portalCooldown = 50;
			if(base instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) base;
				player.setPositionAndUpdate(gap.prevPosX + vec3d.x * 1.5, gap.prevPosY + vec3d.y * 1.5, gap.prevPosZ + vec3d.z * 1.5);
			}
			else {
				base.setPosition(gap.prevPosX + vec3d.x * 1.5, gap.prevPosY + vec3d.y * 1.5, gap.prevPosZ + vec3d.z * 1.5);
			}
			gap.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 0.1F, 0.1F);
		}
	}

	@Nullable
	private EntityGap getClosestGapColorMatch(List<EntityGap> gaps) {
		return sortGaps(gaps.stream().filter(g -> g.getColor() == getColor())).findFirst().orElse(null);
	}

	@Nullable
	private EntityGap getClosestGap(List<EntityGap> gaps) {
		return sortGaps(gaps.stream()).findFirst().orElse(null);
	}

	private Stream<EntityGap> sortGaps(Stream<EntityGap> gaps) {
		float maxClose = ConfigHandler.grimoireOfAlice.features.gapRange;
		float min = 5;
		ToDoubleFunction<EntityGap> toDouble = g -> g.getDistanceSqToEntity(this);

		return gaps.filter(g -> {
			double dist = g.getDistanceSqToEntity(this);
			return dist > min * min && dist < maxClose * maxClose;
		}).sorted(Comparator.comparingDouble(toDouble));
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!stack.isEmpty() && isItemDye(stack)) {
			//TODO: Use other part of ore name to get the actual color
			EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());
			if(enumdyecolor != getColor()) {
				setColor(enumdyecolor);
				stack.shrink(1);
			}
			player.playSound(SoundEvents.ENTITY_ITEMFRAME_PLACE, 1F, 1F);
		}
		else if(player.isSneaking()) {
			if(!world.isRemote) {
				dropItem(ModItems.GAP, 1);
				setDead();
			}
			player.playSound(SoundEvents.ENTITY_ITEMFRAME_BREAK, 1F, 1F);
		}
		return true;
	}

	private boolean isItemDye(ItemStack stack) {
		for(int oreId : OreDictionary.getOreIDs(stack)) {
			if(OreDictionary.getOreName(oreId).startsWith("dye")) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public Vec3d getLookVec() {
		return this.getLook(1.0F);
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		return new AxisAlignedBB(posX - 0.5, posY - 0.5, posZ - 0.5, posX + 0.5, posY + 0.5, posZ + 0.5);
	}

	@Override
	protected void entityInit() {
		dataManager.register(COLOR_DATA, (byte) (EnumDyeColor.WHITE.getMetadata() & 15));
	}

	public void setShouldTeleportByColor(boolean byColor) {
		this.teleportByColor = byColor;
	}

	public EnumDyeColor getColor() {
		return EnumDyeColor.byMetadata(this.dataManager.get(COLOR_DATA) & 15);
	}

	public void setColor(EnumDyeColor color) {
		byte b0 = this.dataManager.get(COLOR_DATA);
		this.dataManager.set(COLOR_DATA, (byte) (b0 & 240 | color.getMetadata() & 15));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		setColor(EnumDyeColor.byMetadata(compound.getByte(COLOR) & 15));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setByte(COLOR, this.dataManager.get(COLOR_DATA));
	}
}