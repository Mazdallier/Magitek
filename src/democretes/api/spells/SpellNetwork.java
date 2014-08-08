package democretes.api.spells;

import net.minecraft.nbt.NBTTagCompound;

public class SpellNetwork extends net.minecraft.world.WorldSavedData {
	
    public Spell spell;
    public int purity;
    public int macht;

    public SpellNetwork(String string){
        super(string);
    }

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		String name = nbt.getString("Spell");
		this.spell = SpellHelper.getSpell(name);
		this.purity = nbt.getInteger("Purity");
		this.macht = nbt.getInteger("Macht");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setString("Spell", this.spell.getName());
		nbt.setInteger("Purity", this.purity);
		nbt.setInteger("Macht", this.macht);
	}
	
	
}
