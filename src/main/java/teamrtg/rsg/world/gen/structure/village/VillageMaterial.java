package teamrtg.rsg.world.gen.structure.village;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rsg.config.VillageConfigManager;
import teamrtg.rsg.event.EventManagerRSG;
import teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

/**
 * Holds all village blocks that should be replaced for the biome it belongs to
 */
public class VillageMaterial {

	public String name;
	public int[] biomes = new int[0];
	public Map<EnumSwap, VillageMaterialSwap> swaps = new HashMap<EnumSwap, VillageMaterialSwap>();
	public boolean generate = true;

    public VillageMaterial(String name) {
	    swaps.clear();
	    swaps.put(PATH, new VillageMaterialSwap(Blocks.gravel.getDefaultState()));
	    swaps.put(FOUNDATION, new VillageMaterialSwap(Blocks.cobblestone.getDefaultState()));
	    swaps.put(WALL, new VillageMaterialSwap(Blocks.planks.getDefaultState()));
	    swaps.put(ROOF, new VillageMaterialSwap(Blocks.oak_stairs));
	    swaps.put(HUT_ROOF, new VillageMaterialSwap(Blocks.log.getDefaultState()));
	    swaps.put(CORNER, new VillageMaterialSwap(Blocks.log.getDefaultState()));
	    swaps.put(STAIRS, new VillageMaterialSwap(Blocks.stone_stairs));
	    swaps.put(BLACKSMITH_ROOF, new VillageMaterialSwap(Blocks.stone_slab));
	    swaps.put(FENCE, new VillageMaterialSwap(Blocks.oak_fence.getDefaultState()));
	    swaps.put(DOOR, new VillageMaterialSwap(Blocks.oak_door));
	    swaps.put(CHURCH_BLOCK, new VillageMaterialSwap(Blocks.cobblestone.getDefaultState()));
	    swaps.put(CHURCH_STAIRS, new VillageMaterialSwap(Blocks.stone_stairs));
	    swaps.put(WINDOW, new VillageMaterialSwap(Blocks.glass_pane.getDefaultState()));
	    swaps.put(WINDOW_SHUTTERS, new VillageMaterialSwap(Blocks.log.getDefaultState()));
	    swaps.put(CROPS, new VillageMaterialSwap(Blocks.wheat, Blocks.wheat, Blocks.wheat, Blocks.carrots, Blocks.potatoes));
	    swaps.put(FARMLAND, new VillageMaterialSwap(Blocks.farmland.getDefaultState()));
	    swaps.put(FARM_BORDER, new VillageMaterialSwap(Blocks.log.getDefaultState()));
	    swaps.put(GARDEN_GROUND, new VillageMaterialSwap(Blocks.grass.getDefaultState()));
	    swaps.put(TABLE_TOP, new VillageMaterialSwap(Blocks.wooden_pressure_plate.getDefaultState()));
	    swaps.put(CHAIR, new VillageMaterialSwap(Blocks.oak_stairs));
	    swaps.put(CHOPPING_BLOCK, new VillageMaterialSwap(Blocks.double_stone_slab.getDefaultState()));
	    swaps.put(ANVIL, new VillageMaterialSwap(Blocks.double_stone_slab.getDefaultState()));
	    swaps.put(WELL_POST, new VillageMaterialSwap(Blocks.oak_fence.getDefaultState()));
	    swaps.put(WELL_BLOCK, new VillageMaterialSwap(Blocks.cobblestone.getDefaultState()));
	    swaps.put(LAMP_POST, new VillageMaterialSwap(Blocks.oak_fence.getDefaultState()));
	    swaps.put(LAMP_BLOCK, new VillageMaterialSwap(Blocks.wool.getStateFromMeta(EnumDyeColor.BLACK.getMetadata())));
	    swaps.put(LAMP_TORCH, new VillageMaterialSwap(Blocks.torch));
	    this.name = name.toUpperCase();
    }

	public VillageMaterialSwap get(EnumSwap swap) {
		return swaps.get(swap);
	}
	public VillageMaterialSwap set(EnumSwap swap, IBlockState... bs) {
		return swaps.put(swap, new VillageMaterialSwap(bs));
	}
	public VillageMaterialSwap set(EnumSwap swap, Block... b) {
		return swaps.put(swap, new VillageMaterialSwap(b));
	}
	public VillageMaterialSwap set(EnumSwap enumSwap, VillageMaterialSwap swap) {
		return swaps.put(enumSwap, swap);
	}
    public static VillageMaterial getForBiome(BiomeGenBase biome) {
	    List<VillageMaterial> am1 = new ArrayList<VillageMaterial>();
	    VillageMaterial[] am2 = VillageConfigManager.materials.values().toArray(new VillageMaterial[VillageConfigManager.materials.values().size()]);
	    for (int i = 0; i < am2.length; i++) {
		    for (int j = 0; j < am2[i].biomes.length; j++) {
			    if (am2[i].biomes[j] == biome.biomeID) am1.add(am2[i]);
		    }
	    }
	    if( am1.size() < 1 ) {
		    VillageMaterial mat = new VillageMaterial("OAK");
		    mat.generate = false;
		    return mat;
	    }
	    int r = EventManagerRSG.rand.nextInt(am1.size());
	    return am1.get(r);
    }

	public void setBiomes(int[] biomeIds) {
		this.biomes = biomeIds;
	}
}
