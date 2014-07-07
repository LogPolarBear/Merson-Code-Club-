/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package creepercow;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Cow;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.Chunk;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class CreeperCow extends JavaPlugin implements Listener {
  public static Logger log = Logger.getLogger("Minecraft");
  
  private static HashMap<Cow, CreeperCowTimer> allCows = 
    new HashMap<Cow, CreeperCowTimer>();
    
  private final static int CHUNK_SIZE = 16;
  
  public void spawnCows(World world, double x, double z, int size, int count) {
    for (int i=0; i< count; i++) {
      Location loc = new Location(world,
        x + (Math.random() * size),
        0,
        z + (Math.random() * size)
      );
      loc.setY(world.getHighestBlockYAt(loc) + 2);
      log.info("[CreeperCow] spawned cow at " + loc);
      Cow cow = world.spawn(loc, Cow.class);
      allCows.put(cow, new CreeperCowTimer(this, cow));
    }
  }
  
  
  public void cowDied(Cow cow) {
    log.info("[CreeperCow] cow died.");
    allCows.remove(cow);
  }
  
  public void onEnable() {
    log.info("[CreeperCow] enabling.");
    getServer().getPluginManager().registerEvents(this, this);
  }


  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) { 
    if (sender instanceof Player) {
      Player me = (Player)sender;
               
      if (commandLabel.equalsIgnoreCase("testSpawnCows")) {
        Location loc = me.getLocation();
        spawnCows(loc.getWorld(), loc.getX(), loc.getY(),
            Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        return true;
      }
      if (commandLabel.equalsIgnoreCase("testJump")) {
        for (Cow c : allCows.keySet()) {
          CreeperCowTimer superCow = allCows.get(c);
          superCow.jump(me.getLocation());
        }
        return true;
      }
      if (commandLabel.equalsIgnoreCase("testJump")) {
        for (Cow c : allCows.keySet()) {
          CreeperCowTimer superCow = allCows.get(c);
          superCow.explode();
        }
        return true;
      }
    }
    return false;
  }
  
  @EventHandler
  public void onChunkLoad(ChunkLoadEvent event) {
    World world = event.getWorld();
    Chunk chunk = event.getChunk();
    
    if (Math.random() > 0.10) { // Only make a cow 1 in 10
      return;
    }
    log.info("[CreeperCow] Spawning");
    // The X and Z from the chunk are indexes;
    // we have to multiply by 16 to get an actual
    // block location.
    spawnCows(world, chunk.getX() * CHUNK_SIZE, 
                     chunk.getZ() * CHUNK_SIZE, 
                     16, 1);
  } 
  
  @EventHandler
  public void onChunkUnload(ChunkUnloadEvent event) {
    Entity[] ents = event.getChunk().getEntities();
    for(int i = 0; i < ents.length; i++) {
      if (ents[i] instanceof Cow) {
        Cow cow = (Cow) ents[i];
        if (allCows.containsKey(cow)) {
          allCows.get(cow).cancel();
          allCows.remove(cow);
        }
      }
    }
  }

  @EventHandler
  public void onEntityDamage(EntityDamageEvent event) {
    Entity ent = event.getEntity();
    if (ent instanceof Cow) {
      Cow cow = (Cow) ent;
      if (event.getCause() == DamageCause.FALL) {
        if (allCows.containsKey(cow)) {
          event.setCancelled(true);
        }
      }
    }
  }

}
