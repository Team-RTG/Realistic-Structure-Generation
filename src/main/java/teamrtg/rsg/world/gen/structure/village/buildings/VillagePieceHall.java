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

public class VillagePieceHall extends StructureVillagePiecesRSG.Village
{

	public VillagePieceHall(VillagePieceStart start, int p_i45567_2_, Random rand, StructureBoundingBox p_i45567_4_, EnumFacing facing)
	{
		super(start, p_i45567_2_);
		this.coordBaseMode = facing;
		this.boundingBox = p_i45567_4_;
	}

	public static VillagePieceHall func_175857_a(VillagePieceStart start, List<StructureComponent> p_175857_1_, Random rand, int p_175857_3_, int p_175857_4_, int p_175857_5_, EnumFacing facing, int p_175857_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175857_3_, p_175857_4_, p_175857_5_, 0, 0, 0, 9, 7, 11, facing);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175857_1_, structureboundingbox) == null ? new VillagePieceHall(start, p_175857_7_, rand, structureboundingbox, facing) : null;
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