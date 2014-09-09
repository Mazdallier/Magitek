package democretes.item.spells;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import democretes.api.spells.Spell;
import democretes.api.spells.SpellHelper;
import democretes.item.ItemMTBase;
import democretes.item.MTItems;
import democretes.utils.helper.StringHelper;
import democretes.utils.network.PacketHandler;
import democretes.utils.network.PacketSpell;

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
		if(!player.isSneaking()) {
			PacketHandler.sendToServer(new PacketSpell((int)player.posX, (int)player.posY, (int)player.posZ));
		}else{
			SpellHelper.bindSpell(player, spell);
			player.addChatComponentMessage(new ChatComponentText(StringHelper.localize("magitek.spell.bound")+ " " + spell.getName() + "."));
		}
		return super.onItemRightClick(stack, world, player);
	}
	
	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack) {
		return new String("spell." + stack.stackTagCompound.getString("SpellName"));
	}
	
	IIcon icons[] = new IIcon[Spell.spells.size()];	
	@Override
	public void registerIcons(IIconRegister ir) {
		for(int i = 0; i < icons.length; i++) {
			ResourceLocation loc = SpellHelper.getSpell((String)Spell.spells.keySet().toArray()[i]).getTexture();
			icons[i] = ir.registerIcon(loc.getResourceDomain() + loc.getResourcePath());
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int meta, int pass) {
		if(pass == 1) {
			return icons[meta];
		}
		return MTItems.material.getIconFromDamage(2);
	}
	
	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	@Override
	public int getRenderPasses(int metadata) {
		return 2;
	}

}
