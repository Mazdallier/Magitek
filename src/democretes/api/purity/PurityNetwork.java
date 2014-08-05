package democretes.api.purity;

import net.minecraft.nbt.NBTTagCompound;

public class PurityNetwork extends net.minecraft.world.WorldSavedData {
	
	    public int purity;

	    public PurityNetwork(String string){
	        super(string);
	        purity = 0;
	    }

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
			this.purity = nbt.getInteger("Purity");
		}

		@Override
		public void writeToNBT(NBTTagCompound nbt) {
			nbt.setInteger("Purity", this.purity);
		}

}
