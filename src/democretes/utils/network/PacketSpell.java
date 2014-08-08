package democretes.utils.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import democretes.api.spells.SpellHelper;

public class PacketSpell extends PacketChoords {
	
	boolean b;
	
	public PacketSpell() {}

	public PacketSpell(int x, int y, int z, boolean b) {
		super(x, y, z);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		this.b = buf.readBoolean();
	}
	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		buf.writeBoolean(b);
	}
	
	@Override
	public IMessage onMessage(PacketChoords message, MessageContext ctx) {
		PacketSpell m = (PacketSpell)message;
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		EntityPlayer player = side == Side.CLIENT ? FMLClientHandler.instance().getClient().thePlayer : ctx.getServerHandler().playerEntity;
		World world = player.worldObj;
		if(SpellHelper.getNetwork(player).spell != null && !world.isRemote) {
			SpellHelper.getNetwork(player).spell.getActivator().activateSpell(player);
		}
		return null;
	}

}
