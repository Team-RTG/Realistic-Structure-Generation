package teamrtg.rsg.world.gen.structure.village;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import teamrtg.rsg.util.Logger;
import teamrtg.rsg.world.gen.structure.village.buildings.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.DOOR;

@SuppressWarnings("incomplete-switch")
public class StructureVillagePiecesRSG extends StructureVillagePieces
{
    public static void registerVillagePieces()
    {
        MapGenStructureIO.registerStructureComponent(VillagePieceHouse1.class, "ViBH_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceField1.class, "ViDF_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceField2.class, "ViF_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceTorch.class, "ViL_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceHall.class, "ViPH_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceHouse4Garden.class, "ViSH_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceWoodHut.class, "ViSmH_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceChurch.class, "ViST_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceHouse2.class, "ViS_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceStart.class, "ViStart_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePiecePath.class, "ViSR_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceHouse3.class, "ViTRH_RTG");
        MapGenStructureIO.registerStructureComponent(VillagePieceWell.class, "ViW_RTG");
        Logger.debug("Registered custom village pieces.");
    }

    public static List<PieceWeight> getStructureVillageWeightedPieceList(Random random, int p_75084_1_)
    {
        List<PieceWeight> list = Lists.<StructureVillagePieces.PieceWeight>newArrayList();
        list.add(new StructureVillagePieces.PieceWeight(VillagePieceHouse4Garden.class, 4, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(VillagePieceChurch.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 1 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(VillagePieceHouse1.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 2 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(VillagePieceWoodHut.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 5 + p_75084_1_ * 3)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(VillagePieceHall.class, 15, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 2 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(VillagePieceField1.class, 3, MathHelper.getRandomIntegerInRange(random, 1 + p_75084_1_, 4 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(VillagePieceField2.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(VillagePieceHouse2.class, 15, MathHelper.getRandomIntegerInRange(random, 0, 1 + p_75084_1_)));
        list.add(new StructureVillagePiecesRSG.PieceWeight(VillagePieceHouse3.class, 8, MathHelper.getRandomIntegerInRange(random, 0 + p_75084_1_, 3 + p_75084_1_ * 2)));
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

    private static StructureVillagePiecesRSG.Village func_176065_a(VillagePieceStart start, StructureVillagePieces.PieceWeight weight, List<StructureComponent> p_176065_2_, Random rand, int p_176065_4_, int p_176065_5_, int p_176065_6_, EnumFacing facing, int p_176065_8_)
    {
        Class <? extends StructureVillagePieces.Village > oclass = weight.villagePieceClass;
        StructureVillagePiecesRSG.Village structurevillagepieces$village = null;

        if (oclass == VillagePieceHouse4Garden.class)
        {
            structurevillagepieces$village = VillagePieceHouse4Garden.func_175858_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == VillagePieceChurch.class)
        {
            structurevillagepieces$village = VillagePieceChurch.func_175854_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == VillagePieceHouse1.class)
        {
            structurevillagepieces$village = VillagePieceHouse1.func_175850_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == VillagePieceWoodHut.class)
        {
            structurevillagepieces$village = VillagePieceWoodHut.func_175853_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == VillagePieceHall.class)
        {
            structurevillagepieces$village = VillagePieceHall.func_175857_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == VillagePieceField1.class)
        {
            structurevillagepieces$village = VillagePieceField1.func_175851_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == VillagePieceField2.class)
        {
            structurevillagepieces$village = VillagePieceField2.func_175852_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == VillagePieceHouse2.class)
        {
            structurevillagepieces$village = VillagePieceHouse2.func_175855_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else if (oclass == VillagePieceHouse3.class)
        {
            structurevillagepieces$village = VillagePieceHouse3.func_175849_a(start, p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }
        else
        {
            structurevillagepieces$village = (Village) net.minecraftforge.fml.common.registry.VillagerRegistry.getVillageComponent(weight, start , p_176065_2_, rand, p_176065_4_, p_176065_5_, p_176065_6_, facing, p_176065_8_);
        }

        return structurevillagepieces$village;
    }

    private static StructureVillagePiecesRSG.Village func_176067_c(VillagePieceStart start, List<StructureComponent> p_176067_1_, Random rand, int p_176067_3_, int p_176067_4_, int p_176067_5_, EnumFacing facing, int p_176067_7_)
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

            StructureBoundingBox structureboundingbox = VillagePieceTorch.func_175856_a(start, p_176067_1_, rand, p_176067_3_, p_176067_4_, p_176067_5_, facing);

            if (structureboundingbox != null)
            {
                return new VillagePieceTorch(start, p_176067_7_, rand, structureboundingbox, facing);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent func_176066_d(VillagePieceStart start, List<StructureComponent> p_176066_1_, Random rand, int p_176066_3_, int p_176066_4_, int p_176066_5_, EnumFacing facing, int p_176066_7_)
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

    public static StructureComponent func_176069_e(VillagePieceStart start, List<StructureComponent> p_176069_1_, Random rand, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing facing, int p_176069_7_)
    {
        if (p_176069_7_ > 3 + start.terrainType)
        {
            return null;
        }
        else if (Math.abs(p_176069_3_ - start.getBoundingBox().minX) <= 112 && Math.abs(p_176069_5_ - start.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = VillagePiecePath.func_175848_a(start, p_176069_1_, rand, p_176069_3_, p_176069_4_, p_176069_5_, facing);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
                StructureComponent structurecomponent = new VillagePiecePath(start, p_176069_7_, rand, structureboundingbox, facing);
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

    public abstract static class Road extends StructureVillagePiecesRSG.Village
    {

        protected Road(VillagePieceStart start, int type)
        {
            super(start, type);
        }
    }

    public abstract static class Village extends StructureVillagePieces.Village
    {
        protected int field_143015_k = -1;
        /** The number of villagers that have been spawned in this component. */
        private int villagersSpawned;
        private boolean isDesertVillage;
        private VillagePieceStart startPiece;
        public BiomeGenBase biome;
        protected VillageMaterial material;

        protected Village(VillagePieceStart start, int type)
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
        protected StructureComponent getNextComponentNN(VillagePieceStart start, List<StructureComponent> p_74891_2_, Random rand, int p_74891_4_, int p_74891_5_)
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
        protected StructureComponent getNextComponentPP(VillagePieceStart start, List<StructureComponent> p_74894_2_, Random rand, int p_74894_4_, int p_74894_5_)
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




}