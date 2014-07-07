/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package creepercow;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
public class CreeperCowTimer extends BukkitRunnable {
  private Cow cow;
  private CreeperCow plugin;
  private BukkitTask task;
  
  CreeperCowTimer(CreeperCow parentPlugin, Cow aCow) {
    cow = aCow;
    plugin = parentPlugin;
    runTaskTimer(plugin, 10, 10);
  }
  
  public void run() {
    if (cow.isOnGround()) { // otherwise it's still jumping
      Location cowLoc = cow.getLocation();
      Player p = getClosestPlayer(cowLoc);
      if (p == null) {
        return;
      }
      Location pLoc = p.getLocation();
      double dist = distance(cowLoc, pLoc);
    
      if (dist <= 4) {
        explode();
      } else if (dist <= 20) {
        jump(pLoc);
      }
    }
  }
  
  public Player getClosestPlayer(Location loc) { //return -1 on failure
    List<Player> list = loc.getWorld().getPlayers();
    Player closestPlayer = null;
    double minDistance = -1;
    for(int i = 0; i < list.size(); i++) {
      Player p = list.get(i);
      Location ploc = p.getLocation();
      if (Math.abs(ploc.getY() - loc.getY()) < 15) {
        double dist = distance(loc, ploc);
        if (dist < minDistance || minDistance == -1) {
          minDistance = dist;
          closestPlayer = p;
        }
      }
    }
    return closestPlayer;
  }
  //
  // Find the distance on the ground (ignores height)
  // between two Locations
  //
  public double distance(Location loc1, Location loc2) {
    return Math.sqrt(
      Math.pow(loc1.getX() - loc2.getX(), 2) +
      Math.pow(loc1.getZ() - loc2.getZ(), 2)
    );
  }
  
  // Explode yourself
  public void explode() {
    plugin.cowDied(cow); // notify parent
    Location cowLoc = cow.getLocation();
    cow.getWorld().createExplosion(cowLoc, 6f, true);
    cow.setHealth(0.0); 
    cancel(); // this task
  }
    
  // Jump this cow toward the target
  public void jump(Location target) {
    Location cowLoc = cow.getLocation();
    double multFactor = 0.1;
    Vector v = new Vector(
      (target.getX() - cowLoc.getX()) * multFactor,
      0.5,
      (target.getZ() - cowLoc.getZ()) * multFactor
    );
    cow.setVelocity(v); 
  }  
}

