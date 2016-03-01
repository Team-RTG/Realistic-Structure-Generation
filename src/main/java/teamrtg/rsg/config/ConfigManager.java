package teamrtg.rsg.config;

import java.io.File;

public class ConfigManager
{
    
    public static File rsgConfigFile;

    private ConfigRSG configRSG = new ConfigRSG();
    public ConfigRSG rsg() {
        return configRSG;
    }
    
    public static void init(String configpath)
    {
    
        rsgConfigFile = new File(configpath + "RSG.cfg");
        
        ConfigRSG.init(rsgConfigFile);

    }
}
