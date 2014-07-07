/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package skycmd;

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Entity;
import java.util.List;

public class SkyCmd extends JavaPlugin { 

  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("sky")) { //(1)
      if (sender instanceof Player) { //(2)
        Player me = (Player)sender; //(3)
        List<Entity> list = me.getNearbyEntities(50,50,50);
        for (Entity target : list) {
          if (!(target instanceof Player)) {
            Location loc = target.getLocation();
            double y = loc.getY();
            loc.setY(y+50);
            target.teleport(loc);
          }
        }
        return true;
      }
    }
    return false;
  }
}
