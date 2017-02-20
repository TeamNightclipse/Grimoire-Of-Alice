package arekkuusu.grimoireofalice.common.plugin.danmakucore.item;

import arekkuusu.grimoireofalice.common.item.ItemModSword;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemHakuroukenSword extends ItemModSword {

	public ItemHakuroukenSword(ToolMaterial material) {
		super(material, LibItemName.HAKUROUKEN);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.hakurouken_sword_header.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (!worldIn.isRemote) {
			Vec3d vec = entityLiving.getLookVec();
			List<EntityDanmaku> list = worldIn.getEntitiesWithinAABB(EntityDanmaku.class, new AxisAlignedBB(entityLiving.getPosition())
					.offset(vec.xCoord * 2, vec.yCoord * 2, vec.zCoord * 2).expandXyz(3D));
			if (!list.isEmpty()) {
				list.forEach(danmaku -> reverseDanmaku(entityLiving, danmaku));
			}
		}
	}

	private void reverseDanmaku(EntityLivingBase entityLiving, EntityDanmaku danmaku) {
		danmaku.rotationYaw = entityLiving.rotationYaw;
		danmaku.rotationPitch = entityLiving.rotationPitch;
		Vec3d vec3d = entityLiving.getLookVec();
		danmaku.motionX *= vec3d.xCoord;
		danmaku.motionY *= -vec3d.yCoord;
		danmaku.motionZ *= vec3d.zCoord;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 100;
	}
}
