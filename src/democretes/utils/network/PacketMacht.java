package democretes.utils.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import democretes.api.macht.IMachtStorage;
import democretes.block.TilePurityBase;
import democretes.utils.helper.ClientHelper;

public class PacketMacht extends PacketChoords {
	
	int macht;
	boolean receive;
	
	public PacketMacht() {}

	PacketMacht(int x, int y, int z, int amount, boolean receive) {
		super(x, y, z);
		this.macht = amount;
		this.receive = receive;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.macht = buf.readInt();
		this.receive = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeInt(this.macht);
		buf.writeBoolean(this.receive);
	}
	
	@Override
	public IMessage onMessage(PacketChoords message, MessageContext ctx) {
		PacketMacht m = (PacketMacht)message;
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		EntityPlayer player = side == Side.CLIENT ? FMLClientHandler.instance().getClient().thePlayer : ctx.getServerHandler().playerEntity;
		World world = player.worldObj;
        TileEntity tile = world.getTileEntity(message.x, message.y, message.z); 		
        if(tile instanceof IMachtStorage) {
        	if(!this.receive) {
            	((IMachtStorage)tile).receiveMacht(this.macht);      
        	}else{
            	((IMachtStorage)tile).extractMacht(this.macht); 
        	}
        }
		return null;
	}

}
