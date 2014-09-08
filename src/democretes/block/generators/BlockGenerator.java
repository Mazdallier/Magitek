package democretes.block.generators;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.helpers.AltarHelper;
import democretes.api.helpers.RunicHelper;
import democretes.block.BlockMTBase;
import democretes.block.MTBlocks;
import democretes.block.altar.TileAltar;
import democretes.block.dummy.BlockSubTerraDummy;
import democretes.block.generators.disposable.TileDetonationGenerator;
import democretes.lib.Reference;
import democretes.lib.RenderIds;

public class BlockGenerator extends BlockMTBase {

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 7; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if(!world.isRemote && world.getBlock(x, y+1, z) != MTBlocks.terraDummy && world.getBlockMetadata(x, y, z) == 1) {
			this.dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, 1));
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world,	int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		return new ItemStack(block, 1, meta);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		if(meta == 0) {
			setBlockBounds(0F, 0F, 0F, 1F, 0.65F, 1F);			
		}else if(meta == 1) {
			setBlockBounds(0F, 0F, 0F, 1F, 2F, 1F);
		}else if(meta == 2) {
			setBlockBounds(0.15F, 0.0F, 0.15F, 0.85F, 0.85F, 0.85F);			
		}else if(meta == 3) {
			setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
		}else{
			setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		}
	}
	
	
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(world.getBlockMetadata(x, y, z) == 4) {
			if(world.getTileEntity(x, y, z) instanceof TileRunicGenerator) {
				TileRunicGenerator generator = (TileRunicGenerator)world.getTileEntity(x, y, z);
				if(generator.inventory != null) {
					if(player.isSneaking()) {
						if(!player.inventory.addItemStackToInventory(generator.inventory)) {
							ForgeDirection fd = ForgeDirection.getOrientation(side);
							world.spawnEntityInWorld(new EntityItem(world, x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, generator.inventory));
						}
						generator.inventory = null;
					}else{
						if(player.getHeldItem() == null) {
							return false;
						}
						if(player.getHeldItem().getItem() == generator.inventory.getItem() && player.getHeldItem().getItemDamage() == generator.inventory.getItemDamage()) {
							generator.inventory.stackSize++;
							player.inventory.decrStackSize(player.inventory.currentItem, 1);
						}
					}
				}else{
					if(player.getHeldItem() != null) {
						ItemStack stack = player.getHeldItem();
						if(!RunicHelper.recipeExistsFromOutput(stack)) {
							return false;
						}
						int size = player.isSneaking() ? stack.stackSize : 1;
						generator.inventory = stack.copy();
						generator.inventory.stackSize = size;
						player.inventory.decrStackSize(player.inventory.currentItem, size);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random r) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileThermalGenerator) {
			if(((TileThermalGenerator)tile).tank.getFluidAmount() > 0) {
		            float f = (float)x + 0.5F;
		            float f1 = (float)y + 0.0F  +0.2F + r.nextFloat() * 6.0F / 16.0F;
		            float f2 = (float)z + 0.5F;
		            float f3 = 0.52F;
		            float f4 = r.nextFloat() * 0.6F - 0.3F;
		            world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3 + 0.2F), 0.0D, 0.0D, 0.0D);
		            world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3 - 0.2F), 0.0D, 0.0D, 0.0D);
		            
		        }			
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if(world.getBlockMetadata(x, y, z) == 1 && !world.isAirBlock(x, y + 1, z)) {
			return false;
		}		
		return super.canPlaceBlockAt(world, x, y, z);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if(stack.getItemDamage() == 1) {
			world.setBlock(x, y+1, z, MTBlocks.terraDummy);
			((BlockSubTerraDummy)world.getBlock(x, y+1, z)).block = this;
			world.getBlock(x, y+1, z).onPostBlockPlaced(world, x, y, z, stack.getItemDamage());
		}
	}
		
	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.stone.getIcon(0, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta) {
		case 0:
			return new TileSolarGenerator();
		case 1:
			return new TileSubTerraGenerator();
		case 2:
			return new TilePurityGenerator();
		case 3:
			return new TileSpreader();
		case 4:
			return new TileRunicGenerator();
		case 5:
			return new TileThermalGenerator();
		case 6:
			return new TileDestructionGenerator();
		}
		return null;
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
	public int getRenderType() {
		return RenderIds.idGENERATOR;
	}
	
	
}
