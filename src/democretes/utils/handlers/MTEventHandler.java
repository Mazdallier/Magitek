package democretes.utils.handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import democretes.utils.crafting.AltarRecipes;

public class MTEventHandler {
	
	@SubscribeEvent
	public void receiveMessages(FMLInterModComms.IMCEvent event) {
		for(final FMLInterModComms.IMCMessage message : event.getMessages()) {
			if(message.key.equalsIgnoreCase("altarrecipe")) {
				NBTTagCompound nbt = message.getNBTValue();
				ItemStack input = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("input"));
				ItemStack output = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("output"));
				AltarRecipes.addRecipe(input, output, nbt.getInteger("macht"));
			}
		}
	}
	

}
