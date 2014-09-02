package democretes.item.tools;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import democretes.api.purity.IPurityHandler;
import democretes.api.spells.SpellHelper;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;
import democretes.utils.handlers.ConfigHandler;

public class ItemPuritySigil extends ItemMTBase {
	
	public ItemPuritySigil() {
		setMaxStackSize(1);
		setUnlocalizedName(Reference.MOD_PREFIX + ".sigil.purity");
	}
		
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return false;
		}
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof IPurityHandler) {
			IPurityHandler ptile = (IPurityHandler)tile;
			int amount = ConfigHandler.syphonAmount;
			if(player.isSneaking()) {
				int purity = SpellHelper.getPlayerPurity(player);
				amount = Math.min(Math.abs(purity), ConfigHandler.syphonAmount);
				if(purity < 0) {
					ptile.decreasePurity(amount);
					SpellHelper.increasePlayerPurity(player, amount);						
				}else if(purity > 0) {
					ptile.increasePurity(amount);
					SpellHelper.decreasePlayerPurity(player, amount);
				}
				return false;
			}else{ 
				amount = Math.min(Math.abs(ptile.getPurity()), ConfigHandler.syphonAmount);
				if(ptile.getPurity() < 0) {
					ptile.increasePurity(amount);
					SpellHelper.decreasePlayerPurity(player, amount);
				}else if(ptile.getPurity() > 0) {
					ptile.decreasePurity(amount);
					SpellHelper.increasePlayerPurity(player, amount);				
				}
			}
		}
		return false;
	}
	
	
	IIcon syphon;
	@Override
	public void registerIcons(IIconRegister ir) {
		syphon = ir.registerIcon(Reference.TEXTURE_PREFIX + "sigil/sigil_purity");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return syphon;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int meta, boolean b) {
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			int purity = SpellHelper.getPlayerPurity(player);
			if(stack.stackTagCompound == null) {
				stack.stackTagCompound = new NBTTagCompound();
			}
			stack.stackTagCompound.setInteger("Purity", SpellHelper.getPlayerPurity(player));
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		stack.stackTagCompound = new NBTTagCompound();
		int purity = SpellHelper.getPlayerPurity(player);
		list.add("Your Purity: " + purity);
		if(purity <= -1000) {
			list.add("You soul has become dark.");
		}else if(purity >= 1000) {
			list.add("You soul has been lightened.");
		}
		stack.stackTagCompound.setInteger("Purity", purity);
	}

}
