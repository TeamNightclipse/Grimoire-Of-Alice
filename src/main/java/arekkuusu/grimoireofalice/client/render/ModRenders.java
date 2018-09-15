package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.render.tile.TileItemStackRenderer;
import arekkuusu.grimoireofalice.client.render.tile.TileMiniShrineRenderer;
import arekkuusu.grimoireofalice.common.block.tile.TileMiniShrine;
import arekkuusu.grimoireofalice.common.block.tile.TileStoneSphere;
import arekkuusu.grimoireofalice.common.entity.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by Arekkuusu on 22/08/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
@SideOnly(Side.CLIENT)
public class ModRenders {

	public static void init() {
		registerEntity(EntityMagicCircle.class, RenderMagicCircle::new);
		registerEntity(EntityNazrinPendulum.class, RenderNazrinPendulum::new);
		registerEntity(EntityDragonJewel.class, RenderDragonJewel::new);
		registerEntity(EntityUnzanFist.class, RenderUnzanFist::new);
		registerEntity(EntityEllyScythe.class, RenderEllyScytheProyectile::new);
		registerEntity(EntityCursedDecoyDoll.class, RenderCursedDecoyDoll::new);
		registerEntity(EntityHakureiOrb.class, RenderHakureiOrb::new);
		registerEntity(EntityBarrier.class, RenderBarrier::new);
		registerEntity(EntityMiracleLantern.class, RenderMiracleLantern::new);
		registerEntity(EntityNetherSoul.class, RenderNetherSoul::new);
		registerEntity(EntityFierySword.class, RenderFierySword::new);
		registerEntity(EntityIceBlock.class, RenderIceBlock::new);
		registerEntity(EntityMiracleCircle.class, RenderMiracleCircle::new);
		registerEntity(EntitySpiritualStrikeTalisman.class, RenderSpiritualStrikeTalisman::new);
		registerEntity(EntityGap.class, RenderGap::new);
		registerEntity(EntityKinkakuJiCeiling.class, RenderKinkakuJiCeiling::new);
		registerEntity(EntityYoukaiBook.class, RenderYoukaiBook::new);
		registerEntity(EntityStopWatch.class, RenderStopWatch::new);
		registerEntity(EntityCameraSquare.class, RenderCameraSquare::new);

		registerTESR(TileMiniShrine.class, new TileMiniShrineRenderer());
		registerTESR(TileStoneSphere.class, new TileItemStackRenderer<>());
	}

	private static <T extends TileEntity> void registerTESR(Class<T> tile, TileEntitySpecialRenderer<T> render) {
		ClientRegistry.bindTileEntitySpecialRenderer(tile, render);
	}

	private static <T extends Entity> void registerEntity(Class<T> entity, IRenderFactory<? super T> render) {
		RenderingRegistry.registerEntityRenderingHandler(entity, render);
	}
}
