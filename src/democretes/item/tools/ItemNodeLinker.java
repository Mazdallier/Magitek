package democretes.item.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import democretes.block.nodes.TileNodeBase;
import democretes.item.ItemMTBase;
import democretes.utils.helper.StringHelper;

public class ItemNodeLinker extends ItemMTBase {
	
	boolean first = true;
	TileNodeBase node;
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return false;
		}
		if(player != null && stack != null) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if(this.node == null && tile instanceof TileNodeBase) {
				this.node = (TileNodeBase)tile;
				player.addChatMessage(new ChatComponentText(StringHelper.localize("magitek.node.selected")));
			}else if(this.node != null && this.node.canLink(tile)) {
				int distance = (Math.abs(this.node.xCoord - tile.xCoord) + Math.abs(this.node.yCoord - tile.yCoord) + Math.abs(this.node.xCoord -tile.zCoord))/3;
				if(distance <= this.node.range && this.node.canLink(tile)) {
					this.node.tiles.add(tile);
					player.addChatMessage(new ChatComponentText(StringHelper.localize("magitek.node.linked")));
				}else if(distance > this.node.range) {
					player.addChatMessage(new ChatComponentText(StringHelper.localize("magitek.node.distance")));
				}else{
					player.addChatMessage(new ChatComponentText(StringHelper.localize("magitek.node.failed")));
					return false;
				}
			}
		}
		return false;
	}

}
