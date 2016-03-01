package teamrtg.rsg;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamrtg.rsg.config.ConfigManager;
import teamrtg.rsg.event.EventManagerRSG;

@Mod(modid = RSG.MODID, version = RSG.VERSION)
public class RSG
{
    public static final String MODID = "rsg";
    public static final String VERSION = "0.0.1";

    @Mod.Instance("RSG")
    public static RSG instance;
    public static String configPath;
    public static EventManagerRSG eventMgr;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;

        eventMgr = new EventManagerRSG();
        MinecraftForge.EVENT_BUS.register(eventMgr);
        MinecraftForge.ORE_GEN_BUS.register(eventMgr);
        MinecraftForge.TERRAIN_GEN_BUS.register(eventMgr);

        configPath = event.getModConfigurationDirectory() + "/RSG/";
        ConfigManager.init(configPath);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
