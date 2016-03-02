package teamrtg.rsg.config;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import teamrtg.rsg.util.BlockUtil;
import teamrtg.rsg.util.RSGException;
import teamrtg.rsg.world.gen.structure.village.VillageMaterial;
import teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap;
import teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap;

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

    public static void materialToConfig(VillageMaterial material, Configuration config) {
        Map<EnumSwap, VillageMaterialSwap> map = material.swaps;
        for (EnumSwap k : map.keySet()) {
	        String[] as1 = config.getStringList(
	            k.name(),
	            Categories.VILLAGE_MATERIALS.append(material.name + ".blocks"),
	            BlockUtil.statesToStrings(map.get(k).getAll()),
	            ""
            );
        }
	    String[] as2 = new String[0];
	    for (int i = 0; i < material.biomes.length; i++) {
		    as2[i] = String.valueOf(material.biomes[i]);
	    }
	    String[] as3 = config.getStringList(
			    "biomes",
			    Categories.VILLAGE_MATERIALS.append(material.name),
			    as2,
			    ""
	    );
	    material.biomes = new int[0];
	    for (int i = 0; i < as3.length; i++) {
		    material.biomes[i] = Integer.valueOf(as3[i]);
	    }
    }
	public static VillageMaterial configToMaterial(Configuration config, String materialName) {
		VillageMaterial material = new VillageMaterial(materialName);
		ConfigCategory cat = config.getCategory(Categories.VILLAGE_MATERIALS.append(materialName + ".blocks"));
		for (int i = 0; i < cat.getValues().size(); i++) {
			try {
				String k = (String) cat.keySet().toArray()[i];
				String[] v = config.getStringList(k, Categories.VILLAGE_MATERIALS.append(materialName + ".blocks"), new String[0], "");
				material.set(EnumSwap.fromName(k), BlockUtil.stringsToSwap(v));
			} catch (RSGException e) {
				e.log();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return material;
	}
}
