/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client;

import arekkuusu.grimoireofalice.client.effect.*;
import arekkuusu.grimoireofalice.client.event.MalletClientEvent;
import arekkuusu.grimoireofalice.client.event.TimeStopEvent;
import arekkuusu.grimoireofalice.client.render.ModRenders;
import arekkuusu.grimoireofalice.client.util.SpriteLibrary;
import arekkuusu.grimoireofalice.client.util.helper.ModelHandler;
import arekkuusu.grimoireofalice.common.Alice;
import arekkuusu.grimoireofalice.common.core.ISidedProxy;
import arekkuusu.grimoireofalice.common.core.handler.GuiHandler;
import net.katsstuff.teamnightclipse.mirror.Mirror;
import net.katsstuff.teamnightclipse.mirror.client.particles.IMirrorParticle;
import net.katsstuff.teamnightclipse.mirror.data.Vector3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy implements ISidedProxy {

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

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new MalletClientEvent());
		MinecraftForge.EVENT_BUS.register(new TimeStopEvent());
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
	public void spawnNeedleSwing(World world, Vector3 pos, Vector3 speed, int age, float scale) {
		if(doParticle()) {
			add(new NeedleSwing(world, pos, speed, age, scale));
		}
	}

	@Override
	public void spawnNetherFire(World world, Vector3 pos, Vector3 speed, int age, float scale) {
		if(doParticle()) {
			add(new NetherFire(world, pos, speed, age, scale));
		}
	}

	@Override
	public void spawnRedGas(World world, Vector3 pos, Vector3 speed) {
		if(doParticle()) {
			add(new RedGas(world, pos, speed));
		}
	}

	@Override
	public void spawnRedMist(World world, Entity entity, Vector3 pos, Vector3 speed) {
		if(doParticle()) {
			add(new RedMist(world, entity, pos, speed));
		}
	}

	@Override
	public void spawnShinmyoumaruSpark(World world, Vector3 pos, Vector3 speed) {
		if(doParticle()) {
			add(new ShinmyoumaruSpark(world, pos, speed));
		}
	}

	public static void add(IMirrorParticle particle) {
		((net.katsstuff.teamnightclipse.mirror.client.ClientProxy) Mirror.proxy()).particleRenderer().addParticle(particle);
	}

	@SideOnly(Side.CLIENT)
	private static boolean doParticle() {
		int setting = Minecraft.getMinecraft().gameSettings.particleSetting;
		float chance;
		switch(setting) {
			case 1:
				chance = 0.6F;
				break;
			case 2:
				chance = 0.2F;
				break;
			default:
				return true;
		}
		return Math.random() < chance;
	}
}
