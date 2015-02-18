package democretes.utils.handlers;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.utils.helper.StringHelper;

public class KeyHandler {
	
	public KeyBinding key = new KeyBinding(StringHelper.localize("magitek.key.spell"), Keyboard.KEY_R, "key.categories.gameplay");
	
	public KeyHandler() {
		 ClientRegistry.registerKeyBinding(key);
	}

	@SideOnly(value=Side.CLIENT)
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if (event.side == Side.SERVER) return;
		if (event.phase == Phase.START ) {
			
		}
	}

}
