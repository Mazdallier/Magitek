package democretes.block.generators;

import java.util.ArrayList;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.macht.IMachtStorage;
import democretes.block.TilePurityBase;
import democretes.block.dummy.TileSubTerraDummy;
import democretes.utils.handlers.ConfigHandler;

public abstract class TileGeneratorBase extends TilePurityBase {
	
	ArrayList<TileEntity> tiles = new ArrayList();
	ForgeDirection[] horizontal = {ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.SOUTH};
	ForgeDirection[] vertical = {ForgeDirection.UP, ForgeDirection.DOWN};
	Block[] hBlocks = {
			Blocks.stonebrick,
			Blocks.nether_brick,
			Blocks.sandstone,
			Blocks.brick_block,
			Blocks.iron_block, 
			Blocks.gold_block, 
			Blocks.redstone_block, 
			Blocks.coal_block,
			Blocks.emerald_block,
			Blocks.diamond_block };
	Block[] vBlocks = {
			Blocks.quartz_block,
			Blocks.emerald_block,
			Blocks.lapis_block,
			Blocks.diamond_block};
	
	public TileGeneratorBase() {
		super(1000);
	}

	int count = 40;
	@Override
	public void updateEntity() {
		if(!this.worldObj.isRemote) {
			if(count >= 40) {
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
		this.tiles.clear();
		int xx = xCoord;
		int yy = yCoord - 1;
		int zz = zCoord;
		Block block = this.worldObj.getBlock(xx, yy, zz);
		boolean horizon = false;
		boolean vertical = false;
		for(int i = 0; i < hBlocks.length; i++) {
			if(block == hBlocks[i]) {
				horizon = true;
				break;
			}
		}
		for(int i = 0; i < vBlocks.length; i++) {
			if(block == vBlocks[i]) {
				vertical = true;
				break;
			}
		}
		if(horizon) {
			for(ForgeDirection dir : horizontal) {
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
		if(vertical) {
			for(ForgeDirection dir : this.vertical) {
				for(int j = 1; j < ConfigHandler.range; j++) {
					if(this.worldObj.getBlock(xx, yy + 1 + dir.offsetY*j, zz) != null) {
						TileEntity tile = this.worldObj.getTileEntity(xx, yy + 1 + dir.offsetY*j, zz);
						if(tile instanceof IMachtStorage && tile instanceof TileSubTerraDummy == false) {
							this.tiles.add(tile);
							break;
						}
					}
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
	
	
}
