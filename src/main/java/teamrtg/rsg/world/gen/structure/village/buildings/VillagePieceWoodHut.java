package teamrtg.rsg.world.gen.structure.village.buildings;

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

public class VillagePieceWoodHut extends StructureVillagePiecesRSG.Village
{
	private boolean isTallHouse;
	private int tablePosition;

	public VillagePieceWoodHut(VillagePieceStart start, int p_i45565_2_, Random rand, StructureBoundingBox p_i45565_4_, EnumFacing facing)
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

	public static VillagePieceWoodHut func_175853_a(VillagePieceStart start, List<StructureComponent> p_175853_1_, Random rand, int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing facing, int p_175853_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, 4, 6, 5, facing);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175853_1_, structureboundingbox) == null ? new VillagePieceWoodHut(start, p_175853_7_, rand, structureboundingbox, facing) : null;
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