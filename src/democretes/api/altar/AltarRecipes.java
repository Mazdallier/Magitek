package democretes.api.altar;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.event.FMLInterModComms;

public class AltarRecipes {

	public static void addAltarRecipe(ItemStack input, ItemStack output, int macht) {
		if (input == null || output == null) {
			return;
		}
		NBTTagCompound nbt = new NBTTagCompound();

		nbt.setInteger("macht", macht);
		nbt.setTag("input", new NBTTagCompound());
		nbt.setTag("output", new NBTTagCompound());

		input.writeToNBT(nbt.getCompoundTag("input"));
		output.writeToNBT(nbt.getCompoundTag("output"));
		FMLInterModComms.sendMessage("Magitek", "AltarRecipe", nbt);
	}
	
	public static void addRitualRecipe(ItemStack catalyst, RitualType type, ItemStack[] input, ItemStack output, int macht) {
		if(catalyst == null || input == null || output == null || type == null) {
			return;
		}
		if(type.size != input.length) {
			return;
		}
		NBTTagCompound nbt = new NBTTagCompound();

		nbt.setInteger("ritual", type.size);
		nbt.setTag("catalyst", new NBTTagCompound());
		nbt.setInteger("macht", macht);
		for(int i = 0;i < input.length; i++) {
			nbt.setTag("input" + i, new NBTTagCompound());
		}
		nbt.setTag("output", new NBTTagCompound());

		for(int i = 0; i < input.length; i++) {
			input[i].writeToNBT(nbt.getCompoundTag("input" + i));
		}
		output.writeToNBT(nbt.getCompoundTag("output"));
		catalyst.writeToNBT(nbt.getCompoundTag("catalyst"));
		FMLInterModComms.sendMessage("Magitek", "RitualRecipe", nbt);
	}
}
