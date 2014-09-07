package democretes.block.generators;

import java.util.ArrayList;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.Magitek;
import democretes.api.macht.IMachtStorage;
import democretes.block.TileEnergyTransferer;
import democretes.block.TilePurityBase;
import democretes.block.dummy.TileSubTerraDummy;
import democretes.utils.handlers.ConfigHandler;
import democretes.utils.helper.DirectionHelper;

public abstract class TileGeneratorBase extends TileEnergyTransferer {
	
	
	public TileGeneratorBase() {
		super(1000);
	}
	
	public TileGeneratorBase(int capacity) {
		super(capacity);
	}
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)) {
			return;
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
	
	
}
