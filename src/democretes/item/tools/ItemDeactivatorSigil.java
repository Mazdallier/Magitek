package democretes.item.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import democretes.api.macht.IMachtStorage;
import democretes.api.spells.SpellHelper;
import democretes.block.BlockMTBase;
import democretes.block.MTBlocks;
import democretes.block.generators.BlockGenerator;
import democretes.block.generators.disposable.BlockDisposableGenerator;
import democretes.block.generators.disposable.TileSingleGeneratorBase;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;

public class ItemDeactivatorSigil extends ItemMTBase {
	
	public ItemDeactivatorSigil() {
		setMaxStackSize(1);
		setUnlocalizedName(Reference.MOD_PREFIX + ".sigil.deactivator");
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return false;
		}
		Block block = world.getBlock(x, y, z);
		if(block instanceof BlockMTBase && block != MTBlocks.simple) {
			ItemStack blockStack = null;
			if(block instanceof BlockDisposableGenerator) {
				TileSingleGeneratorBase tile = (TileSingleGeneratorBase)world.getTileEntity(x, y, z);
				if(SpellHelper.getMacht(player) >= tile.maxEnergy - tile.energyRemaining) {
					SpellHelper.extractMacht(player, tile.maxEnergy - tile.energyRemaining);
					blockStack = new ItemStack(block, 1, world.getBlockMetadata(x, y, z));
				}
			}else if(block instanceof BlockGenerator && world.getBlockMetadata(x, y, z) == 3) {
				blockStack = new ItemStack(block, 1, 3);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("Macht", ((IMachtStorage)world.getTileEntity(x, y, z)).getMachtStored());
				blockStack.setTagCompound(nbt);
			}
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, blockStack));
			world.setBlockToAir(x, y, z);
			world.removeTileEntity(x, y, z);
			return true;
		}
		return false;
	}
	
	IIcon sigil;
	
	@Override
	public void registerIcons(IIconRegister ir) {
		sigil = ir.registerIcon(Reference.TEXTURE_PREFIX + "sigil/sigil_deactivate");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return sigil;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		if(stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
		}
		int macht = SpellHelper.getMacht(player);
		list.add("Your Macht: " + macht);
		stack.stackTagCompound.setInteger("Purity", macht);
	}

}
