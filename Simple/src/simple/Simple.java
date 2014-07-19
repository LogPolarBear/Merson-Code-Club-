/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package simple;

import org.bukkit.Sound;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;//(1)

public class Simple extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
  public void onEnable() {
    log.info("[Simple] Start up.");
  }
  public void onReload() {
    log.info("[Simple] Server reloaded.");
  }
  public void onDisable() {
    log.info("[Simple] Server stopping.");
  }
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {     
				getServer().broadcastMessage("!!!");
    if (commandLabel.equalsIgnoreCase("simple")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        // Put your code after this line:
		me.sendMessage("Can you hear this?");
        int myAge = 13;
		int twiceMyAge = myAge * 2;
		float volume = 0.1f;
		float pitch = 1.0f;
		double dayOnIo = 152853.5047;
		String myName = "Sam, Tom, and Don-E";
		me.sendMessage("My age is " + myAge);
		me.sendMessage("Double my age is " + twiceMyAge);
		me.sendMessage("Volume is " + volume);
		me.sendMessage("Pitch is " + pitch);
		me.sendMessage("Day on Io lasts " + dayOnIo);
		me.playSound(me.getLocation(), Sound.GHAST_SCREAM, volume, pitch);
        // ...and finish your code before this line.
        return true;
        }
    }
    return false;
  }
}
