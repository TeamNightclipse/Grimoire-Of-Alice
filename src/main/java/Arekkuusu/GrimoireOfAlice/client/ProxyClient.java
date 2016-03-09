/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client;

import arekkuusu.grimoireOfAlice.ProxyServer;
import arekkuusu.grimoireOfAlice.client.lib.LibResource;
import arekkuusu.grimoireOfAlice.client.model.ModelDoll;
import arekkuusu.grimoireOfAlice.client.render.RenderDoll;
import arekkuusu.grimoireOfAlice.entity.projectile.EntityThrowingExplosiveDoll;
import arekkuusu.grimoireOfAlice.entity.projectile.EntityThrowingNeedleDoll;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyServer {

	@Override
	public void RegisterRenders() {
		//RenderingRegistry.registerEntityRenderingHandler(EntityTHDoll.class, new RenderLivingGOA(new ModelTHDoll(), LibResource.Doll_O));
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingExplosiveDoll.class, new RenderDoll(new ModelDoll(), LibResource.ALICE_DOLL));
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingNeedleDoll.class, new RenderDoll(new ModelDoll(), LibResource.ALICE_DOLL));
	}
}