package arekkuusu.grimoireofalice.item.food;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ElixirPotion extends Potion {

	public ElixirPotion() {
		super(false, 3381504);
		setRegistryName(LibItemName.ELIXIRPOTION);
		GameRegistry.register(this);
		setPotionName("effect.elixir");
		setBeneficial();
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {

	}

	@Override
	public boolean isBadEffect() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) { }

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha) { }
}
