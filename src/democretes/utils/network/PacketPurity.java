package democretes.utils.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import democretes.api.purity.IPurityHandler;
import democretes.api.purity.Purity;
import democretes.block.TilePurityBase;
import democretes.utils.helper.ClientHelper;

public class PacketPurity extends PacketChoords {
	
	int purity;
	
	public PacketPurity() {}

	PacketPurity(int x, int y, int z, int amount) {
		super(x, y, z);
		this.purity = amount;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.purity = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeInt(this.purity);
	}
	
	@Override
	public IMessage onMessage(PacketChoords message, MessageContext ctx) {
		PacketPurity m = (PacketPurity)message;
        TileEntity tile = ClientHelper.getPlayer().worldObj.getTileEntity(message.x, message.y, message.z);
        if(tile instanceof TilePurityBase) {
        	((TilePurityBase)tile).setPurity(m.purity);
        }        
        ClientHelper.updateRender(m.x, m.y, m.z);
		return null;
	}
}
