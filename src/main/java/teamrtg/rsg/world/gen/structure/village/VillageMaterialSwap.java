package teamrtg.rsg.world.gen.structure.village;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import teamrtg.rsg.util.Logger;

/**
 * The exact materials to be swapped on village generation
 */
public class VillageMaterialSwap {

    private IBlockState defaultBlock;
    private IBlockState materialBlock = null;
    private boolean preserveMeta;

    /**
     * @param defaultBlock the block to replace
     */
    public VillageMaterialSwap(IBlockState defaultBlock) {
        this.defaultBlock = defaultBlock;
    }

    /**
     * Will turn on preserveMeta
     * @param defaultBlock the block to replace
     */
    public VillageMaterialSwap(Block defaultBlock) {
        this.defaultBlock = defaultBlock.getDefaultState();
        this.preserveMeta = true;
    }

    public IBlockState getDefault() {
        return defaultBlock;
    }

    public IBlockState get() {
        return this.get(0);
    }
    public IBlockState get(int defaultMeta) {
        IBlockState result;
        if (materialBlock != null) result = materialBlock;
        else result = defaultBlock;
        if (preserveMeta) result = result.getBlock().getStateFromMeta(defaultMeta);
        return result;
    }

    /**
     * Sets the material specific blockstate
     */
    public void set(IBlockState materialBlock) {
        this.materialBlock = materialBlock;
    }

    /**
     * Will preserve the metadata of the original block when replaced.
     */
    public void set(Block materialBlock) {
        this.materialBlock = materialBlock.getDefaultState();
        this.preserveMeta = true;
    }

    /**
     * If set to true, the metadata of the original block will be preserved.
     */
    public void setPreserveMeta(boolean preserveMeta) {
        this.preserveMeta = preserveMeta;
    }

    /**
     * Get the replacement BlockState for oldBlock
     * @param oldBlock the BlockState to replace
     * @return the block to replace oldBlock with. Will have metadata from oldBlock if preserveMetadata is on.
     */
    public IBlockState replace(IBlockState oldBlock) {
        if (oldBlock.getBlock() != this.defaultBlock) {
            Logger.debug("VillageMaterialSwap was asked to replace a block that didnt match it. This should not have happened");
            return oldBlock;
        }
        IBlockState newBlock = this.materialBlock;
        if (preserveMeta && newBlock != null) {
            newBlock = newBlock.getBlock().getStateFromMeta(oldBlock.getBlock().getMetaFromState(oldBlock));
        }
        return newBlock;
    }
}
