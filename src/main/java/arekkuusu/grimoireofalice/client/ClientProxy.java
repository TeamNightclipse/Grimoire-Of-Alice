/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client;

import arekkuusu.grimoireofalice.client.effect.NeedleSwing;
import arekkuusu.grimoireofalice.client.effect.NetherFire;
import arekkuusu.grimoireofalice.client.effect.RedGas;
import arekkuusu.grimoireofalice.client.effect.RedMist;
import arekkuusu.grimoireofalice.client.effect.ShinmyoumaruSpark;
import arekkuusu.grimoireofalice.client.event.MalletClientEvent;
import arekkuusu.grimoireofalice.client.render.ModRenders;
import arekkuusu.grimoireofalice.client.render.ParticleRenderer;
import arekkuusu.grimoireofalice.client.util.helper.ModelHandler;
import arekkuusu.grimoireofalice.client.util.SpriteLibrary;
import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.core.ISidedProxy;
import arekkuusu.grimoireofalice.common.core.handler.GuiHandler;
import net.katsstuff.danmakucore.DanmakuCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy implements ISidedProxy {

	public static final ParticleRenderer PARTICLE_RENDERER = new ParticleRenderer();

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		ModelHandler.registerModels();
	}

	@SubscribeEvent
	public static void onTextureAtlasSprite(TextureStitchEvent event) {
		TextureMap map = event.getMap();
		map.registerSprite(SpriteLibrary.SHINMYOUMARU_SPARKLE);
		map.registerSprite(SpriteLibrary.RED_MIST);
		map.registerSprite(SpriteLibrary.RED_GAS);
		map.registerSprite(SpriteLibrary.NETHER_FIRE);
		map.registerSprite(SpriteLibrary.NEEDLE_SWING);
	}

	//----------------Particle Renderer Start----------------//
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onTick(TickEvent.ClientTickEvent event) {
		if(event.side == Side.CLIENT && event.phase == TickEvent.Phase.START) {
			PARTICLE_RENDERER.update();
		}
	}

	@SubscribeEvent
	public static void onRenderAfterWorld(RenderWorldLastEvent event) {
		GlStateManager.pushMatrix();
		PARTICLE_RENDERER.renderAll(event.getPartialTicks());
		GlStateManager.popMatrix();
	}
	//----------------Particle Renderer End----------------//

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new MalletClientEvent());
		ModRenders.init();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Alice.instance, new GuiHandler());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void displayRecordText(ITextComponent text) {
		Minecraft.getMinecraft().ingameGUI.setOverlayMessage(text.getFormattedText(), false);
	}

	@Override
	public void spawnNeedleSwing(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int age, float scale) {
		if(doParticle()) {
			NeedleSwing particle = new NeedleSwing(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, age, scale);
			DanmakuCore.proxy.addParticle(particle);
		}
	}

	@Override
	public void spawnNetherFire(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int age, float scale) {
		if(doParticle()) {
			NetherFire particle = new NetherFire(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, age, scale);
			DanmakuCore.proxy.addParticle(particle);
		}
	}

	@Override
	public void spawnRedGas(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		if(doParticle()) {
			RedGas particle = new RedGas(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
			DanmakuCore.proxy.addParticle(particle);
		}
	}

	@Override
	public void spawnRedMist(World world, Entity entity, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		if(doParticle()) {
			RedMist particle = new RedMist(world, entity, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
			DanmakuCore.proxy.addParticle(particle);
		}
	}

	@Override
	public void spawnShinmyoumaruSpark(World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		if(doParticle()) {
			ShinmyoumaruSpark particle = new ShinmyoumaruSpark(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
			DanmakuCore.proxy.addParticle(particle);
		}
	}

	public boolean doParticle() {
		float chance = 1F;
		if(Minecraft.getMinecraft().gameSettings.particleSetting == 1)
			chance = 0.6F;
		else if(Minecraft.getMinecraft().gameSettings.particleSetting == 2)
			chance = 0.2F;

		return chance == 1F || Math.random() < chance;
	}
}
