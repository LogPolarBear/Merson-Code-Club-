/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package squidbombconfig;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class SquidBombConfig extends JavaPlugin { 
  public static Logger log = Logger.getLogger("Minecraft");
  
  private static int numSquids;
  private static double squidDropHeight;
  private static boolean setFire;
  
  public void onEnable() {
    FileConfiguration config = getConfig();
    numSquids = config.getInt("numSquids");
    squidDropHeight = config.getDouble("squidDropHeight");
    setFire = config.getBoolean("setFire");
    saveDefaultConfig();
  }

  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("squidbomb")) { 
      if (sender instanceof Player) { 
        Player player = (Player)sender;
        Location loc = player.getLocation();
        double y = loc.getY();
        loc.setY(y + squidDropHeight);  
        player.sendMessage("Spawning " + numSquids + " squids.");
        //spawning some squids.
        for (int i = 0; i < numSquids; i++) {
            player.getWorld().spawn(loc, Squid.class);
        }
        return true;
      } else {
        log.info("[SquidBombConfig] Console cannot use this command");
        return false;
      }
    } else if (commandLabel.equalsIgnoreCase("squidpurge")) {//(1)
      if (sender instanceof Player) { 
        Player player = (Player)sender;  
        Collection<Squid> squidlist =
          player.getWorld().getEntitiesByClass(Squid.class);
        for (Squid victim : squidlist) {
          if (setFire) {
            victim.setFireTicks(500);
          } else {
            victim.setHealth(0.0);
          }
        }
        return true;//(2)
      } else {
        log.info("[SquidBombConfig] Console cannot use this command");
        return false;
      }
    }
    return false;
  }
}

