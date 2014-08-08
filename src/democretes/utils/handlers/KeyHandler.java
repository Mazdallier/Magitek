package democretes.utils.handlers;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.utils.network.PacketHandler;
import democretes.utils.network.PacketSpell;

public class KeyHandler {
	
	public KeyBinding key = new KeyBinding("Passive Spell", Keyboard.KEY_R, "key.categories.inventory");
	
	public KeyHandler() {
		 ClientRegistry.registerKeyBinding(key);
	}

	@SideOnly(value=Side.CLIENT)
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if (event.side == Side.SERVER) return;
		if (event.phase == Phase.START ) {
			if (key.getIsKeyPressed() && FMLClientHandler.instance().getClient().inGameHasFocus) {
				EntityPlayer player = event.player;
				PacketHandler.sendToServer(new PacketSpell((int)player.posX, (int)player.posY, (int)player.posZ, false));
			}
		}
	}

}
