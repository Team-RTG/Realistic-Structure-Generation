package teamrtg.rsg.config;


import net.minecraft.block.state.IBlockState;

public class ConfigProperty {

    public String id;
    public Type type;
    public String name;
    public String description;
    
    public int minValue;
    public int maxValue;
    public int valueInt;
    public boolean valueBoolean;
    public String valueString;
	public String[] valueStrings;
	public IBlockState valueBlock;
    
    public enum Type {
        INTEGER,
        BOOLEAN,
        STRING,
        STRING_LIST,
        BLOCK
    }
    
    public ConfigProperty(String id, Type type, String name, String description)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public ConfigProperty(String id, Type type, String name, String description, boolean defaultValue)
    {
        this(id, type, name, description);
        
        this.valueBoolean = defaultValue;
    }
    
    public ConfigProperty(String id, Type type, String name, String description, String defaultValue)
    {
        this(id, type, name, description);
        
        this.valueString = defaultValue;
    }
    
    public ConfigProperty(String id, Type type, String name, String description, int defaultValue, int minValue, int maxValue)
    {
        this(id, type, name, description);
        
        this.valueInt = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

	public ConfigProperty(String id, Type type, String name, String description, String[] defaultValue)
	{
		this(id, type, name, description);

		this.valueStrings = defaultValue;
	}

	public ConfigProperty(String id, Type type, String name, String description, IBlockState defaultValue)
	{
		this(id, type, name, description);

		this.valueBlock = defaultValue;
	}
}
