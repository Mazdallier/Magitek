package democretes.api.spells;

import net.minecraft.nbt.NBTTagCompound;

public class SpellNetwork extends net.minecraft.world.WorldSavedData {
	
    public Spell spell;
    public int purity;
    public int macht;
    public int max = 10000;

    public SpellNetwork(String string){
        super(string);
    }

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		String name = nbt.getString("Spell");
		this.spell = SpellHelper.getSpell(name);
		this.purity = nbt.getInteger("Purity");
		this.macht = nbt.getInteger("Macht");
		this.max = nbt.getInteger("Max");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		if(this.spell != null) {
			nbt.setString("Spell", this.spell.getName());
		}		
		nbt.setInteger("Purity", this.purity);
		nbt.setInteger("Macht", this.macht);
		nbt.setInteger("Max", this.max);
	}
	
	
}
