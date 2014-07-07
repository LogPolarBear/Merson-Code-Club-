/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package lavavision;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.block.Block;
import org.bukkit.util.BlockIterator;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Effect;

public class LavaVision extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
  
  public void onEnable() {
    log.info("[LavaVision] Start up.");
  }
  public void onReload() {
    log.info("[LavaVision] Server reloaded.");
  }
  public void onDisable() {
    log.info("[LavaVision] Server stopping.");
  }

  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("lavavision")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        BlockIterator sightItr = new BlockIterator (me, 100);
        while (sightItr.hasNext()) {
          Block b = sightItr.next();
          me.playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, null);
          if (b.getType() != Material.AIR) {
            b.setType(Material.LAVA);
            me.playSound(b.getLocation(), Sound.EXPLODE, 1.0f, 0.5f);
            break;
          }
        }
        return true;
      }
    }
    return false;
  }
}
