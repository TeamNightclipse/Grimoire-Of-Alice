package arekkuusu.grimoireofalice.item.crafting;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VanillaCrafting {

    public static void booksAndStrings() {

        //Items
        shaped().grid("ISG", "SAS", "GSI")
                .where('S').mapsTo(Items.STRING)
                .where('G').mapsTo("dustGlowstone")
                .where('I').mapsTo(Items.GUNPOWDER)
                .where('A').mapsTo(Items.FIREWORK_CHARGE)
                .outputs(new ItemStack(ModItems.volatileString, 16)).build();

        shaped().grid("STS", "TAT", "STS")
                .where('S').mapsTo(Items.PAPER)
                .where('A').mapsTo(Items.COAL)
                .where('T').mapsTo("stickWood")
                .outputs(new ItemStack(ModItems.soldifiedPaper, 4)).build();

        //Blocks
        shaped().grid("SSS", "SSS", "SSS")
                .where('S').mapsTo(Items.SUGAR)
                .outputs(ModBlocks.sugarBlock).build();

        shaped().grid("ACA", "CSC", "ACA")
                .where('S').mapsTo("logWood")
                .where('A').mapsTo(ModItems.volatileString)
                .where('C').mapsTo(Items.COAL)
                .outputs(new ItemStack(ModBlocks.ropeBlock, 8)).build();

        shaped().grid("AAA", "ASA", "AAA")
                .where('A').mapsTo(Items.PAPER)
                .where('S').mapsTo(ModItems.soldifiedPaper)
                .outputs(new ItemStack(ModBlocks.paperBlock, 8)).build();

        shaped().grid("AAA", "ASA", "AAA")
                .where('A').mapsTo("stone")
                .where('S').mapsTo(ModItems.volatileString)
                .outputs(ModBlocks.compactStone).build();

        shaped().grid("ANA", "ASA", "ANA")
                .where('S').mapsTo(ModBlocks.compactStone)
                .where('N').mapsTo("logWood")
                .where('A').mapsTo(ModItems.shimenawaRope)
                .outputs(ModBlocks.compactStone).build();

        GameRegistry.addSmelting(Blocks.QUARTZ_BLOCK, new ItemStack(ModItems.gloriousNipponSteel), 0);
        GameRegistry.addSmelting(ModItems.impureRock, new ItemStack(ModItems.hihiirokane), 0);
    }

    public static void masks() {
        shapeless()
                .add(ModItems.foxMask)
                .add(ModItems.fukuNoKamiMask)
                .add(ModItems.hannyaMask)
                .add(ModItems.hyottokoMask)
                .add(ModItems.koomoteMask)
                .add(ModItems.maskOfHope)
                .add(ModItems.monkeyMask)
                .add(ModItems.raidenMask)
                .add(ModItems.ubaMask)
                .outputs(ModItems.kokorosMasks).build();

        shaped().grid("SSS", "GAG", "SSS")
                .where('S').mapsTo(ModItems.soldifiedPaper)
                .where('G').mapsTo(ModItems.volatileString)
                .where('A').mapsTo(Items.CLAY_BALL)
                .outputs(ModItems.mask).build();

        if(/*ConfigHandler.maskRecipes*/true){
            mask().where('R').mapsTo(Items.SUGAR).outputs(ModItems.foxMask).build();
            mask().where('R').mapsTo(Items.BLAZE_POWDER).outputs(ModItems.fukuNoKamiMask).build();
            mask().where('R').mapsTo(Items.POISONOUS_POTATO).outputs(ModItems.hannyaMask).build();
            mask().where('R').mapsTo(Items.GHAST_TEAR).outputs(ModItems.hyottokoMask).build();
            mask().where('R').mapsTo(Items.ROTTEN_FLESH).outputs(ModItems.koomoteMask).build();
            mask().where('R').mapsTo(Items.FERMENTED_SPIDER_EYE).outputs(ModItems.maskOfHope).build();
            mask().where('R').mapsTo(Items.MUSHROOM_STEW).outputs(ModItems.monkeyMask).build();
            mask().where('R').mapsTo("slimeball").outputs(ModItems.raidenMask).build();
            mask().where('R').mapsTo(Items.NETHER_STAR).outputs(ModItems.ubaMask).build();
        }
    }

    private static ShapedRecipe shaped() {
        return new ShapedRecipe();
    }

    private static ShapelessRecipe shapeless() {
        return new ShapelessRecipe();
    }

    private static ShapedRecipe mask() {
        return new ShapedRecipe().grid("IAR", "SGS", " S ")
                .where('S').mapsTo(ModItems.volatileString)
                .where('G').mapsTo(ModItems.mask)
                .where('A').mapsTo(Items.NETHER_STAR)
                .where('I').mapsTo(new ItemStack(Items.POTIONITEM, 1, 16));
    }

}
