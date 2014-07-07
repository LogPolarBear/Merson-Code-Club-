/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package backcmdsave; //(1)

//import backcmdsave.PermaMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import org.bukkit.World;

public class BackCmdSave extends JavaPlugin implements Listener {
  public static Logger log = Logger.getLogger("Minecraft");
  private static List<Player> isTeleporting = new ArrayList<Player>();
  private static HashMap<String, Stack<Location>> playerTeleports = 
      new HashMap<String, Stack<Location>>();
  private static final String datafile = "BackCmdSave.dat";

  public void onDisable() {
    log.info("[BackCmdSave] Saving state to disk");

    // Create a Hash that we'll save
    HashMap<String, Object> myHash = new HashMap<String, Object>();

    // Load up our Hash from playerTeleports
    for (String playerName : playerTeleports.keySet()) {
      Stack<SavedLocation> saveStack =
          SavedLocation.LocationStackToSavedStack(
                    playerTeleports.get(playerName));
      myHash.put(playerName, saveStack);
    }
    // and save it to disk
    if (!PermaMap.save(this, datafile, myHash)) {
      log.severe("Couldn't save plugin datafile");
    }
  }
   
  @SuppressWarnings("unchecked") 
  public void onEnable() {     
    log.info("[BackCmdSave] enabling.");
    getServer().getPluginManager().registerEvents(this, this);

    // Load the Hash from disk
    HashMap<String, Object> myHash = PermaMap.load(this, datafile);

    // Put it into the playerTeleports hash    
    for (String playerName : myHash.keySet()) {     
      Stack<Location> locsStack =    
          SavedLocation.SavedStackToLocationStack(
                    this.getServer(),
                    (Stack<SavedLocation>)myHash.get(playerName));
      playerTeleports.put(playerName, locsStack);
    }   
  }

  public boolean equalsIsh(Location loc1, Location loc2) {
    return ((int) loc1.getX()) == ((int) loc2.getX()) &&
           ((int) loc1.getZ()) == ((int) loc2.getZ());
  }

  @EventHandler
  public void onTeleport(PlayerTeleportEvent event) {
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
