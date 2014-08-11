package democretes.item.tools;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import democretes.api.macht.IMachtHandler;
import democretes.api.purity.IPurityHandler;
import democretes.api.spells.SpellHelper;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;
import democretes.utils.handlers.ConfigHandler;

public class ItemMachtRune extends ItemMTBase{
	
	public ItemMachtRune() {
		setMaxStackSize(1);
		setUnlocalizedName(Reference.MOD_PREFIX + ".macht");
	}
		
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return false;
		}
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof IMachtHandler) {
			IMachtHandler mtile = (IMachtHandler)tile;
			int amount = ConfigHandler.syphonAmount;
			SpellHelper.receiveMacht(player, mtile.extractMacht(amount));
		}
		return false;
	}
	
	
	IIcon syphon;
	@Override
	public void registerIcons(IIconRegister ir) {
		syphon = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune_energy");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return syphon;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int meta, boolean b) {
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			int macht = SpellHelper.getMacht(player);
			if(stack.stackTagCompound == null) {
				stack.stackTagCompound = new NBTTagCompound();
			}
			stack.stackTagCompound.setInteger("Macht", SpellHelper.getMacht(player));
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		stack.stackTagCompound = new NBTTagCompound();
		int macht = SpellHelper.getMacht(player);
		list.add("Your Macht: " + macht);
		stack.stackTagCompound.setInteger("Purity", macht);
	}

}
