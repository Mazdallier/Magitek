package democretes.utils.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import democretes.lib.Reference;

public class PacketHandler {

    private static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID); 
    private static int id;


    public static void registerPacket(Class clazz, Side side) {
    	INSTANCE.registerMessage(clazz, clazz, id++, side);
    }
    
    public static void init() {
    }
    
    public static void sendToClient(IMessage packet, EntityPlayerMP player) {
    	INSTANCE.sendTo(packet, player);;
    }

    public static void sendAround(IMessage packet, int dim, double x, double y, double z) {
    	INSTANCE.sendToAllAround(packet, new NetworkRegistry.TargetPoint(dim, x, y, z, 176));
    }

    public static void sendToServer(IMessage packet) {
    	INSTANCE.sendToServer(packet);
    }

    public static void sendAround(IMessage packet, TileEntity tile) {
        sendAround(packet, tile.getWorldObj().provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord);
    }
    

}
