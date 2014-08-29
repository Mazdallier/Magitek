package democretes.block.generators;

import democretes.api.macht.IMachtStorage;
import democretes.block.dummy.TileSubTerraDummy;
import democretes.utils.handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSpreader extends TileGeneratorBase {
	
	public TileSpreader() {
		super(10000);
	}
	
	@Override
	protected boolean canGenerate() {
		return false;
	}

	@Override
	protected int getFuel() {
		return 0;
	}

	@Override
	protected void renderWhenActive() {}
	
	@Override
	void searchForTiles() {
		this.tiles.clear();
		int xx = xCoord;
		int yy = yCoord - 1;
		int zz = zCoord;
		Block block = this.worldObj.getBlock(xx, yy, zz);
		boolean canSearch = false;
		for(int i = 0; i < hBlocks.length; i++) {
			if(block == hBlocks[i]) {
				canSearch = true;
				break;
			}
		}
		for(int i = 0; i < vBlocks.length; i++) {
			if(block == vBlocks[i]) {
				canSearch = true;
				break;
			}
		}
		if(!canSearch) {
			return;
		}
		canSearch = false;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			for(int j = 1; j < ConfigHandler.range; j++) {
				block = this.worldObj.getBlock(xx + dir.offsetX*j, yy + dir.offsetY*j, zz + dir.offsetZ*j);
				for(int i = 0; i < hBlocks.length; i++) {
					if(block == hBlocks[i]) {
						canSearch = true;
						break;
					}
				}
				for(int i = 0; i < vBlocks.length; i++) {
					if(block == vBlocks[i]) {
						canSearch = true;
						break;
					}
				}
				if(!canSearch) {
					break;
				}
				if(this.worldObj.getTileEntity(xx + dir.offsetX*j, yy + 1 + dir.offsetY*j, zz + dir.offsetZ*j) != null) {
					TileEntity tile = this.worldObj.getTileEntity(xx + dir.offsetX*j, yy + 1 + dir.offsetY*j, zz + dir.offsetZ*j);
					if(tile instanceof IMachtStorage) {
						if(tile instanceof TileGeneratorBase) {
							if(tile instanceof TileSpreader == false) {
								break;
							}
						}
						this.tiles.add(tile);
						break;
					}
				}				
			}			
		}	
	}
	

}
