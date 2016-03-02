package teamrtg.rsg.config;

import net.minecraftforge.common.config.Configuration;
import teamrtg.rsg.util.Logger;
import teamrtg.rsg.world.gen.structure.village.VillageMaterial;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class VillageConfigManager
{

	public static Configuration config;

	public static Map<VillageMaterial, Integer> biomeVillageConfigs;

	public static void init(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();
			biomeVillageConfigs = new HashMap<VillageMaterial, Integer>();
			setupConfigs(config);
		} catch (Exception e) {
			Logger.error("RSG has had a problem loading Village configuration.");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

	public static void setupConfigs(Configuration config) {
		VillageMaterial oak = new VillageMaterial("OAK");
	}

}
