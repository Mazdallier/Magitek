package democretes.block.machines;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.helpers.ChipCrafterHelper;
import democretes.block.BlockMTBase;
import democretes.block.MTBlocks;
import democretes.block.dummy.BlockSubTerraDummy;
import democretes.block.generators.TileBounceGenerator;
import democretes.block.generators.TileRunicGenerator;
import democretes.item.MTItems;
import democretes.lib.Reference;

public class BlockMachine extends BlockMTBase {

	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < sides.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world,	int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		return new ItemStack(block, 1, meta);
	}
	
	int facing;
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if(stack.getItemDamageForDisplay() == 2) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile instanceof TileBounceGenerator) {
				((TileMachtFurnace)tile).facing = this.facing;
			}
		}
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta){
		if(meta == 2) {
			this.facing = ForgeDirection.OPPOSITES[side];			
		}
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(world.getBlockMetadata(x, y, z) == 0) {
			if(world.getTileEntity(x, y, z) instanceof TileChipCrafter) {
				TileChipCrafter construct = (TileChipCrafter)world.getTileEntity(x, y, z);
				if(player.getHeldItem() != null) {
					int i = player.getHeldItem().getItem() == MTItems.ingot && player.getHeldItem().getItemDamage() == 0? 0 : 1;
					ItemStack stack = player.getHeldItem();
					if(i == 1) {
						if(!ChipCrafterHelper.recipeExists(stack)) {
							return false;
						}
					}
					int size = player.isSneaking() ? stack.stackSize : 1;
					if(construct.inventory[i] == null) {
						construct.inventory[i] = stack.copy();	
						construct.inventory[i].stackSize = size;
					}else{
						construct.inventory[i].stackSize += size;
						if(construct.inventory[i].stackSize > construct.inventory[i].getMaxStackSize()) {
							size = construct.inventory[i].stackSize - construct.inventory[i].getMaxStackSize();
							construct.inventory[i].stackSize = construct.inventory[i].getMaxStackSize();
						}
					}
					player.inventory.decrStackSize(player.inventory.currentItem, size);
					return true;
				}else if((construct.inventory[0] != null || construct.inventory[1] != null) && player.isSneaking()) {
						int i = construct.inventory[0] != null ? 0 : construct.inventory[1] != null ? 1 : -1;
						if(i == -1) {
							return false;
						}
						if(!player.inventory.addItemStackToInventory(construct.inventory[i])) {
							ForgeDirection fd = ForgeDirection.getOrientation(side);
							world.spawnEntityInWorld(new EntityItem(world, x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, construct.inventory[i]));
						}
						construct.inventory[i] = null;
						
				}
				return true;
			}
		}
		if(world.getBlockMetadata(x, y, z) == 2) {
			TileReconstructor tile = (TileReconstructor)world.getTileEntity(x, y, z);
			if(tile.inventory != null) {
				if(player.isSneaking()) {
					if(!player.inventory.addItemStackToInventory(tile.inventory)) {
						ForgeDirection fd = ForgeDirection.getOrientation(side);
						world.spawnEntityInWorld(new EntityItem(world, x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, tile.inventory));
					}
					tile.inventory = null;
				}
			}
		}
		return false;
	}
	
	
	IIcon[] top = new IIcon[3];
	IIcon[] bot = new IIcon[3];
	IIcon[] sides = new IIcon[3];	
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		for(int i = 0; i < top.length; i++) {
			top[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "machine/machine_" + i + "_top");
		}
		for(int i = 0; i < bot.length; i++) {
			bot[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "machine/machine_" + i + "_bot");
		}
		for(int i = 0; i < sides.length; i++) {
			sides[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "machine/machine_" + i + "_side");
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
			return new TileChipCrafter();
		case 1:
			return new TileMachtFurnace();
		case 2:
			return new TileReconstructor();
		}
		return null;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

}
