package teamrtg.rsg.world.gen.structure.village;

import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.biome.BiomeGenBase;

import javax.annotation.Nullable;

/**
 * Holds all village blocks that should be replaced for the biome it belongs to
 */
public class VillageMaterial {

    public final VillageMaterialSwap path = new VillageMaterialSwap(Blocks.gravel.getDefaultState());
    public final VillageMaterialSwap foundation = new VillageMaterialSwap(Blocks.cobblestone.getDefaultState());
    public final VillageMaterialSwap wall = new VillageMaterialSwap(Blocks.planks.getDefaultState());
    public final VillageMaterialSwap roof = new VillageMaterialSwap(Blocks.oak_stairs);
    public final VillageMaterialSwap hut_roof = new VillageMaterialSwap(Blocks.log.getDefaultState());
    public final VillageMaterialSwap corner = new VillageMaterialSwap(Blocks.log.getDefaultState());
    public final VillageMaterialSwap stairs = new VillageMaterialSwap(Blocks.stone_stairs);
    public final VillageMaterialSwap blacksmith_roof = new VillageMaterialSwap(Blocks.stone_slab);
    public final VillageMaterialSwap fence = new VillageMaterialSwap(Blocks.oak_fence.getDefaultState());
    public final VillageMaterialSwap door = new VillageMaterialSwap(Blocks.oak_door);
    public final VillageMaterialSwap church_block = new VillageMaterialSwap(Blocks.cobblestone.getDefaultState());
    public final VillageMaterialSwap church_stairs = new VillageMaterialSwap(Blocks.stone_stairs);
    public final VillageMaterialSwap window = new VillageMaterialSwap(Blocks.glass_pane.getDefaultState());
    public final VillageMaterialSwap window_shutters = new VillageMaterialSwap(Blocks.log.getDefaultState());
    /** If its represented multiple times, it has higher chances */
    public final Block[] crops = {Blocks.wheat, Blocks.wheat, Blocks.wheat, Blocks.carrots, Blocks.potatoes};
    public final VillageMaterialSwap farmland = new VillageMaterialSwap(Blocks.farmland.getDefaultState());
    public final VillageMaterialSwap farm_border = new VillageMaterialSwap(Blocks.log.getDefaultState());
    public final VillageMaterialSwap garden_ground = new VillageMaterialSwap(Blocks.grass.getDefaultState());
    public final VillageMaterialSwap table_top = new VillageMaterialSwap(Blocks.wooden_pressure_plate.getDefaultState());
    public final VillageMaterialSwap chair = new VillageMaterialSwap(Blocks.oak_stairs);
    public final VillageMaterialSwap chopping_block = new VillageMaterialSwap(Blocks.double_stone_slab.getDefaultState());
    public final VillageMaterialSwap anvil = new VillageMaterialSwap(Blocks.double_stone_slab.getDefaultState());
    public final VillageMaterialSwap well_post = new VillageMaterialSwap(Blocks.oak_fence.getDefaultState());
    public final VillageMaterialSwap well_block = new VillageMaterialSwap(Blocks.cobblestone.getDefaultState());
    public final VillageMaterialSwap lamp_post = new VillageMaterialSwap(Blocks.oak_fence.getDefaultState());
    public final VillageMaterialSwap lamp_block = new VillageMaterialSwap(Blocks.wool.getStateFromMeta(EnumDyeColor.BLACK.getMetadata()));
    public final boolean lamp_torches = true;

    public enum Preset {
        BASE,
        SAND,
        ACACIA,
        SPRUCE,
        DARK_OAK,
        BIRCH,
        ICE,
        JUNGLE,
        STONE_BRICK,
        RED_SAND
    }

