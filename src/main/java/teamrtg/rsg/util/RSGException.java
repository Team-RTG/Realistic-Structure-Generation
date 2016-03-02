package teamrtg.rsg.util;

public class RSGException extends Exception {

	public Type type;

	private String message = "";
	private String identifier = "";

	public enum Type {
		CONFIG_SYNTAX
	}

	public RSGException(Type type, String message) {
		this.message = message;
		this.type = type;
	}

	public RSGException(Type type, String message, String identifier) {
		this.message = message;
		this.type = type;
		this.identifier = identifier;
	}

	public void log() {
		String s = "RSG experienced a %s error.";
		if (!this.message.isEmpty()) s += " Reason: " + identifier;
		if (!this.identifier.isEmpty()) s += "Crash identifier: " + identifier;
		Logger.error(s, type);
	}

	public void crash() {
		String s = "RSG experienced a %s error.";
		if (!this.message.isEmpty()) s += " Reason: %s";
		if (!this.identifier.isEmpty()) s += "Crash identifier: " + identifier;
		Logger.fatal(s, this, type);
	}

	public String getMessage() {
		return this.message;
	}
}
