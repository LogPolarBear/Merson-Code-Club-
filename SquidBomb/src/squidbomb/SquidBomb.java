/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package squidbomb; //(1)

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class SquidBomb extends JavaPlugin { 
  public static Logger log = Logger.getLogger("Minecraft");
  
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("squidbomb")) { 
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        Location loc = me.getLocation();

        //spawning some squids.  Inky.
        for (int i = 0; i < 10; i++) {
            Location newloc = new Location(loc.getWorld(),
              loc.getX() + (Math.random() * 5), 
              loc.getY() + (Math.random() + 20),
              loc.getZ() + (Math.random() * 5));
            me.getWorld().spawn(newloc, Squid.class);
        }      
        return true;
      }
    }
    return false;
  }
}
