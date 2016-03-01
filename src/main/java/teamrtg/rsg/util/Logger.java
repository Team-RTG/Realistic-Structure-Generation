package teamrtg.rsg.util;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class Logger {

	public static void debug(String format, Object... data) {
		FMLLog.log(Level.INFO, "[RSG-DEBUG] " + format, data);
	}

	public static void info(String format, Object... data) {
		FMLLog.log(Level.INFO, "[RSG-INFO] " + format, data);
	}

	public static void warn(String format, Object... data) {
		FMLLog.log(Level.WARN, "[RSG-WARN] " + format, data);
	}

	public static void error(String format, Object... data) {
		FMLLog.log(Level.ERROR, "[RSG-ERROR] " + format, data);
	}

	public static void fatal(String message, Throwable throwable, Object... data) {
		FMLLog.log(Level.FATAL, "[RSG-FATAL] " + message, data);
		Minecraft.getMinecraft().crashed(new CrashReport(message, throwable));
	}
}