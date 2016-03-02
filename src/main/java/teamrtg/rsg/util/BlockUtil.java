package teamrtg.rsg.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import teamrtg.rsg.util.RSGException.Type;

/**
 * @author topisani
 */
public class BlockUtil {

	public static String stateToString(IBlockState blockState) {
		return blockState.getBlock().getRegistryName() + "/" + blockState.getBlock().getMetaFromState(blockState);
	}

	public static IBlockState stringToState(String string) throws RSGException{
		String[] s = string.split("/");
		Block b = Block.getBlockFromName(s[0]);
		IBlockState bs;
		if ( b == null ) throw new RSGException(Type.CONFIG_SYNTAX, "Expected 'modID:blockId/metaValue', found " + string);
		try {
			bs = b.getStateFromMeta(Integer.valueOf(s[1]));
		} catch (Exception e) {
			bs = b.getDefaultState();
		}
		return bs;
	}
}
