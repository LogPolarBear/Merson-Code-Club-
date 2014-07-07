/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package backcmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BackCmd extends JavaPlugin implements Listener {//(1)
  public static Logger log = Logger.getLogger("Minecraft");
  private static List<Player> isTeleporting = new ArrayList<Player>();//(2)
  private static HashMap<String, Stack<Location>> playerTeleports = 
      new HashMap<String, Stack<Location>>();

  public void onEnable() {
    log.info("[BackCmd] enabling.");
    getServer().getPluginManager().registerEvents(this, this);//(3)
  }

  
  public boolean equalsIsh(Location loc1, Location loc2) {//(4)
    return ((int) loc1.getX()) == ((int) loc2.getX()) &&
           ((int) loc1.getZ()) == ((int) loc2.getZ());
  }


  @EventHandler
  public void onTeleport(PlayerTeleportEvent event) {//(5)
    Player player = event.getPlayer();
    if (isTeleporting.contains(player)) {
      isTeleporting.remove(player);
    } else {
      Stack<Location> locs = playerTeleports.get(player.getName());
      if (locs == null) {
        locs = new Stack<Location>();
      }
      locs.push(event.getFrom());
      locs.push(event.getTo());
      playerTeleports.put(player.getName(), locs);
    }
  }
  
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {
    if (commandLabel.equalsIgnoreCase("back")) {
      if (sender instanceof Player) {
        Player player = (Player) sender;
        Stack<Location> locs = playerTeleports.get(player.getName());
        
        if (locs != null && !locs.empty()) {
          Location loc = locs.peek();  
          while (equalsIsh(loc, player.getLocation()) && locs.size() > 1) {
            locs.pop();
            loc = locs.peek();
          }
          isTeleporting.add(player);
          player.teleport(loc);
          return true;
        } else {
          player.sendMessage("You have not teleported yet.");
        }
      } else {
        sender.sendMessage("You must be a player to use this command.");
      }
    }
    return false;
  }  
  
}
