package teamrtg.rsg.config.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import teamrtg.rsg.util.RSGException;
import teamrtg.rsg.util.RSGException.Type;
import teamrtg.rsg.world.gen.structure.village.VillageMaterialSwap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author topisani
 */
public class BlockStringUtil {

	public static String stateToString(IBlockState blockState) {
		return blockState.getBlock().getRegistryName() + "/" + blockState.getBlock().getMetaFromState(blockState);
	}
	public static String[] statesToStrings(IBlockState[] blockStates, boolean preserveMeta) {
		List<String> s = new ArrayList<String>();
		for (int i = 0; i < blockStates.length; i++) {
			int meta = (preserveMeta)? -1 : blockStates[i].getBlock().getMetaFromState(blockStates[i]);
			s.add(blockStates[i].getBlock().getRegistryName() + "/" + meta);
		}
		return s.toArray(new String[s.size()]);
	}

	public static IBlockState stringToState(String string) throws RSGException {
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
		List<IBlockState> s = new ArrayList<IBlockState>();
		for (int i = 0; i < strings.length; i++) {
			s.add(stringToState(strings[0]));
		}
		return s.toArray(new IBlockState[s.size()]);
	}

	public static VillageMaterialSwap stringsToSwap(String... strings) throws RSGException {
		for (int i = 0; i < strings.length; i++) {
			String[] s = strings[i].split("/");
			Block b = Block.getBlockFromName(s[0]);
			if ( b == null ) throw new RSGException(Type.CONFIG_SYNTAX, "Expected 'modID:blockId/metaValue', found " + strings[i]);
			try {
				if ( Integer.valueOf(s[1]) == -1) return new VillageMaterialSwap(b);
				return new VillageMaterialSwap(b.getStateFromMeta(Integer.valueOf(s[1])));
			} catch (Exception e) {
				return new VillageMaterialSwap(b);
			}
		}
		throw new RSGException(Type.STUPID_DEVELOPER, "Beats me, but hey, I'm just the stupid developer.", "BlockStringUtil.stringsToSwap()");
	}
}
