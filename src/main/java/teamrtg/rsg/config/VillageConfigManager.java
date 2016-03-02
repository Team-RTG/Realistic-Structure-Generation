package teamrtg.rsg.config;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import teamrtg.rsg.config.util.BlockStringUtil;
import teamrtg.rsg.util.Logger;
import teamrtg.rsg.util.RSGException;
import teamrtg.rsg.world.gen.structure.village.VillageMaterial;
import teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap;
import teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap;

import java.io.File;
import java.util.Map;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

public class VillageConfigManager
{

	public static Configuration config;

	public static VillageMaterial[] materials;

	public static void init(File configFile) {
		config = new Configuration(configFile);

		try {
			config.load();
			setupMaterials();
		} catch (Exception e) {
			Logger.error("RSG has had a problem loading Village configuration.");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

	public static void updateMaterialWithConfig(VillageMaterial material) {
		Map<EnumSwap, VillageMaterialSwap> map = material.swaps;
		for (EnumSwap k : map.keySet()) {
			String[] as1 = config.getStringList(
					k.name(),
					"villages.materials." + material.name + ".blocks",
					BlockStringUtil.statesToStrings(map.get(k).getAll()),
					""
			);
			if (as1.length > 0) {
				try {
					material.set(k, BlockStringUtil.stringsToSwap(as1));
				} catch (RSGException e) {
					e.log();
				}
			}
		}
		String[] as2 = new String[0];
		for (int i = 0; i < material.biomes.length; i++) {
			as2[i] = String.valueOf(material.biomes[i]);
		}
		String[] as3 = config.getStringList(
				"biomes",
				"villages.materials." + material.name,
				as2,
				""
		);
		material.biomes = new int[0];
		for (int i = 0; i < as3.length; i++) {
			material.biomes[i] = Integer.valueOf(as3[i]);
		}
		materials[materials.length] = material;
	}

	public static void setupMaterials() {
		materials = new VillageMaterial[0];
		/* ====================== OAK ====================== */
		VillageMaterial oak = new VillageMaterial("OAK");
		oak.set(ANVIL, Blocks.anvil.getDefaultState());
		oak.setBiomes(new int[]{
				BiomeGenBase.plains.biomeID,
				BiomeGenBase.forest.biomeID,
				BiomeGenBase.forestHills.biomeID,
				BiomeGenBase.extremeHillsEdge.biomeID,
				BiomeGenBase.extremeHillsPlus.biomeID
		});
		updateMaterialWithConfig(oak);

		/* ===================== SPRUCE ==================== */
		VillageMaterial spruce = new VillageMaterial("SPRUCE");
		spruce.set(WALL, Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.SPRUCE));
		spruce.set(ROOF, Blocks.spruce_stairs);
		spruce.set(HUT_ROOF, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.SPRUCE));
		spruce.set(CORNER, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.SPRUCE));
		spruce.set(FENCE, Blocks.spruce_fence.getDefaultState());
		spruce.set(DOOR, Blocks.spruce_door);
		spruce.set(WINDOW_SHUTTERS, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.SPRUCE));
		spruce.set(FARM_BORDER, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.SPRUCE));
		spruce.set(CHAIR, Blocks.spruce_stairs);
		spruce.set(ANVIL, Blocks.anvil.getDefaultState());
		spruce.set(WELL_POST, Blocks.spruce_fence.getDefaultState());
		spruce.set(LAMP_POST, Blocks.spruce_fence.getDefaultState());
		spruce.setBiomes( new int[]{
				BiomeGenBase.forest.biomeID,
				BiomeGenBase.forestHills.biomeID,
				BiomeGenBase.extremeHills.biomeID,
				BiomeGenBase.extremeHillsEdge.biomeID,
				BiomeGenBase.extremeHillsPlus.biomeID,
				BiomeGenBase.megaTaiga.biomeID,
				BiomeGenBase.megaTaigaHills.biomeID,
				BiomeGenBase.coldTaiga.biomeID,
				BiomeGenBase.coldTaigaHills.biomeID,
				BiomeGenBase.taiga.biomeID,
				BiomeGenBase.taigaHills.biomeID
		});
		updateMaterialWithConfig(spruce);

		/* ===================== BIRCH ===================== */
		VillageMaterial birch = new VillageMaterial("BIRCH");
		birch.set(WALL, Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.BIRCH));
		birch.set(ROOF, Blocks.birch_stairs);
		birch.set(HUT_ROOF, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.BIRCH));
		birch.set(CORNER, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.BIRCH));
		birch.set(FENCE, Blocks.birch_fence.getDefaultState());
		birch.set(DOOR, Blocks.birch_door);
		birch.set(WINDOW_SHUTTERS, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.BIRCH));
		birch.set(FARM_BORDER, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.BIRCH));
		birch.set(CHAIR, Blocks.birch_stairs);
		birch.set(ANVIL, Blocks.anvil.getDefaultState());
		birch.set(WELL_POST, Blocks.birch_fence.getDefaultState());
		birch.set(LAMP_POST, Blocks.birch_fence.getDefaultState());
		birch.setBiomes( new int[]{
				BiomeGenBase.birchForest.biomeID,
				BiomeGenBase.birchForestHills.biomeID
		});
		updateMaterialWithConfig(birch);

		/* ===================== JUNGLE ==================== */
		VillageMaterial jungle = new VillageMaterial("JUNGLE");
		jungle.set(WALL, Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.JUNGLE));
		jungle.set(ROOF, Blocks.jungle_stairs);
		jungle.set(HUT_ROOF, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.JUNGLE));
		jungle.set(CORNER, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.JUNGLE));
		jungle.set(FENCE, Blocks.jungle_fence.getDefaultState());
		jungle.set(DOOR, Blocks.jungle_door);
		jungle.set(WINDOW_SHUTTERS, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.JUNGLE));
		jungle.set(FARM_BORDER, Blocks.log.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.JUNGLE));
		jungle.set(CHAIR, Blocks.jungle_stairs);
		jungle.set(ANVIL, Blocks.anvil.getDefaultState());
		jungle.set(WELL_POST, Blocks.jungle_fence.getDefaultState());
		jungle.set(LAMP_POST, Blocks.jungle_fence.getDefaultState());
		jungle.setBiomes( new int[]{
				BiomeGenBase.jungle.biomeID,
				BiomeGenBase.jungleEdge.biomeID,
				BiomeGenBase.jungleHills.biomeID
		});
		updateMaterialWithConfig(jungle);

		/* ===================== ACACIA ==================== */
		VillageMaterial acacia = new VillageMaterial("ACACIA");
		acacia.set(WALL, Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.ACACIA));
		acacia.set(ROOF, Blocks.acacia_stairs);
		acacia.set(HUT_ROOF, Blocks.log2.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.ACACIA));
		acacia.set(CORNER, Blocks.log2.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.ACACIA));
		acacia.set(FENCE, Blocks.acacia_fence.getDefaultState());
		acacia.set(DOOR, Blocks.acacia_door);
		acacia.set(WINDOW_SHUTTERS, Blocks.log2.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.ACACIA));
		acacia.set(FARM_BORDER, Blocks.log2.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.ACACIA));
		acacia.set(CHAIR, Blocks.acacia_stairs);
		acacia.set(ANVIL, Blocks.anvil.getDefaultState());
		acacia.set(WELL_POST, Blocks.acacia_fence.getDefaultState());
		acacia.set(LAMP_POST, Blocks.acacia_fence.getDefaultState());
		acacia.setBiomes( new int[]{
				BiomeGenBase.savanna.biomeID,
				BiomeGenBase.savannaPlateau.biomeID
		});
		updateMaterialWithConfig(acacia);

		/* ===================== DARK_OAK ==================== */
		VillageMaterial dark_oak = new VillageMaterial("DARK_OAK");
		dark_oak.set(WALL, Blocks.planks.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.DARK_OAK));
		dark_oak.set(ROOF, Blocks.dark_oak_stairs);
		dark_oak.set(HUT_ROOF, Blocks.log2.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.DARK_OAK));
		dark_oak.set(CORNER, Blocks.log2.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.DARK_OAK));
		dark_oak.set(FENCE, Blocks.dark_oak_fence.getDefaultState());
		dark_oak.set(DOOR, Blocks.dark_oak_door);
		dark_oak.set(WINDOW_SHUTTERS, Blocks.log2.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.DARK_OAK));
		dark_oak.set(FARM_BORDER, Blocks.log2.getDefaultState().withProperty(BlockPlanks.VARIANT, EnumType.DARK_OAK));
		dark_oak.set(CHAIR, Blocks.dark_oak_stairs);
		dark_oak.set(ANVIL, Blocks.anvil.getDefaultState());
		dark_oak.set(WELL_POST, Blocks.dark_oak_fence.getDefaultState());
		dark_oak.set(LAMP_POST, Blocks.dark_oak_fence.getDefaultState());
		acacia.setBiomes( new int[]{
				BiomeGenBase.swampland.biomeID,
				BiomeGenBase.roofedForest.biomeID
		});
		updateMaterialWithConfig(dark_oak);
	}

}
