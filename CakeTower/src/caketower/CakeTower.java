/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package caketower;

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class CakeTower extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");

  public static int cakeHeight = 100; //(1)

  public void onEnable() {
    log.info("[CakeTower] Start up.");
  }
  public void onReload() {
    log.info("[CakeTower] Server reloaded.");
  }
  public void onDisable() {
    log.info("[CakeTower] Server stopping.");
  }
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("caketower")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;

        me.sendMessage("1) cake height is " + cakeHeight); // Print it

        cakeHeight = 50;
        
        int cakeHeight;  //(2) 
        cakeHeight = 5;
        me.sendMessage("2) cake height is " + cakeHeight); // Print it
        
        makeCakes(me); // Print it
        return true;
      }
    }
    return false;
  }
  
  public void makeCakes(Player me) {
    me.sendMessage("3) cake height is " + cakeHeight);
    Location loc = me.getLocation();
    loc.setY(loc.getY() + 2);
    loc.getWorld().getBlockAt(loc).setType(Material.STONE);
    for(int i = 0;i < cakeHeight;i++) {
      loc.setY(loc.getY() + 1);
      loc.getWorld().getBlockAt(loc).setType(Material.CAKE_BLOCK);
    }
  }
}
