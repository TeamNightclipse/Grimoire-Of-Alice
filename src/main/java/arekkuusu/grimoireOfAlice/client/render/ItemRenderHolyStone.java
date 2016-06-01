/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.render;

import org.lwjgl.opengl.GL11;

import arekkuusu.grimoireOfAlice.client.model.ModelHolyStone;
import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyStone;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderHolyStone implements IItemRenderer {

	private ModelBase MODEL;
	private static final ResourceLocation TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/HolyStone.png");

	public ItemRenderHolyStone() {
		MODEL = new ModelHolyStone();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
			case ENTITY:
			case INVENTORY:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch(type) {
			case ENTITY: {
				return false;
			}
			case EQUIPPED: {
				return false;
			}
			case EQUIPPED_FIRST_PERSON: {
				return false;
			}
			case INVENTORY: {
				return helper == ItemRendererHelper.INVENTORY_BLOCK;
			}
			default: {
				return false;
			}
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		float s = 1.4F;
		GL11.glScalef(s, s, s);
		GL11.glRotatef(-5F, 1F, 0F, 0F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityHolyStone(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		/*switch(type){
		case ENTITY: {
			GL11.glPushMatrix();
			//GL11.glRotatef(0, 0, 0, 1);
			//GL11.glRotatef(180, 0, 1, 0);
			//GL11.glRotatef(180, 1, 0, 0);
			//GL11.glTranslatef(0, -2.95f, 0f);
			GL11.glScalef(0.6F, 0.6F, 0.6F);;
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
			GL11.glPushMatrix();
			MODEL.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
		case EQUIPPED: {
		}
		case EQUIPPED_FIRST_PERSON:{
			GL11.glPushMatrix();
			GL11.glRotatef(180, 0F, 0f, 1f);
			GL11.glTranslatef(0f, -1f, 0f);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
			GL11.glPushMatrix();
			MODEL.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
		case INVENTORY:{
			GL11.glPushMatrix();
			GL11.glRotatef(180, 0F, 0f, 1f);
			GL11.glTranslatef(0f, -1f, 0f);
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
			GL11.glPushMatrix();
			MODEL.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
		default: break;
		}*/
	}

}
