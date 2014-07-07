/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package arrayofblocks;

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class ArrayOfBlocks extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
  
  public void buildTower(Player me) {
    Location loc = me.getLocation();
    loc.setX(loc.getX() + 1); // Not right on top of player
    
    Material[] towerMaterials = new Material[5];
    
    towerMaterials[0] = Material.STONE;
    towerMaterials[1] = Material.CAKE_BLOCK;
    towerMaterials[2] = Material.WOOD;
    towerMaterials[3] = Material.GLASS;
    towerMaterials[4] = Material.ANVIL;
    
    for (int i=0; i < towerMaterials.length; i++) {
      loc.setY(loc.getY() + 1); // go up one each time
      loc.getWorld().getBlockAt(loc).setType(towerMaterials[i]);
    }    
  }
  
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("arrayofblocks")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        // Put your code after this line:
        buildTower(me);
        // ...and finish your code before this line.
        return true;
        }
    }
    return false;
  }
}
