package democretes.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.Magitek;
import democretes.api.block.BlockInfo;
import democretes.api.block.IBlockDebug;
import democretes.api.macht.IMachtStorage;
import democretes.api.purity.IPurityHandler;

public class BlockMTBase extends BlockContainer implements IBlockDebug {

	public BlockMTBase() {
		super(Material.iron);
		setCreativeTab(Magitek.tabMT);
		setHardness(2F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public BlockInfo getInfo(EntityPlayer player, int x, int y, int z) {
		BlockInfo info = new BlockInfo(player, x, y, z);
		TileEntity tile = player.worldObj.getTileEntity(x, y, z);
		if(tile instanceof IPurityHandler) {
			info.setPurity(((IPurityHandler)tile).getPurity());
		}
		if(tile instanceof IMachtStorage) {
			info.setMacht(((IMachtStorage)tile).getMachtStored());
		}
		return info;
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof IInventory) {
			IInventory inv = (IInventory)tile;
			Random r = new Random();
			for (int i1 = 0; i1 < inv.getSizeInventory(); ++i1) {
                ItemStack itemstack = inv.getStackInSlot(i1);

                if (itemstack != null) {
                    float f = r.nextFloat() * 0.8F + 0.1F;
                    float f1 = r.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = r.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
                        int j1 = r.nextInt(21) + 10;
                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)r.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)r.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)r.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())  {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	

}
