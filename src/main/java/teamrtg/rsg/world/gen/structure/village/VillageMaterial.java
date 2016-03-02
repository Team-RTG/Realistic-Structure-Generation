package teamrtg.rsg.world.gen.structure.village;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds all village blocks that should be replaced for the biome it belongs to
 */
public enum VillageMaterial {

	PATH(new VillageMaterialSwap(Blocks.gravel.getDefaultState())),
	FOUNDATION(new VillageMaterialSwap(Blocks.cobblestone.getDefaultState())),
	WALL(new VillageMaterialSwap(Blocks.planks.getDefaultState())),
	ROOF(new VillageMaterialSwap(Blocks.oak_stairs)),
	HUT_ROOF(new VillageMaterialSwap(Blocks.log.getDefaultState())),
	CORNER(new VillageMaterialSwap(Blocks.log.getDefaultState())),
	STAIRS(new VillageMaterialSwap(Blocks.stone_stairs)),
	BLACKSMITH_ROOF(new VillageMaterialSwap(Blocks.stone_slab)),
	FENCE(new VillageMaterialSwap(Blocks.oak_fence.getDefaultState())),
	DOOR(new VillageMaterialSwap(Blocks.oak_door)),
	CHURCH_BLOCK(new VillageMaterialSwap(Blocks.cobblestone.getDefaultState())),
	CHURCH_STAIRS(new VillageMaterialSwap(Blocks.stone_stairs)),
	WINDOW(new VillageMaterialSwap(Blocks.glass_pane.getDefaultState())),
	WINDOW_SHUTTERS(new VillageMaterialSwap(Blocks.log.getDefaultState())),
	CROPS(),
	FARMLAND(new VillageMaterialSwap(Blocks.farmland.getDefaultState())),
	FARM_BORDER(new VillageMaterialSwap(Blocks.log.getDefaultState())),
	GARDEN_GROUND(new VillageMaterialSwap(Blocks.grass.getDefaultState())),
	TABLE_TOP(new VillageMaterialSwap(Blocks.wooden_pressure_plate.getDefaultState())),
	CHAIR(new VillageMaterialSwap(Blocks.oak_stairs)),
	CHOPPING_BLOCK(new VillageMaterialSwap(Blocks.double_stone_slab.getDefaultState())),
	ANVIL(new VillageMaterialSwap(Blocks.double_stone_slab.getDefaultState())),
	WELL_POST(new VillageMaterialSwap(Blocks.oak_fence.getDefaultState())),
	WELL_BLOCK(new VillageMaterialSwap(Blocks.cobblestone.getDefaultState())),
	LAMP_POST(new VillageMaterialSwap(Blocks.oak_fence.getDefaultState())),
	LAMP_BLOCK(new VillageMaterialSwap(Blocks.wool.getStateFromMeta(EnumDyeColor.BLACK.getMetadata()))),
	LAMP_TORCH(new VillageMaterialSwap(Blocks.torch)));

    public String name;
new VillageMaterialSwap(Blocks.gravel.getDefaultState())
new VillageMaterialSwap(Blocks.cobblestone.getDefaultState())
new VillageMaterialSwap(Blocks.planks.getDefaultState())
new VillageMaterialSwap(Blocks.oak_stairs)
new VillageMaterialSwap(Blocks.log.getDefaultState())
new VillageMaterialSwap(Blocks.log.getDefaultState())
new VillageMaterialSwap(Blocks.stone_stairs)
new VillageMaterialSwap(Blocks.stone_slab)
new VillageMaterialSwap(Blocks.oak_fence.getDefaultState())
new VillageMaterialSwap(Blocks.oak_door)
new VillageMaterialSwap(Blocks.cobblestone.getDefaultState())
new VillageMaterialSwap(Blocks.stone_stairs)
new VillageMaterialSwap(Blocks.glass_pane.getDefaultState())
new VillageMaterialSwap(Blocks.log.getDefaultState())
/** If its represented multiple times, it has higher chances */
 {Blocks.wheat, Blocks.wheat, Blocks.wheat, Blocks.carrots, Blocks.potatoes}
new VillageMaterialSwap(Blocks.farmland.getDefaultState())
new VillageMaterialSwap(Blocks.log.getDefaultState())
new VillageMaterialSwap(Blocks.grass.getDefaultState())
new VillageMaterialSwap(Blocks.wooden_pressure_plate.getDefaultState())
new VillageMaterialSwap(Blocks.oak_stairs)
new VillageMaterialSwap(Blocks.double_stone_slab.getDefaultState())
new VillageMaterialSwap(Blocks.double_stone_slab.getDefaultState())
new VillageMaterialSwap(Blocks.oak_fence.getDefaultState())
new VillageMaterialSwap(Blocks.cobblestone.getDefaultState())
new VillageMaterialSwap(Blocks.oak_fence.getDefaultState())
new VillageMaterialSwap(Blocks.wool.getStateFromMeta(EnumDyeColor.BLACK.getMetadata()))
    public  true;

    public VillageMaterial(String name) {

        this.name = name;

    }

    public static VillageMaterial getForBiome(BiomeGenBase biome) {
        return new VillageMaterial("");
    }

    public Map<String, VillageMaterialSwap> asMap() {
        Map<String, VillageMaterialSwap> map = new HashMap<String, VillageMaterialSwap>();
        map.put("path", this.path);
	    map.put("foundation", this.foundation);
	    map.put("wall", this.wall);
	    map.put("roof", this.roof);
	    map.put("hut_roof", this.roof);
	    map.put("corner", this.corner);
	    map.put("stairs", this.stairs);
	    map.put("blacksmith_roof", this.blacksmith_roof);
	    map.put("fence", this.fence);
	    map.put("door", this.door);
	    map.put("church_block", this.church_block);
	    map.put("church_stairs", this.church_stairs);
	    map.put("window", this.window);
	    map.put("window_shutters", this.window_shutters);
	    map.put("farmland", this.farmland);
	    map.put("farm_border", this.farm_border);
	    map.put("garden_ground", this.garden_ground);
	    map.put("table_top", this.table_top);
	    map.put("chair", this.chair);
	    map.put("chopping_block", this.chopping_block);
	    map.put("anvil", this.anvil);
	    map.put("well_post", this.well_post);
	    map.put("well_block", this.well_block);
	    map.put("lamp_post", this.lamp_post);
	    map.put("lamp_block", this.lamp_block);
        return map;
    }
}
