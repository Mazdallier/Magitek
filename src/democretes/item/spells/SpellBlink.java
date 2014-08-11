package democretes.item.spells;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import democretes.api.spells.ISpellActivation;

public class SpellBlink implements ISpellActivation {

	@Override
	public void activateSpell(EntityPlayer player) {
		Vec3 vec = player.getLookVec();
		double x = player.posX;
		double y = player.posY;
		double z = player.posZ;
		for(int i = 1; i < 20; i++) {
			if(!player.worldObj.isAirBlock((int)x, (int)y, (int)z)) {
				i -= 1;
				x = player.posX + i*vec.xCoord;
				y = player.posY + 0.75 + i*vec.yCoord;
				z = player.posZ + i*vec.zCoord;
				break;
			}
			x = player.posX + i*vec.xCoord;
			y = player.posY + i*vec.yCoord;
			z = player.posZ + i*vec.zCoord;
		}
		player.setPosition(x, y, z);
	}

}
