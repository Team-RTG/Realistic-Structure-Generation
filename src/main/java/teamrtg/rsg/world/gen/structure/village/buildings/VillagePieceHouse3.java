package teamrtg.rsg.world.gen.structure.village.buildings;

import net.minecraft.block.BlockTorch;
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

public class VillagePieceHouse3 extends StructureVillagePiecesRSG.Village
{

	public VillagePieceHouse3(VillagePieceStart start, int p_i45561_2_, Random rand, StructureBoundingBox p_i45561_4_, EnumFacing facing)
	{
		super(start, p_i45561_2_);
		this.coordBaseMode = facing;
		this.boundingBox = p_i45561_4_;
	}

	public static VillagePieceHouse3 func_175849_a(VillagePieceStart start, List<StructureComponent> p_175849_1_, Random rand, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing facing, int p_175849_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 9, 7, 12, facing);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null ? new VillagePieceHouse3(start, p_175849_7_, rand, structureboundingbox, facing) : null;
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