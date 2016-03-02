package teamrtg.rsg.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import teamrtg.rsg.util.RSGException.Type;
import teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap;

/**
 * @author topisani
 */
public class BlockUtil {

	public static String stateToString(IBlockState blockState) {
		return blockState.getBlock().getRegistryName() + "/" + blockState.getBlock().getMetaFromState(blockState);
	}
	public static String[] statesToStrings(IBlockState[] blockStates) {
		String[] s = new String[0];
		for (int i = 0; i < blockStates.length; i++) {
			s[i] = stateToString(blockStates[0]);
		}
		return s;
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

	public static IBlockState[] stringsToStates(String[] strings) throws RSGException {
		IBlockState[] s = new IBlockState[0];
		for (int i = 0; i < strings.length; i++) {
			s[i] = stringToState(strings[0]);
		}
		return s;
	}

	public static VillageMaterialSwap stringsToSwap(String... strings) throws RSGException {
		for (int i = 0; i < strings.length; i++) {
			String[] s = strings[i].split("/");
			Block b = Block.getBlockFromName(s[0]);
			if ( b == null ) throw new RSGException(Type.CONFIG_SYNTAX, "Expected 'modID:blockId/metaValue', found " + strings[i]);
			try {
				return new VillageMaterialSwap(b.getStateFromMeta(Integer.valueOf(s[1])));
			} catch (Exception e) {
				return new VillageMaterialSwap(b);
			}
		}
		throw new RSGException(Type.STUPID_DEVELOPER, "Beats me, but hey, I'm just the stupid developer.", "BlockUtil.stringsToSwap()");
	}
}
