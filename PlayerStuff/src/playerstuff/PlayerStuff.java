/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package playerstuff;

import java.util.logging.Logger;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerStuff extends JavaPlugin {
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("whoami")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        String msg = "Your list name is " + me.getPlayerListName();
        me.sendMessage(msg);
        me.setPlayerWeather(WeatherType.DOWNFALL); // or CLEAR
        float exp = me.getExp();
        int food = me.getFoodLevel();
        boolean grounded = ((Entity)me).isOnGround();
        String groundMsg = "";
        if (!grounded) {
          groundMsg = "not "; //(1)
        }
        me.sendMessage("Your experience points are " + exp + 
                ", food is " + food +
                "\nwater falls from the sky "
                + "and you are " + groundMsg + "on the ground."
                );
        return true;
      }
    }
    return false;
  }
}
