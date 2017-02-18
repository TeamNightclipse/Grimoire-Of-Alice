package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EntityGap extends Entity {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
	private static final DataParameter<Byte> COLOR_DATA = EntityDataManager.createKey(EntityGap.class, DataSerializers.BYTE);

	private static final String TARGET = "target";
	private static final String ORIGIN = "origin";
	private static final String COLOR = "color";
	private UUID target;
	private UUID origin;

	private boolean teleportByColor = true;
	private int portalCooldown;
	private EntityPlayer player;
	private ItemStack stack;

	public EntityGap(World worldIn) {
		super(worldIn);
	}

	public EntityGap(World worldIn, EntityPlayer player,@Nullable ItemStack stack) {
		super(worldIn);
		setPositionAndAngles(player);
		setOririnUUID(getOriginUUID());
		this.player = player;
		this.stack = stack;
	}

	public void setPositionAndAngles(EntityPlayer player) {
		Vec3d look = player.getLookVec();
		float distance = 2F;
		double dx = player.posX + look.xCoord * distance;
		double dy = player.posY + look.yCoord * distance + player.getEyeHeight();
		double dz = player.posZ + look.zCoord * distance;
		setPosition(dx, dy, dz);
		setRotation(player.rotationYaw, player.rotationPitch);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if(player != null) {
				if(player.isHandActive() && !player.isDead) {
					setPositionAndAngles(player);
				}
				else {
					player.getCooldownTracker().setCooldown(stack.getItem(), 25);
					reduceStack(player, stack);
					stack = null;
					player = null;
				}
			}
			if (portalCooldown == 0) {
				Optional<EntityLivingBase> optional = worldObj.getEntitiesInAABBexcluding(this, getEntityBoundingBox()
						, entity -> entity instanceof EntityLivingBase).stream().map(entity -> ((EntityLivingBase) entity)).findFirst();
				if (optional.isPresent()) {
					teleport(optional.get());
				}
			}
			if(portalCooldown > 0) {
				--portalCooldown;
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	private void reduceStack(EntityPlayer playerIn, ItemStack toRemove) {
		if (playerIn.hasCapability(ITEM_HANDLER_CAPABILITY, null)) {
			IItemHandler handler = playerIn.getCapability(ITEM_HANDLER_CAPABILITY, null);
			for (int i = 0; i < handler.getSlots(); i++) {
				if (handler.getStackInSlot(i) == toRemove) {
					handler.extractItem(i, 1, false);
				}
			}
		}
	}

	private void teleport(EntityLivingBase base) {
		Vec3d vec3d = getLookVec();

		List<EntityGap> list = worldObj.getEntitiesInAABBexcluding(this, getEntityBoundingBox().expandXyz(ConfigHandler.grimoireOfAlice.features.gapRange)
				, entity -> entity instanceof EntityGap).stream().map(entity -> (EntityGap) entity).collect(Collectors.toList());

		EntityGap gap = null;
		if(teleportByColor) {
			gap = getColor() == EnumDyeColor.WHITE ? getClosestGap(list) : getClosestGapColorMatch(list);
		}
		else if(target != null){
			gap = getGapByUUID(list, target);
		}

		if (gap != null) {
			gap.portalCooldown = 50;
			if (base instanceof EntityPlayer) {
				EntityPlayerMP playerMP = (EntityPlayerMP) base;
				playerMP.setPositionAndUpdate(gap.prevPosX + vec3d.xCoord * 1.5, gap.prevPosY + vec3d.yCoord * 1.5, gap.prevPosZ + vec3d.zCoord * 1.5);
			}
			else {
				base.setPosition(gap.prevPosX + vec3d.xCoord * 1.5, gap.prevPosY + vec3d.yCoord * 1.5, gap.prevPosZ + vec3d.zCoord * 1.5);
			}
			gap.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 0.1F, 0.1F);
		}
	}

	@Nullable
	private EntityGap getClosestGapColorMatch(List<EntityGap> gaps) {
		float closest = ConfigHandler.grimoireOfAlice.features.gapRange;
		float min = 5;
		EntityGap match = null;
		for (EntityGap gap: gaps) {
			float distance = gap.getDistanceToEntity(this);
			if (distance < closest && distance > min && gap.getColor().getDyeDamage() == getColor().getDyeDamage()) {
				closest = distance;
				match = gap;
			}
		}
		return match;
	}

	@Nullable
	private EntityGap getClosestGap(List<EntityGap> gaps) {
		float closest = ConfigHandler.grimoireOfAlice.features.gapRange;
		float min = 5;
		EntityGap match = null;
		for (EntityGap gap: gaps) {
			float distance = gap.getDistanceToEntity(this);
			if (distance < closest && distance > min) {
				closest = distance;
				match = gap;
			}
		}
		return match;
	}

	@Nullable
	private EntityGap getGapByUUID(List<EntityGap> gaps, UUID uuid) {
		for (EntityGap gap : gaps) {
			if (gap.getOriginUUID() == uuid) {
				return gap;
			}
		}
		return null;
	}

	public boolean processInitialInteract(EntityPlayer player, @Nullable ItemStack stack, EnumHand hand) {
		if (stack != null && isItemDye(stack)) {
			EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());
			if(enumdyecolor != getColor()) {
				setColor(enumdyecolor);
				--stack.stackSize;
			}
		}
		else if (!worldObj.isRemote) {
			dropItem(ModItems.GAP, 1);
			setDead();
		}
		player.playSound(SoundEvents.ENTITY_ITEMFRAME_BREAK, 1F, 1F);
		return true;
	}

	private boolean isItemDye(ItemStack stack) {
		for (int oreId : OreDictionary.getOreIDs(stack)) {
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
	public Vec3d getLook(float partialTicks) {
		if (partialTicks == 1.0F) {
			return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
		}
		else {
			float f = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * partialTicks;
			float f1 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * partialTicks;
			return this.getVectorForRotation(f, f1);
		}
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

	public void setOririnUUID(@Nullable UUID uuid) {
		this.origin = uuid;
	}

	public UUID getOriginUUID() {
		return this.origin;
	}

	public void setTargetUUID(@Nullable UUID uuid) {
		this.target = uuid;
	}

	@Nullable
	public UUID getTargetUUID() {
		return this.target;
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
		setTargetUUID(compound.getUniqueId(TARGET));
		setOririnUUID(compound.getUniqueId(ORIGIN));
		setColor(EnumDyeColor.byMetadata(compound.getByte(COLOR) & 15));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		if (target != null) {
			compound.setUniqueId(TARGET, target);
		}
		if (origin != null) {
			compound.setUniqueId(ORIGIN, origin);
		}
		compound.setByte(COLOR, this.dataManager.get(COLOR_DATA));
	}
}
