/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.masks;

import java.util.List;

import arekkuusu.grimoireOfAlice.client.model.ModelKokorosMasks;
import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemKokorosMasks extends ItemArmor /*implements ISpecialArmor*/{

	/*private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/KokorosMasks_layer_1.png");
	protected ModelBiped[] models = null;
	public int type;
	static IIcon willIcon;
	
	public ItemKokorosMasks(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		if(stack.getItem() == GOAItem.itemKokorosMasks){
			return LibMod.MODID + ":textures/models/KokorosMasks_layer_1.png";
		} else {
			return null;
		}
    }
	
	@SideOnly(Side.CLIENT)
	public static void render(ItemStack stack, RenderPlayerEvent event) {
			GL11.glPushMatrix();
			float f = willIcon.getMinU();
			float f1 = willIcon.getMaxU();
			float f2 = willIcon.getMinV();
			float f3 = willIcon.getMaxV();
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
			GL11.glRotatef(90F, 0F, 1F, 0F);
			GL11.glRotatef(180F, 1F, 0F, 0F);
			GL11.glTranslatef(-0.26F, 0.15F, -0.39F);
			GL11.glScalef(0.2F, 0.2F, 0.2F);
			ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, willIcon.getIconWidth(), willIcon.getIconHeight(), 1F / 16F);
			GL11.glPopMatrix();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
			ModelBiped model = getArmorModelForSlot(entityLiving, itemStack, armorSlot);
			if(model == null)
				model = provideArmorModelForSlot(itemStack, armorSlot);

			if(model != null)
				return model;

		return super.getArmorModel(entityLiving, itemStack, armorSlot);
	}
	
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModelForSlot(EntityLivingBase entity, ItemStack stack, int slot) {
		if(models == null)
			models = new ModelBiped[4];

		return models[slot];
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped provideArmorModelForSlot(ItemStack stack, int slot) {
		models[slot] = new ModelKokorosMasks();
		return models[slot];
	}*/

	private static final ModelBiped var10 = new ModelKokorosMasks();
	
	public ItemKokorosMasks(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		if(stack.getItem() == GOAItem.itemKokorosMasks){
			return LibMod.MODID + ":textures/models/KokorosMasks_layer_1.png";
		} else {
			return null;
		}
    }
	
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(int id){
	return var10;
	}
	
	@SideOnly(Side.CLIENT)
	ModelBiped armorModel = new ModelBiped();
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {

		if(itemStack != null){
				if(itemStack.getItem() instanceof ItemKokorosMasks){
					int type = ((ItemArmor)itemStack.getItem()).armorType;

					if(type == 1 || type == 1){
						armorModel = getArmorModel(1);
					} else {
						armorModel = getArmorModel(1);
					}
				}
				if(armorModel != null){
					armorModel.bipedHead.showModel = armorSlot == 3;
					armorModel.bipedHeadwear.showModel = armorSlot == 3;
					armorModel.bipedBody.showModel = armorSlot == 0;
					armorModel.bipedRightArm.showModel = armorSlot == 3;
					armorModel.bipedLeftArm.showModel = armorSlot == 3;
					armorModel.bipedRightLeg.showModel = armorSlot == 3;
					armorModel.bipedLeftLeg.showModel = armorSlot == 3;
					armorModel.isSneak = entityLiving.isSneaking();
					armorModel.isRiding = entityLiving.isRiding();
					armorModel.isChild = entityLiving.isChild();
					armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 :0;
					return armorModel;
				}
			}
		return null;
		}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.DARK_AQUA
				+ "Tsukumogami of Emotions");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "Feel the power of 66 masks");
		p_77624_3_.add(EnumChatFormatting.GOLD
				+ "being worn at the same time");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
			super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);{
			EntityPlayer player = (EntityPlayer) p_77663_3_;
			if (!p_77663_2_.isRemote && p_77663_3_ instanceof EntityPlayer) {
				ItemStack[] inventory = player.inventory.mainInventory;
				for (int i = 0; i < inventory.length; i++) {
					if (inventory[i] != null && inventory[i].getItem() == this && i != p_77663_4_) {
						player.dropPlayerItemWithRandomChoice(inventory[i], true);
						inventory[i] = null;
						}
					}
				}
			}
		}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel >= 30){
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 25, 4));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}
}
