package democretes.item.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import democretes.api.spells.ISpellActivation;
import democretes.entity.EntityWindBlast;

public class SpellWindBlast implements ISpellActivation {

	@Override
	public void activateSpell(EntityPlayer player) {	
		if(player.worldObj.isRemote) {
			return;
		}
		Vec3 vec = player.getLookVec();	
		player.worldObj.spawnEntityInWorld(new EntityWindBlast(player.worldObj, player, vec.xCoord*10, vec.yCoord*10, vec.zCoord*10));

	}

}
