package teamrtg.rsg.config;

import java.io.File;

public class ConfigManager
{
    
    public static File rsgConfigFile;
    public static File villageConfigFile;

    private ConfigRSG configRSG = new ConfigRSG();
    public ConfigRSG rsg() {
        return configRSG;
    }
    
    public static void init(String configpath)
    {
    
        rsgConfigFile = new File(configpath + "RSG.cfg");
        villageConfigFile = new File(configpath + "Villages.cfg");
        
        ConfigRSG.init(rsgConfigFile);
        VillageConfigManager.init(villageConfigFile);

    }
}
