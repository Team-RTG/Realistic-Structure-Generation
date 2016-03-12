package teamrtg.rsg.world.gen.structure.village.buildings;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import teamrtg.rsg.world.gen.structure.village.StructureVillagePiecesRSG;

import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

public class VillagePieceHouse4Garden extends StructureVillagePiecesRSG.Village
{
	private boolean isRoofAccessible;

	public VillagePieceHouse4Garden(VillagePieceStart start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
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

	public static VillagePieceHouse4Garden func_175858_a(VillagePieceStart start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 5, 6, 5, facing);
		return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new VillagePieceHouse4Garden(start, p_175858_7_, rand, structureboundingbox, facing);
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