/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package arrayaddmoreblocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class ArrayAddMoreBlocks extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
  public static List<Material> towerMaterials = new ArrayList<Material>();

  public void buildTower(Player me) {
    Location loc = me.getLocation();
    loc.setX(loc.getX() + 1); // Not right on top of player
 
    towerMaterials.add(Material.GLASS);
    towerMaterials.add(Material.STONE);
    towerMaterials.add(Material.WOOD);
        
    for (Material material : towerMaterials) {
      loc.setY(loc.getY() + 1); // go up one each time
      loc.getWorld().getBlockAt(loc).setType(material);
    }    
  }
  
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("arrayaddmoreblocks")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        buildTower(me);
        return true;
      }
    }
    if (commandLabel.equalsIgnoreCase("arrayclearblocks")) {
      towerMaterials.clear();
    }
    return false;
  }
}
