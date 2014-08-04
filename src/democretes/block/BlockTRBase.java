package democretes.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.Electromancy;

public class BlockTRBase extends BlockContainer {

	public BlockTRBase() {
		super(Material.iron);
		setCreativeTab(Electromancy.tabEM);
		setHardness(2F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	

}
