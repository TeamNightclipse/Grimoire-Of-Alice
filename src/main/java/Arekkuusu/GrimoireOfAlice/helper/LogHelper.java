/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.helper;

import cpw.mods.fml.common.FMLLog;

import arekkuusu.grimoireofalice.lib.libMod;

import org.apache.logging.log4j.Level;

public class LogHelper {

	public static void log(Level loglevel, Object object){
		
		FMLLog.log(libMod.MODNAME, loglevel, String.valueOf(object));
		
	}
	
	public static void all(Object object){
		
		log(Level.ALL, object);
		
	}
	
	public static void debug(Object object){
		
		log(Level.DEBUG, object);
		
	}
	
	public static void error(Object object){
		
		log(Level.ERROR, object);
		
	}
	
	public static void fatal(Object object){
		
		log(Level.FATAL, object);
		
	}
	
	public static void info(Object object){
		
		log(Level.INFO, object);
		
	}
	
	public static void off(Object object){
		
		log(Level.OFF, object);
		
	}
	
	public static void trace(Object object){
		
		log(Level.TRACE, object);
		
	}
	
	public static void warn(Object object){
		
		log(Level.WARN, object);
		
	}
	
}
