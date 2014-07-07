/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package locationsnapshot; //(1)

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;

public class LocationSnapshot extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
  private final static String datafile = "locations.dat";

  private void saveLocations() {
    Player[] playerList = getServer().getOnlinePlayers();
    HashMap<String, Object> map = new HashMap<String, Object>();
    // For all players...
    for (Player player : playerList) {
      String name = player.getPlayerListName();
      Location where = player.getLocation();
      double coordinates[] = { where.getX(), where.getY(), where.getZ() };
      // Save the raw coordinates, not the Location
      map.put(name, coordinates);
    }

    if (!PermaMap.save(this, datafile, map)) {
      log.info("[Locations] Couldn't save file."); 
    }
  }

  private void loadLocations() {
    HashMap<String, Object> map = PermaMap.load(this, datafile);
    //Go through play list; if they are in the hash, teleport them.
    Player[] playerList = getServer().getOnlinePlayers();
    for (Player player : playerList) {
      String name = player.getPlayerListName();
      double[] coordinates = (double[])map.get(name);
      if (coordinates != null) {
        // Reconstitute a Location from coordinates
        Location loc = new Location(player.getWorld(),
          coordinates[0],
          coordinates[1],
          coordinates[2]);
        player.teleport(loc);
      }
    }
  }
       
  public boolean onCommand(CommandSender sender, Command command, 
		     String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("savelocations")) {    
        saveLocations();
        return true;
    }
    if (commandLabel.equalsIgnoreCase("loadlocations")) {
        loadLocations();
        return true;
    }
    return false;
  }
}
