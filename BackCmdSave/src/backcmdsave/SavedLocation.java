/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package backcmdsave;

import java.util.Stack;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;

public class SavedLocation implements java.io.Serializable {
  public String worldName;
  public double x;
  public double y;
  public double z;
  
  public SavedLocation(Location loc) {
    worldName = loc.getWorld().getName();
    x = loc.getX();
    y = loc.getY();
    z = loc.getZ();
    // ...
  }
  
  public Location toLocation(Server s) {
    World world = s.getWorld(worldName);
    if (world == null) {
      System.err.println("Couldn't restore world named " + worldName);
    }
    Location loc = new Location(world, x, y, z);
    return loc;
    //...
  } 
  
  public static Stack<SavedLocation> LocationStackToSavedStack(
        Stack<Location> locsStack) {  
    Stack<SavedLocation> saveStack = new Stack<SavedLocation>();      
    for (Location loc : locsStack) {
      saveStack.push(new SavedLocation(loc));
    }
    return saveStack;
    //...
  }

  public static Stack<Location> SavedStackToLocationStack(Server s,
        Stack<SavedLocation> saveStack) {
    Stack<Location> locStack = new Stack<Location>();
    for (SavedLocation savedLoc : saveStack) {
      locStack.push(savedLoc.toLocation(s));
    }
    return locStack;
    //...
  }
}
