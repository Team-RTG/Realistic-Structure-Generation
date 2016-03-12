package teamrtg.rsg.world.gen.structure.village.buildings;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import teamrtg.rsg.world.gen.structure.village.StructureVillagePiecesRSG;

import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.*;

public class VillagePieceField2 extends StructureVillagePiecesRSG.Village
{
	/** First crop type for this field. */
	private Block cropTypeA;
	/** Second crop type for this field. */
	private Block cropTypeB;

	public VillagePieceField2(VillagePieceStart start, int p_i45569_2_, Random rand, StructureBoundingBox p_i45569_4_, EnumFacing facing)
	{
		super(start, p_i45569_2_);
		this.coordBaseMode = facing;
		this.boundingBox = p_i45569_4_;
		this.cropTypeA = this.func_151560_a(rand);
		this.cropTypeB = this.func_151560_a(rand);
	}

	/**
	 * (abstract) Helper method to write subclass data to NBT
	 */
	protected void writeStructureToNBT(NBTTagCompound tagCompound)
	{
		super.writeStructureToNBT(tagCompound);
		tagCompound.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
		tagCompound.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
	}

	/**
	 * (abstract) Helper method to read subclass data from NBT
	 */
	protected void readStructureFromNBT(NBTTagCompound tagCompound)
	{
		super.readStructureFromNBT(tagCompound);
		this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
		this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
	}

	private Block func_151560_a(Random rand)
	{
		return this.material
				.get(CROPS)
				.get()
				.getBlock();
	}

	public static VillagePieceField2 func_175852_a(VillagePieceStart start, List<StructureComponent> p_175852_1_, Random rand, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, facing);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ? new VillagePieceField2(start, p_175852_7_, rand, structureboundingbox, facing) : null;
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

		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 6, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, this.material.get(FARMLAND).get(), this.material.get(FARMLAND).get(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, this.material.get(FARMLAND).get(), this.material.get(FARMLAND).get(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 5, 0, 0, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 5, 0, 8, this.material.get(FARM_BORDER).get(), this.material.get(FARM_BORDER).get(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);

		for (int i = 1; i <= 7; ++i)
		{
			this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 1, 1, i, structureBoundingBoxIn);
			this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 2, 1, i, structureBoundingBoxIn);
			this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 4, 1, i, structureBoundingBoxIn);
			this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(randomIn, 2, 7)), 5, 1, i, structureBoundingBoxIn);
		}

		for (int k = 0; k < 9; ++k)
		{
			for (int j = 0; j < 7; ++j)
			{
				this.clearCurrentPositionBlocksUpwards(worldIn, j, 4, k, structureBoundingBoxIn);
				this.replaceAirAndLiquidDownwards(worldIn, Blocks.dirt.getDefaultState(), j, -1, k, structureBoundingBoxIn);
			}
		}

		return true;
	}
}