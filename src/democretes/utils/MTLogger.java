package democretes.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.message.MessageFormatMessage;

import com.google.common.base.Defaults;

import democretes.lib.Reference;

public class MTLogger {
	
	public void info(int num) {
		log(Level.INFO, new String("" + num));
	}
	
	public void info(String string) {
		log(Level.INFO, string);
	}
	
	public void warning(String message) {
		log(Level.WARN, message);
	}
	
	public void severe(String message) {
		log(Level.FATAL, message);
	}
	
	public void log(Level logLevel, String message, Object... params) {
		LogManager.getLogger(Reference.MOD_ID).log(logLevel, new MessageFormatMessage(String.format(message, params), params));
	}

}
