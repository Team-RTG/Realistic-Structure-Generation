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

public class VillagePieceChurch extends StructureVillagePiecesRSG.Village
{
	public VillagePieceChurch(VillagePieceStart start, int p_i45564_2_, Random rand, StructureBoundingBox p_i45564_4_, EnumFacing facing)
	{
		super(start, p_i45564_2_);
		this.coordBaseMode = facing;
		this.boundingBox = p_i45564_4_;
	}

	public static VillagePieceChurch func_175854_a(VillagePieceStart start, List<StructureComponent> p_175854_1_, Random rand, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing facing, int p_175854_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 5, 12, 9, facing);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null ? new VillagePieceChurch(start, p_175854_7_, rand, structureboundingbox, facing) : null;
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