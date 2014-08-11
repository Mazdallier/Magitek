package democretes.item.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import democretes.block.coils.TileMachtCoil;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;
import democretes.utils.helper.StringHelper;

public class ItemCoilLinker extends ItemMTBase {
	
	public ItemCoilLinker() {
		setUnlocalizedName(Reference.MOD_PREFIX + ".coilLinker");
	}
	
	boolean first = true;
	TileMachtCoil coil;
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(player != null && stack != null) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if(this.coil == null && tile instanceof TileMachtCoil) {
				this.coil = (TileMachtCoil)tile;
				player.addChatMessage(new ChatComponentText(StringHelper.localize("magitek.node.selected")));
			}else if(this.coil != null && this.coil.canLink(tile)) {
				int distance = (Math.abs(this.coil.xCoord - tile.xCoord) + Math.abs(this.coil.yCoord - tile.yCoord) + Math.abs(this.coil.xCoord -tile.zCoord))/3;
				if(distance <= this.coil.range && this.coil.canLink(tile)) {
					this.coil.sources.add(tile);
					player.addChatMessage(new ChatComponentText(StringHelper.localize("magitek.node.linked")));
				}else if(distance > this.coil.range) {
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
