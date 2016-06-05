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
import arekkuusu.grimoireOfAlice.block.GOABlock;
import arekkuusu.grimoireOfAlice.client.render.ItemRenderHolyKeyStone;
import arekkuusu.grimoireOfAlice.client.render.ItemRenderHolyStone;
import arekkuusu.grimoireOfAlice.client.render.ItemRenderOnbashira;
import arekkuusu.grimoireOfAlice.client.render.ItemRenderPrimordialShield;
import arekkuusu.grimoireOfAlice.client.render.RenderHolyKeyStone;
import arekkuusu.grimoireOfAlice.client.render.RenderHolyStone;
import arekkuusu.grimoireOfAlice.client.render.RenderOnbashira;
import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.tile.TileEntityHolyKeyStone;
import arekkuusu.grimoireOfAlice.tile.TileEntityHolyStone;
import arekkuusu.grimoireOfAlice.tile.TileEntityOnbashira;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

@CleanupDone
public class ProxyClient extends ProxyServer {

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHolyKeyStone.class, new RenderHolyKeyStone());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOnbashira.class, new RenderOnbashira());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHolyStone.class, new RenderHolyStone());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GOABlock.holyKeyStone), new ItemRenderHolyKeyStone());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GOABlock.holyStone), new ItemRenderHolyStone());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GOABlock.onbashira), new ItemRenderOnbashira());
		MinecraftForgeClient.registerItemRenderer(GOAItem.primordialShield, new ItemRenderPrimordialShield());
	}
}