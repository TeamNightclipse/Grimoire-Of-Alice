/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.potion;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.common.lib.LibMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionMod extends Potion {

	private final int iconIndex;

	public PotionMod(String name, boolean badEffect, int color, int iconIndex) {
		super(badEffect, color);
		setPotionName("effect." + LibMod.MODID + "." + name);
		setRegistryName(name);
		this.iconIndex = iconIndex;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		render(x + 6, y + 7, 1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		render(x + 3, y + 3, alpha);
	}

	@SideOnly(Side.CLIENT)
	private void render(int x, int y, float alpha) {
		Minecraft.getMinecraft().renderEngine.bindTexture(ResourceLocations.POTION_TEXTURES);
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer buf = tessellator.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		GlStateManager.color(1, 1, 1, alpha);

		int textureX = iconIndex % 8 * 18;
		int textureY = 198 + iconIndex / 8 * 18;
		float f = 0.00390625F; //What is this for?

		buf.pos(x, y + 18, 0).tex(textureX * f, (textureY + 18) * f).endVertex();
		buf.pos(x + 18, y + 18, 0).tex((textureX + 18) * f, (textureY + 18) * f).endVertex();
		buf.pos(x + 18, y, 0).tex((textureX + 18) * f, textureY * f).endVertex();
		buf.pos(x, y, 0).tex(textureX * f, textureY * f).endVertex();

		tessellator.draw();
	}
}
