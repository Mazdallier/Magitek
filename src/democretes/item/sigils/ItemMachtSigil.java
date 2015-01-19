package democretes.item.sigils;

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

public class ItemMachtSigil extends ItemMTBase{
	
	public ItemMachtSigil() {
		setMaxStackSize(1);
		setUnlocalizedName(Reference.MOD_PREFIX + ".sigil.macht");
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
			if(player.isSneaking()) {
				SpellHelper.extractMacht(player, mtile.receiveMacht(amount));				
			}else{
				SpellHelper.receiveMacht(player, mtile.extractMacht(amount));
			}
		}
		return false;
	}
	
	
	IIcon syphon;
	@Override
	public void registerIcons(IIconRegister ir) {
		syphon = ir.registerIcon(Reference.TEXTURE_PREFIX + "sigil/sigil_energy");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return syphon;
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
