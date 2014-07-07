/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package firebow; //(1)

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class FireBow extends JavaPlugin implements Listener {
  public static Logger log = Logger.getLogger("Minecraft");
  public static boolean enabled = true;

  public void onLoad() {
    log.info("[FireBow] Server loaded.");
  }
    
  public void onEnable() {
    log.info("[FireBow] enabling.");
    getServer().getPluginManager().registerEvents(this, this);
  }

  public void onDisable() {
    log.info("[FireBow] Server stopping.");
  }
    
  @EventHandler
  public void onArrowLaunch(EntityShootBowEvent event) {
    if (enabled) {
      Entity arrow = event.getProjectile();
      World world = arrow.getWorld();
      Vector vel = arrow.getVelocity();
      Entity tnt = world.spawn(arrow.getLocation(), TNTPrimed.class);
      tnt.setVelocity(vel);

      event.setCancelled(true);
    }
  }
  
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("nofirebow")) {
      enabled = false;
      log.info("[FireBow] Turning off arrow -> fireball");
      return true;
    }
    if (commandLabel.equalsIgnoreCase("firebow")) {
      enabled = true;
      log.info("[FireBow] Turning on arrow -> fireball");
      return true;
    }
    return false;
  }
}
