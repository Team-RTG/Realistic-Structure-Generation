package teamrtg.rsg.config;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import teamrtg.rsg.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VillageConfigManager
{
    public static Map<Integer, VillageConfig> biomeConfigMap = new HashMap<Integer, VillageConfig>();

    public static void initBiomeConfigs()
    {
	    biomeConfigMap.clear();
        BiomeGenBase[] biomes = BiomeGenBase.getBiomeGenArray();
        for ( BiomeGenBase biome : biomes ) {
            biomeConfigMap.put(biome.biomeID, new VillageConfig(biome.biomeName));
        }
    }
    public static void setBiomeConfigsFromUserConfigs(VillageConfig[] villageConfig, Configuration config)
    {
        
        for (int i = 0; i < villageConfig.length; i++) {
            
            String categoryName = "village.materials." + villageConfig[i].materialName;
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
