package democretes.utils.helper;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;

public class ClientHelper {
	
	public static EntityPlayer getPlayer() {
        return FMLClientHandler.instance().getClient().thePlayer;
    }
	
	public static void updateRender(int x, int y, int z) {
        getPlayer().worldObj.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
    }

}
