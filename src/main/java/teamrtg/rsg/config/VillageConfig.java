package teamrtg.rsg.config;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import teamrtg.rsg.util.BlockUtil;
import teamrtg.rsg.world.gen.structure.village.VillageMaterial;
import teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap;

import java.util.Map;

/**
 * Created by topisani on 2/23/16.
 */
public class VillageConfig {

    enum Categories {
        VILLAGE_MATERIALS("villages.materials");

        public String s;

        Categories(String s) {
            this.s = s;
        }

        public String append(String s1) {
            return this.s + "." + s1;
        }
    }

    public static ConfigCategory materialToConfig(VillageMaterial material, Configuration config) {

        if (config.hasCategory(Categories.VILLAGE_MATERIALS.append(material.name))) {
            return config.getCategory(Categories.VILLAGE_MATERIALS.append(material.name));
        }
        ConfigCategory category;
        category = new ConfigCategory(material.name);
        Map<String, VillageMaterialSwap> map = material.asMap();
        for (String k : map.keySet()) {
            VillageMaterialSwap swap = map.get(k);
            config.getString(
		            k,
		            Categories.VILLAGE_MATERIALS.append(material.name + ".blocks"),
		            BlockUtil.stateToString(swap.getDefault()),
		            ""
            );
        }
        return category;
    }

	public static
}
