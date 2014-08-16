package democretes.block.dummy;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;
import democretes.utils.crafting.AltarRecipes;

public class BlockAltarDummy extends BlockMTBase {
	
	public BlockAltarDummy() {
		setLightOpacity(10000);
		setLightLevel(15F);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileAltarDummy();
	}
	
	IIcon glow;
	IIcon blank;
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		glow = ir.registerIcon(Reference.TEXTURE_PREFIX + "glow");
		blank = ir.registerIcon(Reference.TEXTURE_PREFIX + "blank");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side > 1) {
			return glow;
		}
		return blank;
	}
	
	@Override
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	} 
	 
	@Override
	public boolean renderAsNormalBlock() {
	    return false;
	}
	 
	@Override
	public int quantityDropped(Random p_149745_1_) {
	    return 0;
	}

}
