package teamrtg.rsg.world.gen.structure.village.buildings;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import teamrtg.rsg.world.gen.structure.village.StructureVillagePiecesRSG;

import java.util.List;
import java.util.Random;

import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.FOUNDATION;
import static teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap.EnumSwap.PATH;

public class VillagePiecePath extends StructureVillagePiecesRSG.Road
{
	private int length;

	public VillagePiecePath(VillagePieceStart start, int p_i45562_2_, Random rand, StructureBoundingBox p_i45562_4_, EnumFacing facing)
	{
		super(start, p_i45562_2_);
		this.coordBaseMode = facing;
		this.boundingBox = p_i45562_4_;
		this.length = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
	}

	/**
	 * (abstract) Helper method to write subclass data to NBT
	 */
	protected void writeStructureToNBT(NBTTagCompound tagCompound)
	{
		super.writeStructureToNBT(tagCompound);
		tagCompound.setInteger("Length", this.length);
	}

	/**
	 * (abstract) Helper method to read subclass data from NBT
	 */
	protected void readStructureFromNBT(NBTTagCompound tagCompound)
	{
		super.readStructureFromNBT(tagCompound);
		this.length = tagCompound.getInteger("Length");
	}

	/**
	 * Initiates construction of the Structure Component picked, at the current Location of StructGen
	 */
	public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
	{
		boolean flag = false;

		for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5))
		{
			StructureComponent structurecomponent = this.getNextComponentNN((VillagePieceStart)componentIn, listIn, rand, 0, i);

			if (structurecomponent != null)
			{
				i += Math.max(structurecomponent.getBoundingBox()
						.getXSize(), structurecomponent.getBoundingBox()
						.getZSize());
				flag = true;
			}
		}

		for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5))
		{
			StructureComponent structurecomponent1 = this.getNextComponentPP((VillagePieceStart)componentIn, listIn, rand, 0, j);

			if (structurecomponent1 != null)
			{
				j += Math.max(structurecomponent1.getBoundingBox()
						.getXSize(), structurecomponent1.getBoundingBox()
						.getZSize());
				flag = true;
			}
		}

		if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null)
		{
			switch (this.coordBaseMode)
			{
				case NORTH:
					StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
							.minX - 1, this.getBoundingBox()
							.minY, this.getBoundingBox()
							.minZ, EnumFacing.WEST, this.getComponentType());
					break;
				case SOUTH:
					StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
							.minX - 1, this.getBoundingBox()
							.minY, this.getBoundingBox()
							.maxZ - 2, EnumFacing.WEST, this.getComponentType());
					break;
				case WEST:
					StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
							.minX, this.getBoundingBox()
							.minY, this.getBoundingBox()
							.minZ - 1, EnumFacing.NORTH, this.getComponentType());
					break;
				case EAST:
					StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
							.maxX - 2, this.getBoundingBox()
							.minY, this.getBoundingBox()
							.minZ - 1, EnumFacing.NORTH, this.getComponentType());
			}
		}

		if (flag && rand.nextInt(3) > 0 && this.coordBaseMode != null)
		{
			switch (this.coordBaseMode)
			{
				case NORTH:
					StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
							.maxX + 1, this.getBoundingBox()
							.minY, this.getBoundingBox()
							.minZ, EnumFacing.EAST, this.getComponentType());
					break;
				case SOUTH:
					StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
							.maxX + 1, this.getBoundingBox()
							.minY, this.getBoundingBox()
							.maxZ - 2, EnumFacing.EAST, this.getComponentType());
					break;
				case WEST:
					StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
							.minX, this.getBoundingBox()
							.minY, this.getBoundingBox()
							.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
					break;
				case EAST:
					StructureVillagePiecesRSG.func_176069_e((VillagePieceStart)componentIn, listIn, rand, this.getBoundingBox()
							.maxX - 2, this.getBoundingBox()
							.minY, this.getBoundingBox()
							.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
			}
		}
	}

	public static StructureBoundingBox func_175848_a(VillagePieceStart start, List<StructureComponent> p_175848_1_, Random rand, int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing facing)
	{
		for (int i = 7 * MathHelper.getRandomIntegerInRange(rand, 3, 5); i >= 7; i -= 7)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0, 0, 3, 3, i, facing);

			if (StructureComponent.findIntersecting(p_175848_1_, structureboundingbox) == null)
			{
				return structureboundingbox;
			}
		}

		return null;
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
	 * Mineshafts at the end, it adds Fences...
	 */
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
	{
		IBlockState iblockstate = this.func_175847_a(this.material.get(PATH).get());
		IBlockState iblockstate1 = this.func_175847_a(this.material.get(FOUNDATION).get());

		for (int i = this.getBoundingBox()
				.minX; i <= this.getBoundingBox()
				.maxX; ++i)
		{
			for (int j = this.getBoundingBox()
					.minZ; j <= this.getBoundingBox()
					.maxZ; ++j)
			{
				BlockPos blockpos = new BlockPos(i, 64, j);

				if (structureBoundingBoxIn.isVecInside(blockpos))
				{
					blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos).down();
					if (worldIn.setBlockState(blockpos, iblockstate, 2))
						worldIn.setBlockState(blockpos.down(), iblockstate1, 2);
				}
			}
		}

		return true;
	}
}