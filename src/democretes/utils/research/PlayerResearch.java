package democretes.utils.research;

import java.util.LinkedList;
import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerResearch  extends net.minecraft.world.WorldSavedData {
	
	LinkedList<Blueprint> researched = new LinkedList();		
	
	PlayerResearch(UUID id) {
		super(id.toString());
	}	
	
	PlayerResearch(String name) {
		super(name);
	}	

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		for(int i = 0; i < nbt.getInteger("Size"); i++) {
			researched.set(i, BlueprintHelper.getBlueprint(nbt.getString("Blueprint" + i)));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		for(int i = 0; i < researched.size(); i++) {
			nbt.setString("Blueprint" + i, researched.get(i).name);
		}
		nbt.setInteger("Size", researched.size());
	}
	
	public void addBlueprint(Blueprint b) {
		if(b == null) {
			return;
		}
		this.researched.add(b);
		this.markDirty();
	}
	
}
