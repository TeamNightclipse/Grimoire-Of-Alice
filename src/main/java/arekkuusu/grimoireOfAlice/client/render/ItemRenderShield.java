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

import arekkuusu.grimoireOfAlice.client.model.ModelPrimordialShield;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@CleanupDone
public class ItemRenderShield implements IItemRenderer {

	private ModelPrimordialShield MODEL;
	private static ResourceLocation TEXTURE;

	public ItemRenderShield(String Name) {
		MODEL = new ModelPrimordialShield();
		TEXTURE = new ResourceLocation(LibMod.MODID, "textures/models/"+Name);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
			case EQUIPPED:
				return true;
			case EQUIPPED_FIRST_PERSON:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Entity entity = (Entity)data[1];
		switch(type) {
			case EQUIPPED:
				render(entity);
				break;
			case EQUIPPED_FIRST_PERSON: {
				render(entity);
				break;
			}
			default:
				break;
		}
	}

	private void render(Entity entity) {
		float limbSwing = 0F;
		float limbSwingAmount = 0F;
		float age = 0F;

		if(entity instanceof EntityLivingBase) {
			EntityLivingBase livingBase = (EntityLivingBase)entity;
			limbSwing = livingBase.limbSwing;
			limbSwingAmount = livingBase.limbSwingAmount;
			age = livingBase.getAge();
		}

		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		GL11.glRotatef(60F, 0F, 0F, 1F);
		GL11.glTranslatef(0.5F, -0.5F, -0.2F);
		MODEL.render(entity, limbSwing, limbSwingAmount, age, entity.getRotationYawHead(), entity.rotationPitch, 0.0625F);
		GL11.glPopMatrix();
	}
}
