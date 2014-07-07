/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package stuck;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Stuck extends JavaPlugin {
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {
    if (commandLabel.equalsIgnoreCase("Stuck")) {
      if(args.length == 1) {
        Player player = getServer().getPlayer(args[0]);
        if (player != null) { return stuck(player); }
      } else {
        sender.sendMessage("Usage: /stuck playerName");
      }
    }
    return false;
  }
  public boolean stuck(Player player) {
    World world = player.getWorld();
    Location loc = player.getLocation();
    int playerX = (int) loc.getX(); 
    int playerY = (int) loc.getY(); 
    int playerZ = (int) loc.getZ();
    loc.setX(playerX + 0.5); loc.setY(playerY); loc.setZ(playerZ + 0.5);
    player.teleport(loc);

    int[][] offsets = {
     //x,  y,  z
      {0,  -1, 0},
      {0,  2,  0},
      {1,  0,  0},
      {1,  1,  0},
      {-1, 0,  0},
      {-1, 1,  0},
      {0,  0,  1},
      {0,  1,  1},
      {0,  0, -1},
      {0,  1, -1},
    };

    for(int i = 0; i < offsets.length; i++) {
      int x = offsets[i][0]; 
      int y = offsets[i][1]; 
      int z = offsets[i][2];
      Block b = world.getBlockAt(x + playerX, y + playerY, z + playerZ);
      b.setType(Material.STONE);
    }
    return true;
  }
}
