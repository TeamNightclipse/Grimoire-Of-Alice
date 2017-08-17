package arekkuusu.grimoireofalice.common.core.helper;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.Multimap;

import arekkuusu.grimoireofalice.api.tile.ITileItemHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MiscHelper {

	public static void multiplyItemModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id, double multiplier) {
		Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getName());
		Optional<AttributeModifier> modifierOptional = modifiers.stream()
				.filter(attributeModifier -> attributeModifier.getID().equals(id))
				.findFirst();

		if(modifierOptional.isPresent()) {
			AttributeModifier modifier = modifierOptional.get();
			modifiers.remove(modifier);
			modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), modifier.getAmount() * multiplier, modifier.getOperation()));
		}
	}

	public static RayTraceResult rayTraceLook(EntityPlayer player, double range) {
		Vec3d look = player.getLookVec();
		Vec3d vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
		Vec3d vec3d1 = new Vec3d(player.posX + look.x * range, player.posY + player.getEyeHeight() + look.y * range, player.posZ + look.z * range);
		return player.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
	}

	public static void pushEntity(Entity pusher, Entity pushed) {
		Vec3d pusherPos = pusher.getPositionVector();
		Vec3d pushedPos = pushed.getPositionVector();
		double ratio = pusherPos.distanceTo(pushedPos) / 4;
		double scaling = 1 - ratio;
		Vec3d motion = pusherPos.subtract(pushedPos).scale(scaling);
		pushed.motionX = -motion.x * 2;
		pushed.motionY = .3F;
		pushed.motionZ = -motion.z * 2;
	}

	public static boolean tileEntityHandlerProcesInteract(World world, BlockPos pos, EntityPlayer player, EnumHand hand) {
		ItemStack heldItem = player.getHeldItem(hand);
		ITileItemHolder tile = (ITileItemHolder) world.getTileEntity(pos);
		boolean ok = false;
		if(tile != null) {
			if(player.isSneaking()) {
				ok = tile.removeItem(player);
			}
			else if(!heldItem.isEmpty()) {
				ok = tile.addItem(player, heldItem);
			}
		}
		return ok;
	}
}
