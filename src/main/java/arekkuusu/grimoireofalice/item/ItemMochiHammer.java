/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMochiHammer extends ItemModSword {

	ItemMochiHammer(ToolMaterial material) {
		super(material, LibItemName.MOCHIHAMMER);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Ooguninushi no Mikoto");
		list.add(TextFormatting.ITALIC + "\"The first strike is for Lord Daikoku~");
		list.add(TextFormatting.ITALIC + "The Second strike is for Lord Daikoku~");
		list.add(TextFormatting.ITALIC + "And for all of the 180 children...\"");
		list.add(TextFormatting.LIGHT_PURPLE + "Master of the Great Land");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if(player.getHeldItemMainhand() == stack) {
				Potion potion1 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 3
				Potion potion2 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 4
				if(potion1 != null && potion2 != null) {
					player.addPotionEffect(new PotionEffect(potion1, 0, 0));
					player.addPotionEffect(new PotionEffect(potion2, 0, 0));
				}
			}
		}
	}
}