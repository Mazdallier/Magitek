package democretes.block.altar;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.helpers.AltarHelper;
import democretes.api.helpers.RitualHelper;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;
import democretes.lib.RenderIds;

public class BlockAltar extends BlockMTBase {	
	
	@Override
	public boolean onBlockActivated(World world, int x,	int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileAltar altar = (TileAltar)world.getTileEntity(x, y, z);
		if(altar.inventory != null) {
			if(player.isSneaking()) {
				if(!player.inventory.addItemStackToInventory(altar.inventory)) {
					ForgeDirection fd = ForgeDirection.getOrientation(side);
					world.spawnEntityInWorld(new EntityItem(world, x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, altar.inventory));
				}
				altar.inventory = null;
			}
		}else{
			if(player.getHeldItem() != null) {
				ItemStack stack = player.getHeldItem();
				if(!AltarHelper.recipeExists(stack) && !RitualHelper.recipeExists(stack)) {
					return false;
				}
				int size = player.isSneaking() ? stack.stackSize : 1;
				altar.inventory = stack.copy();
				altar.inventory.stackSize = size;
				player.inventory.decrStackSize(player.inventory.currentItem, size);
				return true;
			}else{
				if(player.isSneaking()) {
					altar.ritual = null;
					if(altar.dummies.size() > 0) {
						for(TileEntity dummy : altar.dummies) {
							world.removeTileEntity(dummy.xCoord, dummy.yCoord, dummy.zCoord);
							world.setBlockToAir(dummy.xCoord, dummy.yCoord, dummy.zCoord);
						}
					}	
					altar.dummies = new ArrayList();
				}
			}
		}		
		return false;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileAltar altar = (TileAltar)world.getTileEntity(x, y, z);
		if(altar.inventory != null) {
			Random random = new Random();
			float f = random.nextFloat() * 0.8F + 0.1F;
			float f1 = random.nextFloat() * 0.8F + 0.1F;
			EntityItem entityitem;

			for (float f2 = random.nextFloat() * 0.8F + 0.1F; altar.inventory .stackSize > 0; world.spawnEntityInWorld(entityitem)) {
				int k1 = random.nextInt(21) + 10;

				if (k1 > altar.inventory .stackSize) {
					k1 = altar.inventory .stackSize;
				}

				altar.inventory .stackSize -= k1;
				entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(altar.inventory .getItem(), k1, altar.inventory .getItemDamage()));
				float f3 = 0.05F;
				entityitem.motionX = (float)random.nextGaussian() * f3;
				entityitem.motionY = (float)random.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float)random.nextGaussian() * f3;

				if (altar.inventory .hasTagCompound()) {
					entityitem.getEntityItem().setTagCompound((NBTTagCompound)altar.inventory .getTagCompound().copy());
				}
			}
		}
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile." + Reference.MOD_PREFIX + ".altar";
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random r) {
		if(((TileAltar)world.getTileEntity(x, y, z)).inventory == null) {
			return;
		}
		for (int l = x - 2; l <= x + 2; ++l)  {
            for (int i = z - 2; i <= z + 2; ++i) {
                if (l > x - 2 && l < x + 2 && i == z - 1) {
                    i = z + 2;
                }
                if (r.nextInt(16) == 0) {
                    for (int j = y; j <= y + 1; ++j) {
                    	double xx = x + 0.5D;
                    	double yy = y + 1.0D;
                    	double zz = z + 0.5D;
                    	double mX = (double)((float)(l - x) + r.nextFloat()) - 0.5D;
                    	double mY = (double)((float)(j - y) - r.nextFloat() - 1.0F);
                    	double mZ = (double)((float)(i - z) + r.nextFloat()) - 0.5D;
                    	world.spawnParticle("portal", xx, yy, zz, mX, mY, mZ);
                    }
                }
            }
        }
	}
	
	public static IIcon circle[] = new IIcon[3];
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		circle[0] = ir.registerIcon(Reference.TEXTURE_PREFIX + "basic_circle");
		circle[1] = ir.registerIcon(Reference.TEXTURE_PREFIX + "advanced_circle");
		circle[2] = ir.registerIcon(Reference.TEXTURE_PREFIX + "complex_circle");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileAltar();
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
		return RenderIds.idALTAR;
	}

}
