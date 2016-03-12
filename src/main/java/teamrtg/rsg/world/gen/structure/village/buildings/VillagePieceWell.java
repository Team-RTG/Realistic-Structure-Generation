package teamrtg.rsg.world.gen.structure.village.buildings;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import teamrtg.rsg.world.gen.structure.village.StructureVillagePiecesRSG;

import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

public class VillagePieceWell extends StructureVillagePiecesRSG.Village
{

	public VillagePieceWell(VillagePieceStart start, int p_i2109_2_, Random rand, int p_i2109_4_, int p_i2109_5_)
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
		StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
				.minX - 1, this.getBoundingBox()
				.maxY - 4, this.getBoundingBox()
				.minZ + 1, EnumFacing.WEST, this.getComponentType());
		StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
				.maxX + 1, this.getBoundingBox()
				.maxY - 4, this.getBoundingBox()
				.minZ + 1, EnumFacing.EAST, this.getComponentType());
		StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
				.minX + 1, this.getBoundingBox()
				.maxY - 4, this.getBoundingBox()
				.minZ - 1, EnumFacing.NORTH, this.getComponentType());
		StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
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
