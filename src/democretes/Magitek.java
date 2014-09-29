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
import democretes.api.helpers.RitualHelper;
import democretes.api.helpers.RunicHelper;
import democretes.block.MTBlocks;
import democretes.entity.EntitiesMT;
import democretes.item.MTItems;
import democretes.item.spells.SpellsMT;
import democretes.lib.Reference;
import democretes.proxy.CommonProxy;
import democretes.render.fx.ParticleRenderDispatcher;
import democretes.utils.CreativeTabsMT;
import democretes.utils.crafting.RecipeRegistry;
import democretes.utils.handlers.BlockTransferHandler;
import democretes.utils.handlers.ConfigHandler;
import democretes.utils.handlers.GuiHandler;
import democretes.utils.handlers.MTEventHandler;
import democretes.utils.network.PacketHandler;

@Mod(modid = Reference.MOD_ID, 
	 name = Reference.MOD_NAME, 
	 version = "0.2.0",
	 guiFactory = "democretes.utils.handlers.GuiFactory")
 
public class Magitek {

	@Instance(Reference.MOD_ID)
    public static Magitek instance;
	
	@SidedProxy(clientSide = "democretes.proxy.ClientProxy", serverSide = "democretes.proxy.CommonProxy")
    public static CommonProxy proxy;
	
	public static CreativeTabs tabMT = new CreativeTabsMT(CreativeTabs.getNextID(), Reference.MOD_NAME);	
		
	
	
	@EventHandler
	public void foreplay(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		SpellsMT.init();
		MTBlocks.init();
		MTItems.init();
		EntitiesMT.init();
	}
	
	@EventHandler
	public void penetration(FMLInitializationEvent event) {
		PacketHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		proxy.initRenderers();
  		proxy.registerKeyBindings();
		MinecraftForge.EVENT_BUS.register(new MTEventHandler());
		MinecraftForge.EVENT_BUS.register(new ParticleRenderDispatcher());
		BlockTransferHandler.registerBlocks();
	}
	
	@EventHandler
	public void orgasm(FMLPostInitializationEvent event) {
		RecipeRegistry.initAllTheRecipes();
	}
	
	
}
