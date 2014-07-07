/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package namecow;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Cow;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class NameCow extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
  public void onEnable() {
    log.info("[NameCow] Start up.");
  }
  public void onReload() {
    log.info("[NameCow] Server reloaded.");
  }
  public void onDisable() {
    log.info("[NameCow] Server stopping.");
  }

  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("namecow")) {
      if (sender instanceof Player && args.length > 0) { 
        Player me = (Player)sender;
        Cow cow = me.getWorld().spawn(me.getLocation(), Cow.class);
        cow.setCustomName(args[0]);
        cow.setCustomNameVisible(true);
        return true;
      }
    }
    return false;
  }
}
