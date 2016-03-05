package teamrtg.rsg.world.gen.structure.village;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import teamrtg.rsg.util.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

@SuppressWarnings("incomplete-switch")
public class StructureVillagePiecesRSG extends StructureVillagePieces
{
    public static void registerVillagePieces()
    {
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.House1.class, "ViBH_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.Field1.class, "ViDF_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.Field2.class, "ViF_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.Torch.class, "ViL_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.Hall.class, "ViPH_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.House4Garden.class, "ViSH_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.WoodHut.class, "ViSmH_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.Church.class, "ViST_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.House2.class, "ViS_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.Start.class, "ViStart_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.Path.class, "ViSR_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.House3.class, "ViTRH_RTG");
        MapGenStructureIO.registerStructureComponent(StructureVillagePiecesRSG.Well.class, "ViW_RTG");
        Logger.debug("Registered custom village pieces.");
    }

    public static List<PieceWeight> getStructureVillageWeightedPieceList(Random random, int p_75084_1_)
    {
        List<PieceWeight> list = Lists.<StructureVillagePieces.PieceWeight>newArrayList();
        list.add(new StructureVillagePieces.PieceWeight(StructureVillagePiecesRSG.House4Garden.class, 4, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(StructureVillagePiecesRSG.Church.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 1 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(StructureVillagePiecesRSG.House1.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 2 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(StructureVillagePiecesRSG.WoodHut.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 5 + p_75084_1_ * 3)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(StructureVillagePiecesRSG.Hall.class, 15, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 2 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(StructureVillagePiecesRSG.Field1.class, 3, MathHelper.getRandomIntegerInRange(random, 1 + p_75084_1_, 4 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(StructureVillagePiecesRSG.Field2.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(StructureVillagePiecesRSG.House2.class, 15, MathHelper.getRandomIntegerInRange(random, 0, 1 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(StructureVillagePiecesRSG.House3.class, 8, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 3 + p_75084_1_ * 2)));
        net.minecraftforge.fml.common.registry.VillagerRegistry.addExtraVillageComponents(list, random, p_75084_1_);
        Iterator<PieceWeight> iterator = list.iterator();

        while (iterator.hasNext())
        {
            if (((StructureVillagePieces.PieceWeight)iterator.next()).villagePiecesLimit == 0)
            {
                iterator.remove();
            }
        }

        return list;
    }

    private static int func_75079_a(List<PieceWeight> p_75079_0_)
    {
        boolean flag = false;
        int i = 0;

        for (StructureVillagePieces.PieceWeight structurevillagepieces$pieceweight : p_75079_0_)
        {
            if (structurevillagepieces$pieceweight.villagePiecesLimit > 0 && structurevillagepieces$pieceweight.villagePiecesSpawned < structurevillagepieces$pieceweight.villagePiecesLimit)
            {
                flag = true;
            }

            i += structurevillagepieces$pieceweight.villagePieceWeight;
        }

        return flag ? i : -1;
    }

    private static StructureVillagePiecesRSG.Village func_176065_a(StructureVillagePiecesRSG.Start start, StructureVillagePieces.PieceWeight weight, List<StructureComponent> p_176065_2_, Random rand, int p_176065_4_, int p_176065_5_, int p_176065_6_, EnumFacing facing, int p_176065_8_)
    {
        Class <? extends StructureVillagePieces.Village > oclass = weight.villagePieceClass;
        StructureVillagePiecesRSG.Village structurevillagepieces$village = null;

        if (oclass == StructureVillagePiecesRSG.House4Garden.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.House4Garden.func_175858_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureVillagePiecesRSG.Church.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.Church.func_175854_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureVillagePiecesRSG.House1.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.House1.func_175850_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureVillagePiecesRSG.WoodHut.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.WoodHut.func_175853_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureVillagePiecesRSG.Hall.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.Hall.func_175857_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureVillagePiecesRSG.Field1.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.Field1.func_175851_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureVillagePiecesRSG.Field2.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.Field2.func_175852_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureVillagePiecesRSG.House2.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.House2.func_175855_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == StructureVillagePiecesRSG.House3.class)
        {
            structurevillagepieces$village = StructureVillagePiecesRSG.House3.func_175849_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else
        {
            structurevillagepieces$village = (Village) net.minecraftforge.fml.common.registry.VillagerRegistry.getVillageComponent(weight, start , p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }

        return structurevillagepieces$village;
    }

    private static StructureVillagePiecesRSG.Village func_176067_c(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_176067_1_, Random rand, int p_176067_3_, int p_176067_4_, int p_176067_5_, EnumFacing facing, int p_176067_7_)
    {
        int i = func_75079_a(start.structureVillageWeightedPieceList);

        if (i <= 0)
        {
            return null;
        }
        else
        {
            int j = 0;

            while (j < 5)
            {
                ++j;
                int k = rand.nextInt(i);

                for (StructureVillagePieces.PieceWeight structurevillagepieces$pieceweight : start.structureVillageWeightedPieceList)
                {
                    k -= structurevillagepieces$pieceweight.villagePieceWeight;

                    if (k < 0)
                    {
                        if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePiecesOfType(p_176067_7_) || structurevillagepieces$pieceweight == start.structVillagePieceWeight && start.structureVillageWeightedPieceList.size() > 1)
                        {
                            break;
                        }

                        StructureVillagePiecesRSG.Village structurevillagepieces$village = func_176065_a(start, structurevillagepieces$pieceweight, p_176067_1_, rand, p_176067_3_, p_176067_4_, p_176067_5_, facing, p_176067_7_);

                        if (structurevillagepieces$village != null)
                        {
                            ++structurevillagepieces$pieceweight.villagePiecesSpawned;
                            start.structVillagePieceWeight = structurevillagepieces$pieceweight;

                            if (!structurevillagepieces$pieceweight.canSpawnMoreVillagePieces())
                            {
                                start.structureVillageWeightedPieceList.remove(structurevillagepieces$pieceweight);
                            }

                            return structurevillagepieces$village;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = StructureVillagePiecesRSG.Torch.func_175856_a(start, p_176067_1_, rand, p_176067_3_, p_176067_4_, p_176067_5_, facing);

            if (structureboundingbox != null)
            {
                return new StructureVillagePiecesRSG.Torch(start, p_176067_7_, rand, structureboundingbox, facing);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent func_176066_d(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_176066_1_, Random rand, int p_176066_3_, int p_176066_4_, int p_176066_5_, EnumFacing facing, int p_176066_7_)
    {
        if (p_176066_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_176066_3_ - start.getBoundingBox().minX) <= 112 && Math.abs(p_176066_5_ - start.getBoundingBox().minZ) <= 112)
        {
            StructureComponent structurecomponent = func_176067_c(start, p_176066_1_, rand, p_176066_3_, p_176066_4_, p_176066_5_, facing, p_176066_7_ + 1);

            if (structurecomponent != null)
            {
                int i = (structurecomponent.getBoundingBox().minX + structurecomponent.getBoundingBox().maxX) / 2;
                int j = (structurecomponent.getBoundingBox().minZ + structurecomponent.getBoundingBox().maxZ) / 2;
                int k = structurecomponent.getBoundingBox().maxX - structurecomponent.getBoundingBox().minX;
                int l = structurecomponent.getBoundingBox().maxZ - structurecomponent.getBoundingBox().minZ;
                int i1 = k > l ? k : l;

                p_176066_1_.add(structurecomponent);
                start.field_74932_i.add(structurecomponent);
                return structurecomponent;
            }

            return null;
        }
        else
        {
            return null;
        }
    }

    private static StructureComponent func_176069_e(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_176069_1_, Random rand, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing facing, int p_176069_7_)
    {
        if (p_176069_7_ > 3 + start.terrainType)
        {
            return null;
        }
        else if (Math.abs(p_176069_3_ - start.getBoundingBox().minX) <= 112 && Math.abs(p_176069_5_ - start.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = StructureVillagePiecesRSG.Path.func_175848_a(start, p_176069_1_, rand, p_176069_3_, p_176069_4_, p_176069_5_, facing);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
                StructureComponent structurecomponent = new StructureVillagePiecesRSG.Path(start, p_176069_7_, rand, structureboundingbox, facing);
                int i = (structurecomponent.getBoundingBox().minX + structurecomponent.getBoundingBox().maxX) / 2;
                int j = (structurecomponent.getBoundingBox().minZ + structurecomponent.getBoundingBox().maxZ) / 2;
                int k = structurecomponent.getBoundingBox().maxX - structurecomponent.getBoundingBox().minX;
                int l = structurecomponent.getBoundingBox().maxZ - structurecomponent.getBoundingBox().minZ;
                int i1 = k > l ? k : l;

                p_176069_1_.add(structurecomponent);
                start.field_74930_j.add(structurecomponent);
                return structurecomponent;
            }

            return null;
        }
        else
        {
            return null;
        }
    }

    public static class Church extends StructureVillagePiecesRSG.Village
    {
        public Church(StructureVillagePiecesRSG.Start start, int p_i45564_2_, Random rand, StructureBoundingBox p_i45564_4_, EnumFacing facing)
        {
            super(start, p_i45564_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45564_4_;
        }

        public static StructureVillagePiecesRSG.Church func_175854_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175854_1_, Random rand, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing facing, int p_175854_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 5, 12, 9, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null ? new StructureVillagePiecesRSG.Church(start, p_175854_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 12 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 3, 9, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 3, 0, 8, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 3, 10, 0, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 10, 3, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 10, 3, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 4, 0, 4, 7, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 4, 4, 4, 7, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 8, 3, 4, 8, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 4, 3, 10, 4, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 5, 3, 5, 7, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 9, 0, 4, 9, 4, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 4, 4, 4, this.material.get(CHURCH_BLOCK).get(), this.material.get(CHURCH_BLOCK).get(), false);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 0, 11, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 4, 11, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 2, 11, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 2, 11, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 1, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 1, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 2, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 3, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_BLOCK).get(), 3, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 3, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 1)), 1, 2, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHURCH_STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 0)), 3, 2, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 7, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 7, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 6, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 7, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 6, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 7, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 3, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 2, 4, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), 1, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), 3, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 4, 5, structureBoundingBoxIn);
            int i = this.getMetadataWithOffset(Blocks.ladder, 4);

            for (int j = 1; j <= 9; ++j)
            {
                this.setBlockState(worldIn, Blocks.ladder.getStateFromMeta(i), 3, j, 3, structureBoundingBoxIn);
            }

            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
            this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

            if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getBlock().getMaterial() == Material.air && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock().getMaterial() != Material.air)
            {
                this.setBlockState(worldIn, this.material.get(CHURCH_STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, structureBoundingBoxIn);
            }

            for (int l = 0; l < 9; ++l)
            {
                for (int k = 0; k < 5; ++k)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, k, 12, l, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, this.material.get(CHURCH_BLOCK).get(), k, -1, l, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1);
            return true;
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return 2;
        }
    }

    public static class Field1 extends StructureVillagePiecesRSG.Village
    {
        /** First crop type for this field. */
        private Block cropTypeA;
        /** Second crop type for this field. */
        private Block cropTypeB;
        /** Third crop type for this field. */
        private Block cropTypeC;
        /** Fourth crop type for this field. */
        private Block cropTypeD;

        public Field1(StructureVillagePiecesRSG.Start start, int p_i45570_2_, Random rand, StructureBoundingBox p_i45570_4_, EnumFacing facing)
        {
            super(start, p_i45570_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45570_4_;
            this.cropTypeA = this.func_151559_a(rand);
            this.cropTypeB = this.func_151559_a(rand);
            this.cropTypeC = this.func_151559_a(rand);
            this.cropTypeD = this.func_151559_a(rand);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
            tagCompound.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
            tagCompound.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
            tagCompound.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
            this.cropTypeC = Block.getBlockById(tagCompound.getInteger("CC"));
            this.cropTypeD = Block.getBlockById(tagCompound.getInteger("CD"));
        }

        private Block func_151559_a(Random rand)
        {
            return this.material.get(CROPS).get().getBlock();
        }

        public static StructureVillagePiecesRSG.Field1 func_175851_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175851_1_, Random rand, int p_175851_3_, int p_175851_4_, int p_175851_5_, EnumFacing facing, int p_175851_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175851_3_, p_175851_4_, p_175851_5_, 0, 0, 0, 13, 4, 9, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175851_1_, structureboundingbox) == null ? new StructureVillagePiecesRSG.Field1(start, p_175851_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 4 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 12, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, this.material.get(FARMLAND).get(), this.material.get(FARMLAND).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, this.material.get(FARMLAND).get(), this.material.get(FARMLAND).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 1, 8, 0, 7, this.material.get(FARMLAND).get(), this.material.get(FARMLAND).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 0, 1, 11, 0, 7, this.material.get(FARMLAND).get(), this.material.get(FARMLAND).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 0, 12, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 11, 0, 0, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 11, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 1, 9, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);

            for (int i = 1; i <= 7; ++i)
            {
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 1, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 2, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 4, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 5, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 7, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 8, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 10, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 11, 1, i, structureBoundingBoxIn);
            }

            for (int k = 0; k < 9; ++k)
            {
                for (int j = 0; j < 13; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 4, k, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, Blocks.dirt.getDefaultState(), j, -1, k, structureBoundingBoxIn);
                }
            }

            return true;
        }
    }

    public static class Field2 extends StructureVillagePiecesRSG.Village
    {
        /** First crop type for this field. */
        private Block cropTypeA;
        /** Second crop type for this field. */
        private Block cropTypeB;

        public Field2(StructureVillagePiecesRSG.Start start, int p_i45569_2_, Random rand, StructureBoundingBox p_i45569_4_, EnumFacing facing)
        {
            super(start, p_i45569_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45569_4_;
            this.cropTypeA = this.func_151560_a(rand);
            this.cropTypeB = this.func_151560_a(rand);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
            tagCompound.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
        }

        private Block func_151560_a(Random rand)
        {
            return this.material
		            .get(CROPS)
		            .get()
		            .getBlock();
        }

        public static StructureVillagePiecesRSG.Field2 func_175852_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175852_1_, Random rand, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ? new StructureVillagePiecesRSG.Field2(start, p_175852_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 4 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 6, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, this.material.get(FARMLAND).get(), this.material.get(FARMLAND).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, this.material.get(FARMLAND).get(), this.material.get(FARMLAND).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 5, 0, 0, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 5, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);

            for (int i = 1; i <= 7; ++i)
            {
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 1, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 2, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 4, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 5, 1, i, structureBoundingBoxIn);
            }

            for (int k = 0; k < 9; ++k)
            {
                for (int j = 0; j < 7; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 4, k, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, Blocks.dirt.getDefaultState(), j, -1, k, structureBoundingBoxIn);
                }
            }

            return true;
        }
    }

    public static class Hall extends StructureVillagePiecesRSG.Village
    {

        public Hall(StructureVillagePiecesRSG.Start start, int p_i45567_2_, Random rand, StructureBoundingBox p_i45567_4_, EnumFacing facing)
        {
            super(start, p_i45567_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45567_4_;
        }

        public static StructureVillagePiecesRSG.Hall func_175857_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175857_1_, Random rand, int p_175857_3_, int p_175857_4_, int p_175857_5_, EnumFacing facing, int p_175857_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175857_3_, p_175857_4_, p_175857_5_, 0, 0, 0, 9, 7, 11, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175857_1_, structureboundingbox) == null ? new StructureVillagePiecesRSG.Hall(start, p_175857_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 7 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 6, 8, 0, 10, this.material.get(GARDEN_GROUND).get(), this.material.get(GARDEN_GROUND).get(), false);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 6, 0, 6, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 2, 1, 10, this.material.get(FENCE).get(), this.material.get(FENCE).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 6, 8, 1, 10, this.material.get(FENCE).get(), this.material.get(FENCE).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 10, 7, 1, 10, this.material.get(FENCE).get(), this.material.get(FENCE).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 7, 0, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 3, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 8, 3, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 7, 1, 0, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 7, 1, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 3, 0, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 7, 3, 5, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 8, 4, 1, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 4, 8, 4, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 2, 8, 5, 3, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 0, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 0, 4, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 8, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 8, 4, 3, structureBoundingBoxIn);
            int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

            for (int k = -1; k <= 2; ++k)
            {
                for (int l = 0; l <= 8; ++l)
                {
                    this.setBlockState(worldIn, this.material.get(ROOF).get(i), l, 4 + k, k, structureBoundingBoxIn);
                    this.setBlockState(worldIn, this.material.get(ROOF).get(j), l, 4 + k, 5 - k, structureBoundingBoxIn);
                }
            }

            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 0, 2, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 0, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 8, 2, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 8, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 3, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 5, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 6, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FENCE).get(), 2, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(TABLE_TOP).get(), 2, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 1, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHAIR).get(this.getMetadataWithOffset(Blocks.oak_stairs, 3)), 2, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHAIR).get(this.getMetadataWithOffset(Blocks.oak_stairs, 1)), 1, 1, 3, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 1, 7, 0, 3, this.material.get(CHOPPING_BLOCK).get(), this.material.get(CHOPPING_BLOCK).get(), false);
            this.setBlockState(worldIn, this.material.get(CHOPPING_BLOCK).get(), 6, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHOPPING_BLOCK).get(), 6, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, structureBoundingBoxIn);
            this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

            if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getBlock().getMaterial() == Material.air && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock().getMaterial() != Material.air)
            {
                this.setBlockState(worldIn, this.material.get(STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, structureBoundingBoxIn);
            }

            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 6, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 6, 3, 4, structureBoundingBoxIn);
            this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 6, 1, 5, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

            for (int i1 = 0; i1 < 5; ++i1)
            {
                for (int j1 = 0; j1 < 9; ++j1)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j1, 7, i1, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, this.material.get(FOUNDATION).get(), j1, -1, i1, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
            return true;
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return p_180779_1_ == 0 ? 4 : super.func_180779_c(p_180779_1_, p_180779_2_);
        }
    }

    public static class House1 extends StructureVillagePiecesRSG.Village
    {
        public House1(StructureVillagePiecesRSG.Start start, int p_i45571_2_, Random rand, StructureBoundingBox p_i45571_4_, EnumFacing facing)
        {
            super(start, p_i45571_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45571_4_;
        }

        public static StructureVillagePiecesRSG.House1 func_175850_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175850_1_, Random rand, int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing facing, int p_175850_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, 9, 9, 6, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175850_1_, structureboundingbox) == null ? new StructureVillagePiecesRSG.House1(start, p_175850_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 9 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 8, 0, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 8, 5, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 1, 8, 6, 4, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 7, 2, 8, 7, 3, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

            for (int k = -1; k <= 2; ++k)
            {
                for (int l = 0; l <= 8; ++l)
                {
                    this.setBlockState(worldIn, this.material.get(ROOF).get(i), l, 6 + k, k, structureBoundingBoxIn);
                    this.setBlockState(worldIn, this.material.get(ROOF).get(j), l, 6 + k, 5 - k, structureBoundingBoxIn);
                }
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 8, 1, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 0, 8, 1, 4, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 0, 7, 1, 0, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 4, 0, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 5, 0, 4, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 5, 8, 4, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 0, 8, 4, 0, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 1, 0, 4, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 7, 4, 5, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 1, 8, 4, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 4, 0, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 5, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 6, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 5, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 6, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 3, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 3, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 3, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 5, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 6, 2, 5, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 7, 4, 1, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 4, 7, 4, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 4, 7, 3, 4, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 7, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(ROOF).get(this.getMetadataWithOffset(Blocks.oak_stairs, 0)), 7, 1, 3, structureBoundingBoxIn);
            int j1 = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            this.setBlockState(worldIn, this.material.get(ROOF).get(j1), 6, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(ROOF).get(j1), 5, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(ROOF).get(j1), 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(ROOF).get(j1), 3, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FENCE).get(), 6, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(TABLE_TOP).get(), 6, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FENCE).get(), 4, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(TABLE_TOP).get(), 4, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.crafting_table.getDefaultState(), 7, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 2, 0, structureBoundingBoxIn);
            this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

            if (this.getBlockStateFromPos(worldIn, 1, 0, -1, structureBoundingBoxIn).getBlock().getMaterial() == Material.air && this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getBlock().getMaterial() != Material.air)
            {
                this.setBlockState(worldIn, this.material.get(STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, structureBoundingBoxIn);
            }

            for (int k1 = 0; k1 < 6; ++k1)
            {
                for (int i1 = 0; i1 < 9; ++i1)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i1, 9, k1, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, this.material.get(FOUNDATION).get(), i1, -1, k1, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1);
            return true;
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return 1;
        }
    }

    public static class House2 extends StructureVillagePiecesRSG.Village
    {
        private static final List<WeightedRandomChestContent> villageBlacksmithChestContents = Lists.newArrayList(new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.sapling), 0, 3, 7, 5), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)});
        private boolean hasMadeChest;

        static
        {
            net.minecraftforge.common.ChestGenHooks.init(net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH, villageBlacksmithChestContents, 3, 8);
        }

        public House2(StructureVillagePiecesRSG.Start start, int p_i45563_2_, Random rand, StructureBoundingBox p_i45563_4_, EnumFacing facing)
        {
            super(start, p_i45563_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45563_4_;
        }

        public static StructureVillagePiecesRSG.House2 func_175855_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175855_1_, Random rand, int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing facing, int p_175855_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0, 10, 6, 7, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175855_1_, structureboundingbox) == null ? new StructureVillagePiecesRSG.House2(start, p_175855_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Chest", this.hasMadeChest);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.hasMadeChest = tagCompound.getBoolean("Chest");
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 6 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 9, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 9, 0, 6, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 9, 4, 6, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 9, 5, 6, this.material.get(BLACKSMITH_ROOF).get(), this.material.get(BLACKSMITH_ROOF).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 8, 5, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 2, 3, 0, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 4, 0, this.material.get(CORNER).get(), this.material.get(CORNER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 0, 3, 4, 0, this.material.get(CORNER).get(), this.material.get(CORNER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 6, 0, 4, 6, this.material.get(CORNER).get(), this.material.get(CORNER).get(), false);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 3, 3, 1, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 2, 3, 3, 2, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 3, 5, 3, 3, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 5, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 6, 5, 3, 6, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 0, 5, 3, 0, this.material.get(FENCE).get(), this.material.get(FENCE).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 0, 9, 3, 0, this.material.get(FENCE).get(), this.material.get(FENCE).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 4, 9, 4, 6, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.setBlockState(worldIn, Blocks.flowing_lava.getDefaultState(), 7, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.flowing_lava.getDefaultState(), 8, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.iron_bars.getDefaultState(), 9, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.iron_bars.getDefaultState(), 9, 2, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 2, 4, 8, 2, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 6, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.furnace.getDefaultState(), 6, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.furnace.getDefaultState(), 6, 3, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(ANVIL).get(), 8, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FENCE).get(), 2, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(TABLE_TOP).get(), 2, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 1, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHAIR).get(this.getMetadataWithOffset(Blocks.oak_stairs, 3)), 2, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(CHAIR).get(this.getMetadataWithOffset(Blocks.oak_stairs, 1)), 1, 1, 4, structureBoundingBoxIn);

            if (!this.hasMadeChest && structureBoundingBoxIn.isVecInside(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5))))
            {
                this.hasMadeChest = true;
                this.generateChestContents(worldIn, structureBoundingBoxIn, randomIn, 5, 1, 5, net.minecraftforge.common.ChestGenHooks.getItems(net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH, randomIn), net.minecraftforge.common.ChestGenHooks.getCount(net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH, randomIn));
            }

            for (int i = 6; i <= 8; ++i)
            {
                if (this.getBlockStateFromPos(worldIn, i, 0, -1, structureBoundingBoxIn).getBlock().getMaterial() == Material.air && this.getBlockStateFromPos(worldIn, i, -1, -1, structureBoundingBoxIn).getBlock().getMaterial() != Material.air)
                {
                    this.setBlockState(worldIn, this.material.get(STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), i, 0, -1, structureBoundingBoxIn);
                }
            }

            for (int k = 0; k < 7; ++k)
            {
                for (int j = 0; j < 10; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 6, k, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, this.material.get(FOUNDATION).get(), j, -1, k, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 7, 1, 1, 1);
            return true;
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return 3;
        }
    }

    public static class House3 extends StructureVillagePiecesRSG.Village
    {

        public House3(StructureVillagePiecesRSG.Start start, int p_i45561_2_, Random rand, StructureBoundingBox p_i45561_4_, EnumFacing facing)
        {
            super(start, p_i45561_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45561_4_;
        }

        public static StructureVillagePiecesRSG.House3 func_175849_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175849_1_, Random rand, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing facing, int p_175849_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 9, 7, 12, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null ? new StructureVillagePiecesRSG.House3(start, p_175849_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 7 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 5, 8, 0, 10, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 7, 0, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 3, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 8, 3, 10, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 7, 2, 0, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 2, 1, 5, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 6, 2, 3, 10, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 10, 7, 3, 10, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 3, 0, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 2, 3, 5, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 8, 4, 1, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 4, 3, 4, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 2, 8, 5, 3, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 0, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 0, 4, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 8, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 8, 4, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 8, 4, 4, structureBoundingBoxIn);
            int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);

            for (int k = -1; k <= 2; ++k)
            {
                for (int l = 0; l <= 8; ++l)
                {
                    this.setBlockState(worldIn, this.material.get(ROOF).get(i), l, 4 + k, k, structureBoundingBoxIn);

                    if ((k > -1 || l <= 1) && (k > 0 || l <= 3) && (k > 1 || l <= 4 || l >= 6))
                    {
                        this.setBlockState(worldIn, this.material.get(ROOF).get(j), l, 4 + k, 5 - k, structureBoundingBoxIn);
                    }
                }
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 4, 5, 3, 4, 10, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 4, 2, 7, 4, 10, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 4, 4, 5, 10, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 5, 4, 6, 5, 10, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 6, 3, 5, 6, 10, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            int k1 = this.getMetadataWithOffset(Blocks.oak_stairs, 0);

            for (int l1 = 4; l1 >= 1; --l1)
            {
                this.setBlockState(worldIn, this.material.get(WALL).get(), l1, 2 + l1, 7 - l1, structureBoundingBoxIn);

                for (int i1 = 8 - l1; i1 <= 10; ++i1)
                {
                    this.setBlockState(worldIn, this.material.get(ROOF).get(k1), l1, 2 + l1, i1, structureBoundingBoxIn);
                }
            }

            int i2 = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 6, 6, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 7, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(ROOF).get(i2), 6, 6, 4, structureBoundingBoxIn);

            for (int j2 = 6; j2 <= 8; ++j2)
            {
                for (int j1 = 5; j1 <= 10; ++j1)
                {
                    this.setBlockState(worldIn, this.material.get(ROOF).get(i2), j2, 12 - j2, j1, structureBoundingBoxIn);
                }
            }

            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 0, 2, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 0, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 5, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 6, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 8, 2, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 8, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 8, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 8, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 2, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 8, 2, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 8, 2, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 2, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 2, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 2, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 2, 2, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 4, 4, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 5, 4, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW_SHUTTERS).get(), 6, 4, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 5, 5, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, structureBoundingBoxIn);
            this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, -1, 3, 2, -1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

            if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getBlock().getMaterial() == Material.air && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock().getMaterial() != Material.air)
            {
                this.setBlockState(worldIn, this.material.get(STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, structureBoundingBoxIn);
            }

            for (int k2 = 0; k2 < 5; ++k2)
            {
                for (int i3 = 0; i3 < 9; ++i3)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i3, 7, k2, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, this.material.get(FOUNDATION).get(), i3, -1, k2, structureBoundingBoxIn);
                }
            }

            for (int l2 = 5; l2 < 11; ++l2)
            {
                for (int j3 = 2; j3 < 9; ++j3)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j3, 7, l2, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, this.material.get(FOUNDATION).get(), j3, -1, l2, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
            return true;
        }
    }

    public static class House4Garden extends StructureVillagePiecesRSG.Village
    {
        private boolean isRoofAccessible;

        public House4Garden(StructureVillagePiecesRSG.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
        {
            super(start, p_i45566_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Terrace", this.isRoofAccessible);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.isRoofAccessible = tagCompound.getBoolean("Terrace");
        }

        public static StructureVillagePiecesRSG.House4Garden func_175858_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 5, 6, 5, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new StructureVillagePiecesRSG.House4Garden(start, p_175858_7_, rand, structureboundingbox, facing);
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 6 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 0, 4, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 4, 4, 4, this.material.get(HUT_ROOF).get(), this.material.get(HUT_ROOF).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 3, 4, 3, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 0, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 0, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 0, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 4, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 0, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 0, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 0, 3, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 4, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(FOUNDATION).get(), 4, 3, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 3, 3, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 2, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 4, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 1, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 1, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 2, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 3, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 3, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WALL).get(), 3, 1, 0, structureBoundingBoxIn);

            if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getBlock().getMaterial() == Material.air && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock().getMaterial() != Material.air)
            {
                this.setBlockState(worldIn, this.material.get(STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, structureBoundingBoxIn);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

            if (this.isRoofAccessible)
            {
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 0, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 1, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 2, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 3, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 4, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 0, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 1, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 2, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 3, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 4, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 4, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 4, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 4, 5, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 0, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 0, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(FENCE).get(), 0, 5, 3, structureBoundingBoxIn);
            }

            if (this.isRoofAccessible)
            {
                int i = this.getMetadataWithOffset(Blocks.ladder, 3);
                this.setBlockState(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 1, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 2, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 3, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 4, 3, structureBoundingBoxIn);
            }

            this.setBlockState(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, structureBoundingBoxIn);

            for (int k = 0; k < 5; ++k)
            {
                for (int j = 0; j < 5; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 6, k, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, this.material.get(FOUNDATION).get(), j, -1, k, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1);
            return true;
        }
    }

    public static class Path extends StructureVillagePiecesRSG.Road
    {
        private int length;

        public Path(StructureVillagePiecesRSG.Start start, int p_i45562_2_, Random rand, StructureBoundingBox p_i45562_4_, EnumFacing facing)
        {
            super(start, p_i45562_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45562_4_;
            this.length = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("Length", this.length);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.length = tagCompound.getInteger("Length");
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            boolean flag = false;

            for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5))
            {
                StructureComponent structurecomponent = this.getNextComponentNN((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, 0, i);

                if (structurecomponent != null)
                {
                    i += Math.max(structurecomponent.getBoundingBox()
                            .getXSize(), structurecomponent.getBoundingBox()
                            .getZSize());
                    flag = true;
                }
            }

            for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5))
            {
                StructureComponent structurecomponent1 = this.getNextComponentPP((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, 0, j);

                if (structurecomponent1 != null)
                {
                    j += Math.max(structurecomponent1.getBoundingBox()
                            .getXSize(), structurecomponent1.getBoundingBox()
                            .getZSize());
                    flag = true;
                }
            }

            if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                    case NORTH:
                        StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                                .minX - 1, this.getBoundingBox()
                                .minY, this.getBoundingBox()
                                .minZ, EnumFacing.WEST, this.getComponentType());
                        break;
                    case SOUTH:
                        StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                                .minX - 1, this.getBoundingBox()
                                .minY, this.getBoundingBox()
                                .maxZ - 2, EnumFacing.WEST, this.getComponentType());
                        break;
                    case WEST:
                        StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                                .minX, this.getBoundingBox()
                                .minY, this.getBoundingBox()
                                .minZ - 1, EnumFacing.NORTH, this.getComponentType());
                        break;
                    case EAST:
                        StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                                .maxX - 2, this.getBoundingBox()
                                .minY, this.getBoundingBox()
                                .minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }

            if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                    case NORTH:
                        StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                                .maxX + 1, this.getBoundingBox()
                                .minY, this.getBoundingBox()
                                .minZ, EnumFacing.EAST, this.getComponentType());
                        break;
                    case SOUTH:
                        StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                                .maxX + 1, this.getBoundingBox()
                                .minY, this.getBoundingBox()
                                .maxZ - 2, EnumFacing.EAST, this.getComponentType());
                        break;
                    case WEST:
                        StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                                .minX, this.getBoundingBox()
                                .minY, this.getBoundingBox()
                                .maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                        break;
                    case EAST:
                        StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                                .maxX - 2, this.getBoundingBox()
                                .minY, this.getBoundingBox()
                                .maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
        }

        public static StructureBoundingBox func_175848_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175848_1_, Random rand, int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing facing)
        {
            for (int i = 7 * MathHelper.getRandomIntegerInRange(rand, 3, 5); i >= 7; i -= 7)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0, 0, 3, 3, i, facing);

                if (StructureComponent.findIntersecting(p_175848_1_, structureboundingbox) == null)
                {
                    return structureboundingbox;
                }
            }

            return null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            IBlockState iblockstate = this.func_175847_a(this.material.get(PATH).get());
            IBlockState iblockstate1 = this.func_175847_a(this.material.get(FOUNDATION).get());

            for (int i = this.getBoundingBox()
                    .minX; i <= this.getBoundingBox()
                    .maxX; ++i)
            {
                for (int j = this.getBoundingBox()
                        .minZ; j <= this.getBoundingBox()
                        .maxZ; ++j)
                {
                    BlockPos blockpos = new BlockPos(i, 64, j);

                    if (structureBoundingBoxIn.isVecInside(blockpos))
                    {
                        blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos).down();
                        if (worldIn.setBlockState(blockpos, iblockstate, 2))
                            worldIn.setBlockState(blockpos.down(), iblockstate1, 2);
                    }
                }
            }

            return true;
        }
    }

    public abstract static class Road extends StructureVillagePiecesRSG.Village
    {

        protected Road(StructureVillagePiecesRSG.Start start, int type)
        {
            super(start, type);
        }
    }

    public static class Start extends StructureVillagePieces.Start
    {
        public WorldChunkManager worldChunkMngr;
        /** Boolean that determines if the village is in a desert or not. */
        public boolean inDesert;
        /** World terrain type, 0 for normal, 1 for flap map */
        public int terrainType;
        public StructureVillagePieces.PieceWeight structVillagePieceWeight;
        public List<PieceWeight> structureVillageWeightedPieceList;
        public List<StructureComponent> field_74932_i = Lists.<StructureComponent>newArrayList();
        public List<StructureComponent> field_74930_j = Lists.<StructureComponent>newArrayList();
        public BiomeGenBase biome;
        VillageMaterial material;

        public Start(WorldChunkManager chunkManagerIn, int p_i2104_2_, Random rand, int p_i2104_4_, int p_i2104_5_, List<PieceWeight> p_i2104_6_, int p_i2104_7_)
        {
            super(chunkManagerIn, p_i2104_2_, rand, p_i2104_4_, p_i2104_5_, p_i2104_6_, p_i2104_7_);
            try {
                this.worldChunkMngr =  chunkManagerIn;
            } catch (Exception e) {
                Logger.fatal("This should NOT have happened! how did you get RTG villages and a different ChunkManager?", e);
            }
            this.structureVillageWeightedPieceList = p_i2104_6_;
            this.terrainType = p_i2104_7_;
            BiomeGenBase biomegenbase = chunkManagerIn.getBiomeGenerator(new BlockPos(p_i2104_4_, 0, p_i2104_5_), BiomeGenBase.field_180279_ad);
            this.inDesert = biomegenbase == BiomeGenBase.desert || biomegenbase == BiomeGenBase.desertHills;
            this.biome = biomegenbase;
            this.func_175846_a(this.inDesert);
            this.material = VillageMaterial.getForBiome(this.biome);
        }

        public WorldChunkManager getWorldChunkManager()
        {
            return this.worldChunkMngr;
        }
        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
            StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
            StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 3, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, 12, 4, this.material.get(WELL_BLOCK).get(), Blocks.flowing_water.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 12, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 3, 12, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 12, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 3, 12, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 1, 13, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 1, 14, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 4, 13, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 4, 14, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 1, 13, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 1, 14, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 4, 13, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 4, 14, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 15, 1, 4, 15, 4, this.material.get(WELL_BLOCK).get(), this.material.get(WELL_BLOCK).get(), false);

            for (int i = 0; i <= 5; ++i)
            {
                for (int j = 0; j <= 5; ++j)
                {
                    if (j == 0 || j == 5 || i == 0 || i == 5)
                    {
                        this.setBlockState(worldIn, this.material.get(PATH).get(), j, 11, i, structureBoundingBoxIn);
                        this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, structureBoundingBoxIn);
                    }
                }
            }

            return true;
        }
    }

    public static class Torch extends StructureVillagePiecesRSG.Village
    {

        public Torch(StructureVillagePiecesRSG.Start start, int p_i45568_2_, Random rand, StructureBoundingBox p_i45568_4_, EnumFacing facing)
        {
            super(start, p_i45568_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45568_4_;
        }

        public static StructureBoundingBox func_175856_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175856_1_, Random rand, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing facing)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 3, 4, 2, facing);
            return StructureComponent.findIntersecting(p_175856_1_, structureboundingbox) != null ? null : structureboundingbox;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 4 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 2, 3, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.setBlockState(worldIn, this.material.get(LAMP_POST).get(), 1, 0, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(LAMP_POST).get(), 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(LAMP_POST).get(), 1, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(LAMP_BLOCK).get(), 1, 3, 0, structureBoundingBoxIn);
            boolean flag = this.coordBaseMode == EnumFacing.EAST || this.coordBaseMode == EnumFacing.NORTH;
            this.setBlockState(worldIn, this.material.get(LAMP_TORCH).get().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), flag ? 2 : 0, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(LAMP_TORCH).get().withProperty(BlockTorch.FACING, this.coordBaseMode), 1, 3, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(LAMP_TORCH).get().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), flag ? 0 : 2, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(LAMP_TORCH).get().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 1, 3, -1, structureBoundingBoxIn);

            return true;
        }
    }

    public abstract static class Village extends StructureVillagePieces.Village
    {
        protected int field_143015_k = -1;
        /** The number of villagers that have been spawned in this component. */
        private int villagersSpawned;
        private boolean isDesertVillage;
        private StructureVillagePiecesRSG.Start startPiece;
        public BiomeGenBase biome;
        protected VillageMaterial material;

        protected Village(StructureVillagePiecesRSG.Start start, int type)
        {
            super(start, type);

            if (start != null)
            {
                this.isDesertVillage = start.inDesert;
                startPiece = start;
                biome = start.biome;
                material = VillageMaterial.getForBiome(biome);
	            if( !material.generate ) {
		            Logger.error("How did you do that? we don't do villages in this biome!");
	            }
            }
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setInteger("HPos", this.field_143015_k);
            tagCompound.setInteger("VCount", this.villagersSpawned);
            tagCompound.setBoolean("Desert", this.isDesertVillage);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            this.field_143015_k = tagCompound.getInteger("HPos");
            this.villagersSpawned = tagCompound.getInteger("VCount");
            this.isDesertVillage = tagCompound.getBoolean("Desert");
        }

        /**
         * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentNN(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_74891_2_, Random rand, int p_74891_4_, int p_74891_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                    case NORTH:
                        return StructureVillagePiecesRSG.func_176066_d(start, p_74891_2_, rand, this.getBoundingBox()
                                .minX - 1, this.getBoundingBox()
                                .minY + p_74891_4_, this.getBoundingBox()
                                .minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                    case SOUTH:
                        return StructureVillagePiecesRSG.func_176066_d(start, p_74891_2_, rand, this.getBoundingBox()
                                .minX - 1, this.getBoundingBox()
                                .minY + p_74891_4_, this.getBoundingBox()
                                .minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                    case WEST:
                        return StructureVillagePiecesRSG.func_176066_d(start, p_74891_2_, rand, this.getBoundingBox()
                                .minX + p_74891_5_, this.getBoundingBox()
                                .minY + p_74891_4_, this.getBoundingBox()
                                .minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    case EAST:
                        return StructureVillagePiecesRSG.func_176066_d(start, p_74891_2_, rand, this.getBoundingBox()
                                .minX + p_74891_5_, this.getBoundingBox()
                                .minY + p_74891_4_, this.getBoundingBox()
                                .minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }

            return null;
        }

        /**
         * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentPP(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_74894_2_, Random rand, int p_74894_4_, int p_74894_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (this.coordBaseMode)
                {
                    case NORTH:
                        return StructureVillagePiecesRSG.func_176066_d(start, p_74894_2_, rand, this.getBoundingBox()
                                .maxX + 1, this.getBoundingBox()
                                .minY + p_74894_4_, this.getBoundingBox()
                                .minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                    case SOUTH:
                        return StructureVillagePiecesRSG.func_176066_d(start, p_74894_2_, rand, this.getBoundingBox()
                                .maxX + 1, this.getBoundingBox()
                                .minY + p_74894_4_, this.getBoundingBox()
                                .minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                    case WEST:
                        return StructureVillagePiecesRSG.func_176066_d(start, p_74894_2_, rand, this.getBoundingBox()
                                .minX + p_74894_5_, this.getBoundingBox()
                                .minY + p_74894_4_, this.getBoundingBox()
                                .maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    case EAST:
                        return StructureVillagePiecesRSG.func_176066_d(start, p_74894_2_, rand, this.getBoundingBox()
                                .minX + p_74894_5_, this.getBoundingBox()
                                .minY + p_74894_4_, this.getBoundingBox()
                                .maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }

            return null;
        }

        /**
         * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of
         * all the levels in the BB's horizontal rectangle).
         */
        protected int getAverageGroundLevel(World worldIn, StructureBoundingBox p_74889_2_)
        {
            int i = 0;
            int j = 0;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int k = this.getBoundingBox()
                    .minZ; k <= this.getBoundingBox()
                    .maxZ; ++k)
            {
                for (int l = this.getBoundingBox()
                        .minX; l <= this.getBoundingBox()
                        .maxX; ++l)
                {
                    blockpos$mutableblockpos.set(l, 64, k);

                    if (p_74889_2_.isVecInside(blockpos$mutableblockpos))
                    {
                        i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel());
                        ++j;
                    }
                }
            }

            if (j == 0)
            {
                return -1;
            }
            else
            {
                return i / j;
            }
        }

        protected static boolean canVillageGoDeeper(StructureBoundingBox p_74895_0_)
        {
            return p_74895_0_ != null && p_74895_0_.minY > 10;
        }

        /**
         * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y
         * offset, z offset, number of villagers
         */
        protected void spawnVillagers(World worldIn, StructureBoundingBox p_74893_2_, int p_74893_3_, int p_74893_4_, int p_74893_5_, int p_74893_6_)
        {
            if (this.villagersSpawned < p_74893_6_)
            {
                for (int i = this.villagersSpawned; i < p_74893_6_; ++i)
                {
                    int j = this.getXWithOffset(p_74893_3_ + i, p_74893_5_);
                    int k = this.getYWithOffset(p_74893_4_);
                    int l = this.getZWithOffset(p_74893_3_ + i, p_74893_5_);

                    if (!p_74893_2_.isVecInside(new BlockPos(j, k, l)))
                    {
                        break;
                    }

                    ++this.villagersSpawned;
                    EntityVillager entityvillager = new EntityVillager(worldIn);
                    entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null);
                    entityvillager.setProfession(this.func_180779_c(i, entityvillager.getProfession()));
                    worldIn.spawnEntityInWorld(entityvillager);
                }
            }
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return p_180779_2_;
        }

        protected IBlockState func_175847_a(IBlockState p_175847_1_)
        {
            net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID event = new net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID(startPiece == null ? null : startPiece.biome, p_175847_1_);
            net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
            if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.replacement;

            return p_175847_1_;
        }

        protected void setBlockState(World worldIn, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn)
        {
            IBlockState iblockstate = this.func_175847_a(blockstateIn);
            super.setBlockState(worldIn, iblockstate, x, y, z, boundingboxIn);
        }

        /**
         * Fill the given area with the selected blocks
         */
        protected void fillWithBlocks(World worldIn, StructureBoundingBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, IBlockState boundaryBlockState, IBlockState insideBlockState, boolean existingOnly)
        {
            IBlockState iblockstate = this.func_175847_a(boundaryBlockState);
            IBlockState iblockstate1 = this.func_175847_a(insideBlockState);
            super.fillWithBlocks(worldIn, boundingboxIn, xMin, yMin, zMin, xMax, yMax, zMax, iblockstate, iblockstate1, existingOnly);
        }

        /**
         * Replaces air and liquid from given position downwards. Stops when hitting anything else than air or
         * liquid
         */
        protected void replaceAirAndLiquidDownwards(World worldIn, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn)
        {
            IBlockState iblockstate = this.func_175847_a(blockstateIn);
            super.replaceAirAndLiquidDownwards(worldIn, iblockstate, x, y, z, boundingboxIn);
        }

        protected void func_175846_a(boolean p_175846_1_)
        {
            this.isDesertVillage = p_175846_1_;
        }
        /**
         * Places door on given position
         */
        protected void placeDoorCurrentPosition(World worldIn, StructureBoundingBox boundingBoxIn, Random rand, int x, int y, int z, EnumFacing facing)
        {
            BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
            Block door = func_175847_a(this.material.get(DOOR).get()).getBlock();

            if (boundingBoxIn.isVecInside(blockpos))
            {
                ItemDoor.placeDoor(worldIn, blockpos, facing.rotateYCCW(), door);
            }
        }
    }

    public static class Well extends StructureVillagePiecesRSG.Village
    {

        public Well(StructureVillagePiecesRSG.Start start, int p_i2109_2_, Random rand, int p_i2109_4_, int p_i2109_5_)
        {
            super(start, p_i2109_2_);
            this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(rand);

            switch (this.coordBaseMode)
            {
                case NORTH:
                case SOUTH:
                    this.boundingBox
                            = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
                    break;
                default:
                    this.boundingBox
                            = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
            }
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                    .minX - 1, this.getBoundingBox()
                    .maxY - 4, this.getBoundingBox()
                    .minZ + 1, EnumFacing.WEST, this.getComponentType());
            StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                    .maxX + 1, this.getBoundingBox()
                    .maxY - 4, this.getBoundingBox()
                    .minZ + 1, EnumFacing.EAST, this.getComponentType());
            StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                    .minX + 1, this.getBoundingBox()
                    .maxY - 4, this.getBoundingBox()
                    .minZ - 1, EnumFacing.NORTH, this.getComponentType());
            StructureVillagePiecesRSG.func_176069_e((StructureVillagePiecesRSG.Start)componentIn, listIn, rand, this.getBoundingBox()
                    .minX + 1, this.getBoundingBox()
                    .maxY - 4, this.getBoundingBox()
                    .maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 3, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, 12, 4, this.material.get(WELL_BLOCK).get(), Blocks.flowing_water.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 12, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 3, 12, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 2, 12, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 3, 12, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 1, 13, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 1, 14, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 4, 13, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 4, 14, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 1, 13, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 1, 14, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 4, 13, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WELL_POST).get(), 4, 14, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 15, 1, 4, 15, 4, this.material.get(WELL_BLOCK).get(), this.material.get(WELL_BLOCK).get(), false);

            for (int i = 0; i <= 5; ++i)
            {
                for (int j = 0; j <= 5; ++j)
                {
                    if (j == 0 || j == 5 || i == 0 || i == 5)
                    {
                        this.setBlockState(worldIn, this.material.get(PATH).get(), j, 11, i, structureBoundingBoxIn);
                        this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, structureBoundingBoxIn);
                    }
                }
            }

            return true;
        }
    }

    public static class WoodHut extends StructureVillagePiecesRSG.Village
    {
        private boolean isTallHouse;
        private int tablePosition;

        public WoodHut(StructureVillagePiecesRSG.Start start, int p_i45565_2_, Random rand, StructureBoundingBox p_i45565_4_, EnumFacing facing)
        {
            super(start, p_i45565_2_);
            this.coordBaseMode = facing;
            this.boundingBox = p_i45565_4_;
            this.isTallHouse = rand.nextBoolean();
            this.tablePosition = rand.nextInt(3);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("T", this.tablePosition);
            tagCompound.setBoolean("C", this.isTallHouse);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound)
        {
            super.readStructureFromNBT(tagCompound);
            this.tablePosition = tagCompound.getInteger("T");
            this.isTallHouse = tagCompound.getBoolean("C");
        }

        public static StructureVillagePiecesRSG.WoodHut func_175853_a(StructureVillagePiecesRSG.Start start, List<StructureComponent> p_175853_1_, Random rand, int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing facing, int p_175853_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, 4, 6, 5, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175853_1_, structureboundingbox) == null ? new StructureVillagePiecesRSG.WoodHut(start, p_175853_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.getBoundingBox()
                        .offset(0, this.field_143015_k - this.getBoundingBox()
                                .maxY + 6 - 1, 0);
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 3, 0, 4, this.material.get(FOUNDATION).get(), this.material.get(FOUNDATION).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 3, this.material.get(GARDEN_GROUND).get(), this.material.get(GARDEN_GROUND).get(), false);

            if (this.isTallHouse)
            {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 2, 4, 3, this.material.get(HUT_ROOF).get(), this.material.get(HUT_ROOF).get(), false);
            }
            else
            {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 2, 5, 3, this.material.get(HUT_ROOF).get(), this.material.get(HUT_ROOF).get(), false);
            }

            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 1, 4, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 2, 4, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 1, 4, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 2, 4, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 0, 4, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 0, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 0, 4, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 3, 4, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 3, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(HUT_ROOF).get(), 3, 4, 3, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 3, 0, this.material.get(CORNER).get(), this.material.get(CORNER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 0, 3, 3, 0, this.material.get(CORNER).get(), this.material.get(CORNER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 4, 0, 3, 4, this.material.get(CORNER).get(), this.material.get(CORNER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 4, 3, 3, 4, this.material.get(CORNER).get(), this.material.get(CORNER).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 1, 3, 3, 3, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 2, 3, 0, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 2, 3, 4, this.material.get(WALL).get(), this.material.get(WALL).get(), false);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, this.material.get(WINDOW).get(), 3, 2, 2, structureBoundingBoxIn);

            if (this.tablePosition > 0)
            {
                this.setBlockState(worldIn, this.material.get(FENCE).get(), this.tablePosition, 1, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.material.get(TABLE_TOP).get(), this.tablePosition, 2, 3, structureBoundingBoxIn);
            }

            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 2, 0, structureBoundingBoxIn);
            this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

            if (this.getBlockStateFromPos(worldIn, 1, 0, -1, structureBoundingBoxIn).getBlock().getMaterial() == Material.air && this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getBlock().getMaterial() != Material.air)
            {
                this.setBlockState(worldIn, this.material.get(STAIRS).get(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, structureBoundingBoxIn);
            }

            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 4; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 6, i, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, this.material.get(FOUNDATION).get(), j, -1, i, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 1);
            return true;
        }
    }

}