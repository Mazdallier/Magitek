package democretes.block.altar;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;
import democretes.utils.crafting.AltarRecipes;

public class BlockAltar extends BlockMTBase {
	
	@Override
	public boolean onBlockActivated(World world, int x,	int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
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
					if(AltarRecipes.getResult(stack) == null) {
						return false;
					}
					altar.inventory = stack.copy();
					player.inventory.decrStackSize(player.inventory.currentItem, stack.stackSize);
				}
			}
		}
		return false;
	}
	
	@Override
	public String getUnlocalizedName() {
		return Reference.MOD_PREFIX + ".altar";
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random r) {
		for (int l = x - 2; l <= x + 2; ++l)  {
            for (int i = z - 2; i <= z + 2; ++i) {
                if (l > x - 2 && l < x + 2 && i == z - 1) {
                    i = z + 2;
                }
                if (r.nextInt(16) == 0) {
                    for (int j = y; j <= y + 1; ++j) {
                       world.spawnParticle("portal", (double)x + 0.5D, (double)y + 1.0D, (double)z + 0.5D, (double)((float)(l - x) + r.nextFloat()) - 0.5D, (double)((float)(j - y) - r.nextFloat() - 1.0F), (double)((float)(i - z) + r.nextFloat()) - 0.5D);
                    }
                }
            }
        }
	}
	
	public static IIcon basic;
	public static IIcon advanced;
	public static IIcon complex;
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		basic = ir.registerIcon(Reference.TEXTURE_PREFIX + "basic_circle");
		advanced = ir.registerIcon(Reference.TEXTURE_PREFIX + "advanced_circle");
		complex = ir.registerIcon(Reference.TEXTURE_PREFIX + "complex_circle");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileAltar();
	}

}
