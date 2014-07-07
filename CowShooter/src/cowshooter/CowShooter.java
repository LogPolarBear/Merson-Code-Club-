/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package cowshooter; //(1)

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class CowShooter extends JavaPlugin implements Listener {
  public static Logger log = Logger.getLogger("Minecraft");

  public void onEnable() {
    log.info("[CowShooter] enabling.");
    getServer().getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    if (event.getAction() == Action.LEFT_CLICK_BLOCK ||
        event.getAction() == Action.LEFT_CLICK_AIR) {//(2)
      final Player player = event.getPlayer();
      if (player.getItemInHand().getType() == Material.LEATHER) {
        Location loc = player.getLocation();
        Vector vec = loc.getDirection();
        int mult = 3;
        vec.setX(vec.getX() * mult);
        vec.setY(vec.getY() * mult);
        vec.setZ(vec.getZ() * mult);
 
        final Cow cow = player.getWorld().spawn(loc, Cow.class);//(3)
        cow.setVelocity(vec);
        cow.setFireTicks(20);
        BukkitRunnable runnable = new CowTask(player.getWorld(), cow);
        runnable.runTaskTimer(this, 0L, 0L);
      }
    }
  }
}
