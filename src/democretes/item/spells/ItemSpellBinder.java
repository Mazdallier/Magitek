package democretes.item.spells;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import democretes.api.spells.Spell;
import democretes.api.spells.SpellHelper;
import democretes.item.ItemMTBase;

public class ItemSpellBinder extends ItemMTBase {
	
	public ItemSpellBinder() {
		setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < Spell.spells.size(); i++) {
			ItemStack stack = new ItemStack(item, 1, i);
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setString("SpellName", (String)Spell.spells.keySet().toArray()[i]);
			list.add(stack);
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(world.isRemote) {
			return super.onItemRightClick(stack, world, player);
		}
		Spell spell = SpellHelper.getSpell((String)Spell.spells.keySet().toArray()[stack.getItemDamage()]);
		SpellHelper.bindSpell(player, spell);
		player.addChatComponentMessage(new ChatComponentText("You have been bound with " + spell.getName() + "."));
		return super.onItemRightClick(stack, world, player);
	}
	
	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack) {
		return new String("spell." + stack.stackTagCompound.getString("SpellName"));
	}

}
