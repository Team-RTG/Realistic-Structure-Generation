package teamrtg.rsg.config;

import net.minecraftforge.common.config.Configuration;
import teamrtg.rsg.util.Logger;

import java.io.File;

public class ConfigRSG
{
	public static Configuration config;

	/*======================== VILLAGES ========================*/

	public static int villageSize = 0;
	public static int minDistanceVillages = 12; // Vanilla = 8
	public static int maxDistanceVillages = 48; // Vanilla = 32

	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();

			villageSize = config.getInt("Size of villages", "Villages", villageSize, 0, 10, "Higher values = bigger villages; 0 = Vanilla" + Configuration.NEW_LINE);
			minDistanceVillages = config.getInt("Minimum distance between villages", "Villages", minDistanceVillages, 1, Integer.MAX_VALUE, "Higher values = villages further apart; 8 = Vanilla" + Configuration.NEW_LINE);
			maxDistanceVillages = config.getInt("Maximum distance between villages", "Villages", maxDistanceVillages, 1, Integer.MAX_VALUE, "Lower values = villages closer together; 32 = Vanilla" + Configuration.NEW_LINE);
		}
		catch (Exception e) 
		{
		    Logger.error("Problem loading RSG configuration.");
		}
		finally 
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}
	}
}