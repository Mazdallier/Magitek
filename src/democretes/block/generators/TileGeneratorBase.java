package democretes.block.generators;

import java.util.ArrayList;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.macht.IMachtStorage;
import democretes.block.TilePurityBase;
import democretes.utils.handlers.ConfigHandler;

public abstract class TileGeneratorBase extends TilePurityBase {
	
	ArrayList<TileEntity> tiles = new ArrayList();
	ForgeDirection[] valid = {ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.SOUTH};
	Block[] blocks = {
			Blocks.stonebrick,
			Blocks.nether_brick,
			Blocks.sandstone,
			Blocks.brick_block,
			Blocks.iron_block, 
			Blocks.gold_block, 
			Blocks.diamond_block,
			Blocks.redstone_block, 
			Blocks.coal_block, 
			Blocks.quartz_block,
			Blocks.emerald_block,
			Blocks.lapis_block};
	
	public TileGeneratorBase() {
		super(1000);
	}

	int count = 200;
	@Override
	public void updateEntity() {
		if(!this.worldObj.isRemote) {
			if(count == 200) {
				count = 0;
				searchForTiles();
			}
			count++;
			transferEnergy();				
		}
		if(this.canGenerate()) {
			if(!this.worldObj.isRemote) {
				this.macht.receiveMacht(this.getFuel());
			}else{
				renderWhenActive();
			}
		}
	}
	
	protected abstract boolean canGenerate();
	
	protected abstract int getFuel();
	
	protected abstract void renderWhenActive();
	
	void searchForTiles() {
		int xx = xCoord;
		int yy = yCoord - 1;
		int zz = zCoord;
		Block block = this.worldObj.getBlock(xx, yy, zz);
		boolean b = false;
		for(int i = 0; i < blocks.length; i++) {
			if(block == blocks[i]) {
				b = true;
				break;
			}
		}
		if(!b) {
			return;
		}
		for(ForgeDirection dir : valid) {
			for(int j = 1; j < ConfigHandler.range; j++) {
				if(block == this.worldObj.getBlock(xx + dir.offsetX*j, yy + dir.offsetY*j, zz + dir.offsetZ*j)) {
					if(this.worldObj.getBlock(xx + dir.offsetX*j, yy + 1 + dir.offsetY*j, zz + dir.offsetZ*j) != null) {
						TileEntity tile = this.worldObj.getTileEntity(xx + dir.offsetX*j, yy + 1 + dir.offsetY*j, zz + dir.offsetZ*j);
						if(tile instanceof IMachtStorage) {
							this.tiles.add(tile);
							break;
						}
					}
				}else{
					break;
				}
			}			
		}
	}
	
	void transferEnergy() {
		for(TileEntity tile : tiles) {
			if(tile != null) {
				this.extractMacht(((IMachtStorage)tile).receiveMacht(Math.min(this.getMachtStored(), 100)));
			}else{
				tiles.remove(tile);
			}
		}
	}
	
	@Override
	public int receiveMacht(int amount) {
		return 0;
	}
	
	
}
