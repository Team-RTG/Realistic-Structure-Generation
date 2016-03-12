package teamrtg.rsg.world.gen.structure.village.buildings;

import net.minecraft.block.BlockTorch;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import teamrtg.rsg.world.gen.structure.village.StructureVillagePiecesRSG;

import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

public class VillagePieceTorch extends StructureVillagePiecesRSG.Village
{

	public VillagePieceTorch(VillagePieceStart start, int p_i45568_2_, Random rand, StructureBoundingBox p_i45568_4_, EnumFacing facing)
	{
		super(start, p_i45568_2_);
		this.coordBaseMode = facing;
		this.boundingBox = p_i45568_4_;
	}

	public static StructureBoundingBox func_175856_a(VillagePieceStart start, List<StructureComponent> p_175856_1_, Random rand, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing facing)
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