package teamrtg.rsg.world.gen.structure.village;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import teamrtg.rsg.event.EventManagerRSG;


/**
 * The exact materials to be swapped on village generation
 */
public class VillageMaterialSwap {

	public enum EnumSwap {
		PATH,
		FOUNDATION,
		WALL,
		ROOF,
		HUT_ROOF,
		CORNER,
		STAIRS,
		BLACKSMITH_ROOF,
		FENCE,
		DOOR,
		CHURCH_BLOCK,
		CHURCH_STAIRS,
		WINDOW,
		WINDOW_SHUTTERS,
		CROPS,
		FARMLAND,
		FARM_BORDER,
		GARDEN_GROUND,
		TABLE_TOP,
		CHAIR,
		CHOPPING_BLOCK,
		ANVIL,
		WELL_POST,
		WELL_BLOCK,
		LAMP_POST,
		LAMP_BLOCK,
		LAMP_TORCH;

		public static EnumSwap fromName(String s) {
			for(EnumSwap e : EnumSwap.values()){
				if(s == e.name()) return e;
			}
			return null;
		}
	}

    private IBlockState[] materialBlocks = null;
    private boolean preserveMeta;

    /**
     * @param states possible blocks
     */
    public VillageMaterialSwap(IBlockState... states) {
        this.materialBlocks = states;
    }

    /**
     * Will turn on preserveMeta
     * @param blocks possible blocks
     */
    public VillageMaterialSwap(Block... blocks) {
	    this.materialBlocks = new IBlockState[0];
	    for (int i = 0; i < blocks.length; i++) {
		    this.materialBlocks[i] = blocks[i].getDefaultState();
	    }
        this.preserveMeta = true;
    }

    public IBlockState get() {
        return this.get(0);
    }

    public IBlockState get(int defaultMeta) {
        int r = EventManagerRSG.world.rand.nextInt(materialBlocks.length - 1);
	    IBlockState result = materialBlocks[r];
        if (preserveMeta) result = result.getBlock().getStateFromMeta(defaultMeta);
        return result;
    }

	public IBlockState[] getAll() {
		return this.materialBlocks;
	}

    /**
     * Sets the material specific blockstate
     */
    public void set(IBlockState... materialBlock) {
        this.materialBlocks = materialBlock;
    }

    /**
     * Will preserve the metadata of the original block when replaced.
     */
    public void set(Block... materialBlock) {
	    this.materialBlocks = new IBlockState[0];
	    for (int i = 0; i < materialBlock.length; i++) {
			this.materialBlocks[i] = materialBlock[i].getDefaultState();
	    }
        this.preserveMeta = true;
    }

    /**
     * If set to true, the metadata of the original block will be preserved.
     */
    public void setPreserveMeta(boolean preserveMeta) {
        this.preserveMeta = preserveMeta;
    }
}
