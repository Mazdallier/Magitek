package democretes.api.spells;

import democretes.api.purity.PurityNetwork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class SpellNetwork extends net.minecraft.world.WorldSavedData {
	
    public Spell spell;

    public SpellNetwork(String string){
        super(string);
    }

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.spell = SpellHelper.getSpell(nbt.getString("Spell"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setString("Spell", this.spell.getName());
	}
	
	
}
