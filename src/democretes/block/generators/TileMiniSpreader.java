package democretes.block.generators;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.MagitekApi;
import democretes.api.macht.IMachtStorage;
import democretes.block.TileEnergyTransferer;
import democretes.utils.handlers.ConfigHandler;

public class TileMiniSpreader extends TileEnergyTransferer {
	
	public TileMiniSpreader() {
		super(1000);
	}
	
	@Override
	protected void searchForTiles() {
		this.tiles.clear();
		int xx = xCoord;
		int yy = yCoord - 1;
		int zz = zCoord;
		Block block = this.worldObj.getBlock(xx, yy, zz);
		boolean canSearch = false;
		for(int i = 0; i < MagitekApi.horizontal.size(); i++) {
			if(block == MagitekApi.horizontal.get(i)) {
				canSearch = true;
				break;
			}
		}
		if(!canSearch) {
			for(int i = 0; i < MagitekApi.vertical.size(); i++) {
				if(block == MagitekApi.vertical.get(i)) {
					canSearch = true;
					break;
				}
			}
		}
		if(!canSearch) {
			return;
		}
		canSearch = false;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			for(int j = 1; j < ConfigHandler.range; j++) {
				block = this.worldObj.getBlock(xx + dir.offsetX*j, yy + dir.offsetY*j, zz + dir.offsetZ*j);
				for(int i = 0; i < MagitekApi.horizontal.size(); i++) {
					if(block == MagitekApi.horizontal.get(i)) {
						canSearch = true;
						break;
					}
				}
				for(int i = 0; i < MagitekApi.vertical.size(); i++) {
					if(block == MagitekApi.vertical.get(i)) {
						canSearch = true;
						break;
					}
				}
				if(dir == ForgeDirection.DOWN || dir == ForgeDirection.UP) {
					canSearch = true;
				}
				if(!canSearch) {
					break;
				}
				if(this.worldObj.getTileEntity(xx + dir.offsetX*j, yy + 1 + dir.offsetY*j, zz + dir.offsetZ*j) != null) {
					TileEntity tile = this.worldObj.getTileEntity(xx + dir.offsetX*j, yy + 1 + dir.offsetY*j, zz + dir.offsetZ*j);
					if(tile instanceof IMachtStorage) {
						if(tile instanceof TileGeneratorBase) {
							canSearch = false;
							break;							
						}
						this.tiles.add(tile);
						canSearch = false;
						break;
					}
				}				
			}			
		}	
	}
	
	@Override
	protected void transferEnergy() {
		int amount = (Math.min(this.getMachtStored(), 100))/this.tiles.size();
		for(TileEntity tile : tiles) {
			if(tile != null) {
				if(tile instanceof TileMiniSpreader) {
					if(((IMachtStorage)tile).getMachtStored() > this.getMachtStored()) {
						continue;
					}
				}
				this.extractMacht(((IMachtStorage)tile).receiveMacht(Math.min(amount, this.getMachtStored())));
			}else{
				tiles.remove(tile);
			}
		}
	}
}
