package teamrtg.rsg.world.gen.structure.village.buildings;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import teamrtg.rsg.world.gen.structure.village.StructureVillagePiecesRSG;

import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

public class VillagePieceHouse1 extends StructureVillagePiecesRSG.Village
{
	public VillagePieceHouse1(VillagePieceStart start, int p_i45571_2_, Random rand, StructureBoundingBox p_i45571_4_, EnumFacing facing)
	{
		super(start, p_i45571_2_);
		this.coordBaseMode = facing;
		this.boundingBox = p_i45571_4_;
	}

	public static VillagePieceHouse1 func_175850_a(VillagePieceStart start, List<StructureComponent> p_175850_1_, Random rand, int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing facing, int p_175850_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, 9, 9, 6, facing);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175850_1_, structureboundingbox) == null ? new VillagePieceHouse1(start, p_175850_7_, rand, structureboundingbox, facing) : null;
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
