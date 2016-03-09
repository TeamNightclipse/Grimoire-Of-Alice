/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client;

import net.minecraft.client.renderer.entity.RenderSnowball;
import cpw.mods.fml.client.registry.RenderingRegistry;
import arekkuusu.grimoireofalice.ProxyServer;
import arekkuusu.grimoireofalice.client.entity.proyectile.EntityThrowingExplosiveDoll;
import arekkuusu.grimoireofalice.client.entity.proyectile.EntityThrowingNeedleDoll;
import arekkuusu.grimoireofalice.client.lib.LibResource;
import arekkuusu.grimoireofalice.client.model.ModelDoll;
import arekkuusu.grimoireofalice.client.render.RenderDoll;
import arekkuusu.grimoireofalice.item.GOAItem;

public class ProxyClient extends ProxyServer{

	@Override
	public void RegisterRenders(){
		
	  //RenderingRegistry.registerEntityRenderingHandler(EntityTHDoll.class, new RenderLivingGOA(new ModelTHDoll(), LibResource.Doll_O));
	  RenderingRegistry.registerEntityRenderingHandler(EntityThrowingExplosiveDoll.class, new RenderDoll(new ModelDoll(), LibResource.ALICE_DOLL));
	  RenderingRegistry.registerEntityRenderingHandler(EntityThrowingNeedleDoll.class, new RenderDoll(new ModelDoll(), LibResource.ALICE_DOLL));
		
	}
	
}