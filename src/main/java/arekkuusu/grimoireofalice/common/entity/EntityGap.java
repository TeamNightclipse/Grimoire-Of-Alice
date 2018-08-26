package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public class EntityGap extends Entity {

	private static final DataParameter<Byte> COLOR_DATA = EntityDataManager.createKey(EntityGap.class, DataSerializers.BYTE);
	private static final String COLOR = "color";

	private int portalCooldown;

	public EntityGap(World world) {
		super(world);
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
	public boolean getIsInvulnerable() {
		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote) {
			if(portalCooldown == 0) {
				List<Entity> inRange = world.getEntitiesInAABBexcluding(this, getEntityBoundingBox(), entity -> entity instanceof EntityLivingBase);
				if(!inRange.isEmpty()) {
					teleport((EntityLivingBase) inRange.get(0));
				}
			}
			if(portalCooldown > 0) --portalCooldown;
		}
	}

	private void teleport(EntityLivingBase base) {
		Vec3d vec3d = getLookVec();
		Stream<EntityGap> list = world.getEntitiesInAABBexcluding(this, getEntityBoundingBox()
				.grow(ConfigHandler.grimoireOfAlice.features.gapRange), entity -> entity instanceof EntityGap).stream()
				.map(entity -> ((EntityGap) entity));
		EntityGap gap = getColor() == EnumDyeColor.WHITE ? getClosestGap(list) : getClosestGapColorMatch(list);
		if(gap != null) {
			gap.portalCooldown = 50;
			if(base instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) base;
				player.setPositionAndUpdate(gap.prevPosX + vec3d.x * 1.5, gap.prevPosY + vec3d.y * 1.5, gap.prevPosZ + vec3d.z * 1.5);
			} else {
				base.setPosition(gap.prevPosX + vec3d.x * 1.5, gap.prevPosY + vec3d.y * 1.5, gap.prevPosZ + vec3d.z * 1.5);
			}
			gap.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 0.1F, 0.1F);
		}
	}

	@Nullable
	private EntityGap getClosestGapColorMatch(Stream<EntityGap> gaps) {
		return sortGaps(gaps.filter(g -> g.getColor() == getColor())).findFirst().orElse(null);
	}

	@Nullable
	private EntityGap getClosestGap(Stream<EntityGap> gaps) {
		return sortGaps(gaps).findFirst().orElse(null);
	}

	private Stream<EntityGap> sortGaps(Stream<EntityGap> gaps) {
		ToDoubleFunction<EntityGap> toDouble = g -> g.getDistanceSq(this);
		float maxClose = ConfigHandler.grimoireOfAlice.features.gapRange;
		float min = 2;
		return gaps.filter(g -> {
			double dist = g.getDistanceSq(this);
			return dist > min * min && dist < maxClose * maxClose;
		}).sorted(Comparator.comparingDouble(toDouble));
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		Optional<EnumDyeColor> optional;
		if(!stack.isEmpty() && (optional = getItemDye(stack)).isPresent()) {
			if(!world.isRemote) {
				EnumDyeColor dye = optional.get();
				setColor(dye);
			}
			player.playSound(SoundEvents.ENTITY_ITEMFRAME_PLACE, 1F, 1F);
		} else if(player.isSneaking()) {
			if(!world.isRemote) {
				dropItem(ModItems.GAP, 1);
				setDead();
			}
			player.playSound(SoundEvents.ENTITY_ITEMFRAME_BREAK, 1F, 1F);
		}
		return true;
	}

	private static Optional<EnumDyeColor> getItemDye(ItemStack stack) {
		for(int oreId : OreDictionary.getOreIDs(stack)) {
			String name = OreDictionary.getOreName(oreId);
			if(name.startsWith("dye")) {
				String dyeName = name.substring(3);
				return Arrays.stream(EnumDyeColor.values()).filter(dye -> dye.getName().equalsIgnoreCase(dyeName)).findFirst();
			}
		}
		return Optional.empty();
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