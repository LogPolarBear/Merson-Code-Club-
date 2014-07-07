/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package namedsigns;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class NamedSigns extends JavaPlugin {
  private static Logger log = Logger.getLogger("Minecraft");
  private static Map<String,Location> signs = new HashMap<String,Location>();//(1)
  
  private void usage(Player me) {//(2)
    me.sendMessage("Usage: signs new name");
    me.sendMessage("       signs set name message"); 
  }
  
  private boolean parseArgs(Player me, String [] args) {
    if (args.length < 2) {//(3)
      usage(me);
      return false;
    }
    if (args[0].equalsIgnoreCase("new")) {
      return makeNewSign(me, args);
    }
    if (args[0].equalsIgnoreCase("set")) {
      if (args.length < 3) {//(4)
        usage(me);
        return false;
      }
      return setSign(me, args);
    }
    return false;
  }

  // signs new sign_name 
  private boolean makeNewSign(Player me, String [] args) {//(5) 
    Location loc = me.getLocation();
    loc.setX(loc.getX() + 1); // Not right on top of player
    Block block = loc.getWorld().getHighestBlockAt(loc);
    signs.put(args[1], block.getLocation());
    block.setType(Material.SIGN_POST);
    log.info("Made new sign named " + args[1]);
    return true;
  }

  // signs set sign_name line1
  private boolean setSign(Player me, String [] args) {//(6) 
    String name = args[1];
    String msg = args[2];
    if (!signs.containsKey(name)) {
      // No such named sign
      me.sendMessage("No sign named " + name);
      return false;
    }
    Location loc = signs.get(name);
    Sign sign = (Sign)loc.getWorld().getBlockAt(loc).getState();//(7)
    sign.setLine(0, msg);
    sign.update();
    log.info("Set sign named " + name + " to " + msg);
    return true;
  }

  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("signs")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        return parseArgs(me, args);
      }
    }
    return false;
  }
}
