package democretes.utils.handlers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import democretes.item.MTItems;
import democretes.lib.Reference;

public class MTEventHandler {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
	    if(event.modID.equals(Reference.MOD_ID)) {
	        ConfigHandler.sync();
	    }
	}
	/*
	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event) {
		if(event.crafting != null) {
		
		}
	}
	*/
	/*
	@SubscribeEvent
	public void updateChunks(ChunkWatchEvent.Watch event) {
	}
	*/
	
	@SubscribeEvent
	public void rightClickEvent(PlayerInteractEvent event) {
		if(event.action == Action.RIGHT_CLICK_BLOCK) {
			if(event.entityPlayer.getCurrentEquippedItem() == null) {
				return;
			}
			int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
			if(event.entityPlayer.getCurrentEquippedItem().getItem() == Items.iron_ingot && event.world.getBlock(event.x, event.y, event.z) == Blocks.cauldron && meta > 0) {
				event.entityPlayer.getCurrentEquippedItem().stackSize--;
				if(event.entityPlayer.getCurrentEquippedItem().stackSize <= 0) {
					event.entityPlayer.inventory.mainInventory[event.entityPlayer.inventory.currentItem] = null;
				}
				ItemStack stack = new ItemStack(MTItems.ingot, 1, 0);
				if(!event.entityPlayer.inventory.addItemStackToInventory(stack)) {
					event.world.spawnEntityInWorld(new EntityItem(event.world, event.entityPlayer.posX, event.entityPlayer.posY, event.entityPlayer.posZ, stack));
				}
				event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, meta-1, 2);
				event.world.func_147453_f(event.x, event.y, event.z, event.world.getBlock(event.x, event.y, event.z));
			}
		}
	}
}