    public VillageMaterial(Preset preset) {

        switch ( preset ) {
            case SAND:
                path.set(Blocks.sandstone.getDefaultState());
                foundation.set(Blocks.sandstone.getDefaultState());
                wall.set(Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()));
                corner.set(Blocks.sandstone.getDefaultState());
                roof.set(Blocks.sandstone_stairs);
                stairs.set(Blocks.sandstone_stairs);
                blacksmith_roof.set(Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.getMetadata()));
                break;
            case ACACIA:
                wall.set(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.ACACIA.getMetadata()));
                corner.set(Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA));
                roof.set(Blocks.acacia_stairs);
                fence.set(Blocks.acacia_fence);
                door.set(Blocks.acacia_door);
                church_block.set(Blocks.red_sandstone.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH));
                church_stairs.set(Blocks.red_sandstone_stairs);
                window.set(Blocks.stained_glass_pane.getDefaultState());
                window_shutters.set(Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA));
                hut_roof.set(Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA));
                fence.set(Blocks.acacia_fence.getDefaultState());
                farm_border.set(Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA));
                chair.set(Blocks.acacia_stairs);
                anvil.set(Blocks.anvil.getDefaultState());
                well_post.set(Blocks.acacia_fence.getDefaultState());
                break;
            case SPRUCE:
                wall.set(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.getMetadata()));
                corner.set(Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE));
                roof.set(Blocks.spruce_stairs);
                fence.set(Blocks.spruce_fence);
                door.set(Blocks.spruce_door);
                break;
            case DARK_OAK:
                wall.set(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.DARK_OAK.getMetadata()));
                corner.set(Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK));
                roof.set(Blocks.dark_oak_stairs);
                fence.set(Blocks.dark_oak_fence);
                door.set(Blocks.dark_oak_door);
                break;
            case BIRCH:
                wall.set(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.BIRCH.getMetadata()));
                corner.set(Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH));
                roof.set(Blocks.birch_stairs);
                fence.set(Blocks.birch_fence);
                door.set(Blocks.birch_door);
                break;
            case ICE:
                path.set(Blocks.packed_ice.getDefaultState());
                wall.set(Blocks.packed_ice.getDefaultState());
                foundation.set(Blocks.snow.getDefaultState());
                corner.set(Blocks.snow.getDefaultState());
                roof.set(Blocks.snow.getDefaultState());
                stairs.set(Blocks.snow_layer.getDefaultState().withProperty(BlockSnow.LAYERS, 4));
                blacksmith_roof.set(Blocks.snow_layer.getDefaultState().withProperty(BlockSnow.LAYERS, 4));
                fence.set(Blocks.birch_fence);
                door.set(Blocks.spruce_door);
                break;
            case JUNGLE:
                wall.set(Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.JUNGLE.getMetadata()));
                corner.set(Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));
                roof.set(Blocks.jungle_stairs);
                fence.set(Blocks.jungle_fence);
                door.set(Blocks.jungle_door);
                break;
            case STONE_BRICK:
                wall.set(Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT));
                foundation.set(Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY));
                corner.set(Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED));
                roof.set(Blocks.stone_brick_stairs);
                stairs.set(Blocks.stone_brick_stairs);
                blacksmith_roof.set(Blocks.stone_slab.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.BRICK));
                fence.set(Blocks.cobblestone_wall);
                door.set(Blocks.spruce_door);
                break;
            case RED_SAND:
                path.set(Blocks.red_sandstone.getDefaultState());
                foundation.set(Blocks.red_sandstone.getDefaultState());
                wall.set(Blocks.red_sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()));
                corner.set(Blocks.red_sandstone.getDefaultState());
                roof.set(Blocks.red_sandstone_stairs);
                stairs.set(Blocks.red_sandstone_stairs);
                blacksmith_roof.set(Blocks.stone_slab2.getDefaultState().withProperty(BlockStoneSlabNew.VARIANT, BlockStoneSlabNew.EnumType.RED_SANDSTONE));
                door.set(Blocks.acacia_door);
                break;
        }

    }

    @Nullable
    public static VillageMaterial getForBiome(BiomeGenBase biome) {
        return new VillageMaterial(Preset.ACACIA);
    }
}
