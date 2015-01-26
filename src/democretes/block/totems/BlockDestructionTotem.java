package democretes.block.totems;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.Magitek;
import democretes.api.macht.IMachtStorage;
import democretes.block.MTBlocks;
import democretes.lib.Reference;
import democretes.utils.CreativeTabsMT;

public class BlockDestructionTotem extends Block {
	
	public BlockDestructionTotem() {
		super(Material.rock);
		setCreativeTab(Magitek.tabMT);
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + Reference.MOD_PREFIX + ".totem.destruction";
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		for(ForgeDirection dir : ForgeDirection.values()) {
			Block b = world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
			int meta = world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
			if(b != MTBlocks.totemDestruction && b != Blocks.piston && b != Blocks.piston_extension && b != Blocks.piston_head && b != Blocks.sticky_piston) {
				ArrayList<ItemStack> drops = b.getDrops(world, x, y, z, meta, 0);
				if(b == MTBlocks.generator && meta == 3) {
					int macht = ((IMachtStorage)world.getTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)).getMachtStored();
					ItemStack stack = new ItemStack(this, 1, 3);
					stack.stackTagCompound = new NBTTagCompound();
					stack.stackTagCompound.setInteger("Macht", macht);
					drops.add(stack);
				}
				world.setBlockToAir(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
				for(ItemStack stack : drops) {
					world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack));
				}
			}
		}
	}

}
