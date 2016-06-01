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
import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyKeyStone;
import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyStone;
import arekkuusu.grimoireOfAlice.client.tile.TileEntityOnbashira;
import arekkuusu.grimoireOfAlice.item.GOAItem;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ProxyClient extends ProxyServer {

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHolyKeyStone.class, new RenderHolyKeyStone());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOnbashira.class, new RenderOnbashira());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHolyStone.class, new RenderHolyStone());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GOABlock.blockHolyKeyStone), new ItemRenderHolyKeyStone());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GOABlock.blockHolyStone), new ItemRenderHolyStone());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GOABlock.blockOnbashira), new ItemRenderOnbashira());
		MinecraftForgeClient.registerItemRenderer(GOAItem.itemPrimordialShield, new ItemRenderPrimordialShield());
		//MinecraftForgeClient.registerItemRenderer(GOAItem.itemKokorosMasks, new RenderKokorosMask());
	}

	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}