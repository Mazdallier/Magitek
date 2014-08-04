package democretes;

import java.io.File;

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
import democretes.block.BlocksTR;
import democretes.item.ItemsTR;
import democretes.lib.Reference;
import democretes.proxy.CommonProxy;
import democretes.utils.CreativeTabsEM;
import democretes.utils.handlers.ConfigHandler;
import democretes.utils.handlers.GuiHandler;
import democretes.utils.network.PacketHandler;

@Mod(modid = Reference.MOD_ID, 
	 name = Reference.MOD_NAME, 
	 version = "0.0.1", 
	 dependencies = "required-after:CoFHCore")
 
public class Electromancy {

	@Instance(Reference.MOD_ID)
    public static Electromancy instance;
	
	@SidedProxy(clientSide = "democretes.proxy.ClientProxy", serverSide = "democretes.proxy.CommonProxy")
    public static CommonProxy proxy;
	
	public static CreativeTabs tabEM = new CreativeTabsEM(CreativeTabs.getNextID(), Reference.MOD_NAME);	
	
	
	@EventHandler
	public void foreplay(FMLPreInitializationEvent event) {
		ConfigHandler.init(new File(event.getModConfigurationDirectory(), "Technology Refined.cfg"));
		BlocksTR.init();
		ItemsTR.init();
	}
	
	@EventHandler
	public void penetration(FMLInitializationEvent event) {
		PacketHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		proxy.initRenderers();
		//MinecraftForge.EVENT_BUS.register(new EMEventHandler());
	}
	
	@EventHandler
	public void orgasm(FMLPostInitializationEvent event) {
	}
	
	
}
