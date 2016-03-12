package teamrtg.rsg.world.gen.structure.village.buildings;

import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import teamrtg.rsg.util.Logger;
import teamrtg.rsg.world.gen.structure.village.StructureVillagePiecesRSG;
import teamrtg.rsg.world.gen.structure.village.VillageMaterial;

import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

public class VillagePieceStart extends StructureVillagePieces.Start
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

	public VillagePieceStart(WorldChunkManager chunkManagerIn, int p_i2104_2_, Random rand, int p_i2104_4_, int p_i2104_5_, List<PieceWeight> p_i2104_6_, int p_i2104_7_)
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
		StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
		StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
		StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
		StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
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