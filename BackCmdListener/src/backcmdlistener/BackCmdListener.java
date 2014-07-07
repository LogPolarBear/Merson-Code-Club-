/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package backcmdlistener;

import java.util.List;
import java.util.Stack;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BackCmdListener implements Listener {

  @EventHandler
  public void onTeleport(PlayerTeleportEvent event) {
    Player player = event.getPlayer();
    if (BackCmd.isTeleporting.contains(player)) {
      BackCmd.isTeleporting.remove(player);
    } else {
      Stack<Location> locs = BackCmd.playerTeleports.get(player);
      if (locs == null) {
        locs = new Stack<Location>();
      }
      locs.push(event.getFrom());
      locs.push(event.getTo());
      BackCmd.playerTeleports.put(player, locs);
    }
  }
}