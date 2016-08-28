/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class LargeItemRenderer implements IItemRenderer{

	private final double scale;
	private double rotate = 0;
	
	public LargeItemRenderer(double scale) {
		this.scale = scale;
	}
	
	public LargeItemRenderer(double scale, double rotate) {
		this.scale = scale;
		this.rotate = rotate;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... obj) {
		switch (type){
		case EQUIPPED_FIRST_PERSON:
			doTheStuff(item, (EntityLivingBase) obj[1], true);
			break;
		case EQUIPPED:
			doTheStuff(item, (EntityLivingBase) obj[1], false);
			break;
		default:
		}
	}

	private void doTheStuff(ItemStack stack, EntityLivingBase entity, boolean view) {
		GL11.glPushMatrix();
		double size = scale;
		if (view) {
			size *= 1.75F;
			GL11.glTranslated(-0.35F * scale, -0.125F * scale, 0.0F);
		} else {
			size *= (entity instanceof EntityPlayer ? 2.0F : 1.75F);
			GL11.glTranslated(1.0D - size, -0.125D * scale, 0.05D * scale);
			if(rotate!=0){
				GL11.glTranslated(2.0D - size, -2.125D * scale, 0.05D * scale);
				GL11.glTranslatef(0, 4, 0);
				GL11.glTranslated(1.2, -2, -0.1);
			}
		}
		GL11.glScaled(size, size, size);
		IIcon icon = stack.getItem().getIcon(stack, 0);
		Tessellator tessellator = Tessellator.instance;
		ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
		GL11.glPopMatrix();
	}
	 
}
