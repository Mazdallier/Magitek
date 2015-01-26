package democretes.item.tools;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;

public class ItemStaffSmite extends ItemMTBase {
	
	public ItemStaffSmite() {
		setUnlocalizedName(Reference.MOD_PREFIX + ".smite");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer p) {
		List<Entity> targets = world.getEntitiesWithinAABBExcludingEntity(p, AxisAlignedBB.getBoundingBox(p.posX-20, p.posY-2, p.posZ-20, p.posX+20, p.posY+5, p.posZ+20));
		EntityLightningBolt lightning;
		for(Entity entity : targets) {
			lightning = new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ);
			world.addWeatherEffect(lightning);
		}
		return stack;
	}

}
