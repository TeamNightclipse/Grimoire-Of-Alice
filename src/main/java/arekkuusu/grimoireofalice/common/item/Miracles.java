package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.items.IItemHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Miracles {

	@CapabilityInject(IItemHandler.class)
	private static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;

	public static final Miracle WIND = new Miracle() {

		@Override
		public String getName() {
			return "wind";
		}

		@Override
		public boolean canUse(int charge, EntityPlayer player) {
			return charge >= 1 && !player.isInWater();
		}

		@Override
		public int execute(Item item, int charge, EntityPlayer player) {
			World world = player.world;
			world.playSound(null, player.getPosition(), GrimoireSoundEvents.WIND, SoundCategory.PLAYERS, 1F, 1F);
			Vec3d vec = player.getLookVec();
			List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class,
					player.getEntityBoundingBox().offset(vec.x * 2, vec.y * 2, vec.z * 2).grow(3D), entity -> !player.equals(entity));

			boolean isServer = world instanceof WorldServer;
			list.forEach(entity -> {
				if(isServer) {
					((WorldServer) world).spawnParticle(EnumParticleTypes.CLOUD, entity.posX, entity.posY, entity.posZ, 5, 0, 0, 0, 0.1D);
				}
				entity.motionX = -MathHelper.sin((float) Math.toRadians(player.rotationYaw)) * 4;
				entity.motionY = -MathHelper.sin((float) Math.toRadians(player.rotationPitch)) * 4;
				entity.motionZ = MathHelper.cos((float) Math.toRadians(player.rotationYaw)) * 4;
			});

			world.playSound(null, player.getPosition(), SoundEvents.ENTITY_WITCH_THROW, SoundCategory.PLAYERS, 1F, 1F);
			return 1;
		}
	};

	public static final Miracle HEAL = new Miracle() {

		@Override
		public String getName() {
			return "heal";
		}

		@Override
		public boolean canUse(int charge, EntityPlayer player) {
			return charge >= 2 && player.shouldHeal();
		}

		@Override
		public int execute(Item item, int charge, EntityPlayer player) {
			World world = player.world;
			float health = player.getMaxHealth() - player.getHealth();
			player.heal(health * player.getRNG().nextFloat());
			if(world instanceof WorldServer) {
				((WorldServer)world).spawnParticle(EnumParticleTypes.HEART, player.posX, player.posY + 2, player.posZ, 20, 0D, 0D, 0D, 0D);
			}

			world.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1F, 1F);
			return 2;
		}
	};

	public static final Miracle POTIONS = new Miracle() {

		@Override
		public String getName() {
			return "potions";
		}

		@Override
		public boolean canUse(int charge, EntityPlayer player) {
			return charge >= 3;
		}

		@Override
		public int execute(Item item, int charge, EntityPlayer player) {
			List<PotionEffect> effects = consumePotionEffectsInInventory(player);
			if(!effects.isEmpty()) {
				addPlayerPotionEffects(player, effects);
				player.getCooldownTracker().setCooldown(item, 15);

				player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS, 1F, 1F);
				return 3;
			}
			else return 0;
		}

		private List<PotionEffect> consumePotionEffectsInInventory(EntityPlayer player) {
			IItemHandler capability = player.getCapability(ITEM_HANDLER_CAPABILITY, null);
			List<PotionEffect> potionEffects = new ArrayList<>();
			for(int i = 0; i < capability.getSlots(); ++i) {
				ItemStack stack = capability.getStackInSlot(i);
				if(!stack.isEmpty() && PotionUtils.getPotionFromItem(stack) != PotionTypes.WATER) {
					if(!player.capabilities.isCreativeMode) {
						//noinspection ConstantConditions
						capability.extractItem(i, 1, false);
					}
					potionEffects.addAll(PotionUtils.getEffectsFromStack(stack));
				}
			}
			return potionEffects;
		}

		private void addPlayerPotionEffects(EntityPlayer player, List<PotionEffect> potionEffects) {
			potionEffects.forEach(player::addPotionEffect);
		}
	};

	public static class WeatherMiracle implements Miracle {

		private final int cleanWeatherTime;
		private final int rainTime;
		private final int thunderTime;
		private final boolean raining;
		private final boolean thundering;

		private final String name;
		private final int chargeCost;

		/**
		 * @param cleanWeatherTime How long there will be clear weather, or random if -1
		 * @param rainTime How long there will be rain, or random if -1
		 * @param thunderTime How long there will be thunder, or random if -1
		 * @param raining If there will be rain
		 * @param thundering If there will be thunder
		 * @param name The miracle name
		 * @param chargeCost The charge cost
		 */
		public WeatherMiracle(int cleanWeatherTime, int rainTime, int thunderTime, boolean raining, boolean thundering, String name, int chargeCost) {
			this.cleanWeatherTime = cleanWeatherTime;
			this.rainTime = rainTime;
			this.thunderTime = thunderTime;
			this.raining = raining;
			this.thundering = thundering;
			this.name = name;
			this.chargeCost = chargeCost;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public boolean canUse(int charge, EntityPlayer player) {
			return charge >= chargeCost;
		}

		@Override
		public int execute(Item item, int charge, EntityPlayer player) {
			WorldInfo worldInfo = player.world.getWorldInfo();
			int randTime = 400 + player.getRNG().nextInt(1000) * 20;

			worldInfo.setCleanWeatherTime(cleanWeatherTime == -1 ? randTime : cleanWeatherTime);
			worldInfo.setRainTime(rainTime == -1 ? randTime : rainTime);
			worldInfo.setThunderTime(thunderTime == -1 ? randTime : thunderTime);
			worldInfo.setRaining(raining);
			worldInfo.setThundering(thundering);

			player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.PLAYERS, 1F, 1F);
			return chargeCost;
		}
	}

	public static final Miracle RAIN = new WeatherMiracle(0, -1, -1, true, false, "rain", 4);
	public static final Miracle THUNDER = new WeatherMiracle(0, -1, -1, true, true, "thunder", 5);
	public static final Miracle CLEAR = new WeatherMiracle(-1, 0, 0, false, false, "clear", 1);

	public static final Miracle SOIL = new Miracle() {

		@Override
		public String getName() {
			return "soil";
		}

		@Override
		public boolean canUse(int charge, EntityPlayer player) {
			return charge >= 8;
		}

		@Override
		public int execute(Item item, int charge, EntityPlayer player) {
			applyBonemealRandomPos(player, player.world);

			player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.PLAYERS, 1F, 1F);
			return 6;
		}

		private void applyBonemealRandomPos(EntityPlayer player, World world) {
			Random rand = player.getRNG();

			BlockPos posI = new BlockPos(player.posX - 4 + rand.nextInt(4), player.posY - 4 + rand.nextInt(4), player.posZ - 4 + rand.nextInt(4));
			BlockPos posF = new BlockPos(player.posX + 4 + rand.nextInt(4), player.posY + 4 + rand.nextInt(4), player.posZ + 4 + rand.nextInt(4));

			BlockPos.getAllInBox(posI, posF).forEach(pos -> {
				if(rand.nextBoolean() && ItemDye.applyBonemeal(new ItemStack(Items.DYE), world, pos, player, player.getActiveHand())) {
					IBlockState state = world.getBlockState(pos);
					for(int j = 0; j < 15; ++j) {
						double d0 = rand.nextGaussian() * 0.02D;
						double d1 = rand.nextGaussian() * 0.02D;
						double d2 = rand.nextGaussian() * 0.02D;
						world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + rand.nextFloat(), pos.getY() + rand.nextFloat() * state.getBoundingBox(world, pos).maxY, pos.getZ() + rand.nextFloat(), d0, d1, d2);
					}
					if(!world.isRemote) {
						world.playEvent(2005, pos, 0);
					}
				}
			});
		}
	};

	public static final Miracle TIME = new Miracle() {

		@Override
		public String getName() {
			return "time";
		}

		@Override
		public boolean canUse(int charge, EntityPlayer player) {
			return charge >= 15;
		}

		@Override
		public int execute(Item item, int charge, EntityPlayer player) {
			World world = player.world;
			long time = world.isDaytime() ? 14_000 : 0;
			world.setWorldTime(time);

			player.getCooldownTracker().setCooldown(item, 5);

			world.playSound(null, player.getPosition(), SoundEvents.AMBIENT_CAVE, SoundCategory.PLAYERS, 1F, 1F);
			return 10;
		}
	};

	public static final Miracle TESTIFICATE = new Miracle() {

		private final Method convertZombie = ReflectionHelper.findMethod(EntityZombieVillager.class, "startConverting", "func_191991_a", UUID.class, int.class);

		@Override
		public String getName() {
			return "testificate";
		}

		@Override
		public boolean canUse(int charge, EntityPlayer player) {
			return charge >= 15;
		}

		@Override
		public int execute(Item item, int charge, EntityPlayer player) {
			if(convertNearZombies(player, player.world)) {
				player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_WITCH_AMBIENT, SoundCategory.PLAYERS, 1F, 1F);
				return 15;
			}
			else return 0;
		}

		private boolean convertNearZombies(EntityPlayer player, World world) {
			List<EntityZombieVillager> list = world.getEntitiesWithinAABB(EntityZombieVillager.class, player.getEntityBoundingBox().grow(10));
			list.forEach(this::convertToVillager);
			return !list.isEmpty();
		}

		private void convertToVillager(EntityZombieVillager zombie) {
			try {
				convertZombie.invoke(zombie, zombie.getEntityData().getUniqueId("ConversionPlayer"), zombie.getEntityData().getInteger("ConversionTime"));
			} catch(IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	};
}
