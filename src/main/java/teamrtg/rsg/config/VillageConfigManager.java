package teamrtg.rsg.config;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import teamrtg.rsg.util.Logger;
import teamrtg.rsg.world.gen.structure.village.VillageMaterial;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class VillageConfigManager
{

	public static Configuration config;

	public static Map<Integer, VillageMaterial> biomeVillageConfigs;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			setBiomeConfigsFromUserConfigs(config);
		}
		catch (Exception e)
		{
			Logger.error("RSG has had a problem loading Village configuration.");
		}
		finally
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}
	}

    public static void setBiomeConfigsFromUserConfigs(Configuration config)
    {
        ConfigCategory[] presets = (ConfigCategory[]) config.getCategory("village.materials").getChildren().toArray();
	    biomeVillageConfigs.clear();
        for (int i = 0; i < presets.length; i++) {
	        ConfigCategory preset = presets[i];
            String categoryName = preset.getName();
	        config.getInt()
            ArrayList<ConfigProperty> properties = villageConfig[i].getProperties();
            
            for (int j = 0; j < properties.size(); j++) {

                ConfigProperty prop = properties.get(j);
                                
                switch (prop.type) {
                    
                    case INTEGER:

                        prop.valueInt = config.getInt(
                            prop.name,
                            categoryName,
                            prop.valueInt,
                            prop.minValue,
                            prop.maxValue,
                            prop.description
                        );
                        
                        break;
                        
                    case BOOLEAN:
                        
                        prop.valueBoolean = config.getBoolean(
                            prop.name,
                            categoryName,
                            prop.valueBoolean,
                            prop.description
                        );
                        
                        break;
                        
                    case STRING:
                        
                        prop.valueString = config.getString(
                            prop.name,
                            categoryName,
                            prop.valueString,
                            prop.description
                        );
                        
                        break;
	                case STRING_LIST:

		                prop.valueStrings = config.getStringList(
			                prop.name,
			                categoryName,
			                prop.valueStrings,
			                prop.description
		                );
		                break;

	                case BLOCK:

		                String[] s = config.getString(
				                prop.name,
				                categoryName,
				                prop.valueBlock.toString() + "/" + prop.valueBlock.getBlock().getMetaFromState(prop.valueBlock),
				                prop.description
		                ).split("/");
		                try {
			                prop.valueBlock = Block.getBlockFromName(s[0]).getStateFromMeta(Integer.valueOf(s[1]));
		                } catch (Exception e) {
			                Logger.error("Invalid block format for %s.%s", categoryName, prop.name, e);
		                }
	                default:
                        throw new RuntimeException("ConfigProperty type not supported.");
                }
            }
        }
    }

}
