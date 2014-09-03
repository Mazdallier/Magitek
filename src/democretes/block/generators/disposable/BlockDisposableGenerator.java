package democretes.block.generators.disposable;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;

public class BlockDisposableGenerator extends BlockMTBase {
	
	public BlockDisposableGenerator() {
		setHardness(25.0F);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 1; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world,	int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		return new ItemStack(block, 1, meta);
	}
	
	IIcon[] top = new IIcon[1];
	IIcon[] bot = new IIcon[1];
	IIcon[] sides = new IIcon[1];
	
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		for(int i = 0; i < top.length; i++) {
			top[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "disposable_" + i + "_top");
		}
		for(int i = 0; i < bot.length; i++) {
			bot[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "disposable_" + i + "_bot");
		}
		for(int i = 0; i < sides.length; i++) {
			sides[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "disposable_" + i + "_side");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 0) {
			return bot[meta];
		}
		if(side == 1) {
			return top[meta];
		}
		return sides[meta];
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta) {
		case 0:
			return new TileDetonationGenerator();
		}
		return null;
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random r) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 0) {
			for(int i = 0; i < 10; i++) {
				world.spawnParticle("smoke", x + 0.5D + r.nextDouble() - r.nextDouble(), y + 0.5D, z + 0.5D + r.nextDouble() - r.nextDouble(), 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta) {
		return false;
	}
}
