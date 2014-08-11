package democretes;

import java.io.File;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import democretes.api.spells.Spell;
import democretes.block.BlocksMT;
import democretes.item.ItemsMT;
import democretes.item.spells.SpellsMT;
import democretes.lib.Reference;
import democretes.proxy.CommonProxy;
import democretes.utils.CreativeTabsMT;
import democretes.utils.crafting.AltarRecipes;
import democretes.utils.handlers.ConfigHandler;
import democretes.utils.handlers.MTEventHandler;
import democretes.utils.handlers.GuiHandler;
import democretes.utils.network.PacketHandler;

@Mod(modid = Reference.MOD_ID, 
	 name = Reference.MOD_NAME, 
	 version = "0.0.1")
 
public class Magitek {

	@Instance(Reference.MOD_ID)
    public static Magitek instance;
	
	@SidedProxy(clientSide = "democretes.proxy.ClientProxy", serverSide = "democretes.proxy.CommonProxy")
    public static CommonProxy proxy;
	
	public static CreativeTabs tabMT = new CreativeTabsMT(CreativeTabs.getNextID(), Reference.MOD_NAME);	
		
	@EventHandler
	public void foreplay(FMLPreInitializationEvent event) {
		ConfigHandler.init(new File(event.getModConfigurationDirectory(), "Magitek.cfg"));
		SpellsMT.init();
		BlocksMT.init();
		ItemsMT.init();
	}
	
	@EventHandler
	public void penetration(FMLInitializationEvent event) {
		PacketHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		proxy.initRenderers();
  		proxy.registerKeyBindings();
		//MinecraftForge.EVENT_BUS.register(new MTEventHandler());
	}
	
	@EventHandler
	public void orgasm(FMLPostInitializationEvent event) {
		AltarRecipes.initAltarRecipes();
	}
	
	
}
