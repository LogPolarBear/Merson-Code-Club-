/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package hashplay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HashPlay extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
 
  HashMap<String, Integer> currentScores = new HashMap<String, Integer>(); //(1)
  
  public static void addToScore(HashMap<String, Integer> allScores, 
                      String playerName, 
                      int amount) { //(2)
    int score = allScores.get(playerName); //(3)
    score += amount; //(4)
    allScores.put(playerName, score); //(5)
  }

  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("hashplay")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        // Put your code after this line:

        currentScores.put("Andy", 1001);
        currentScores.put("Bob", 20);
        currentScores.put("Carol", 50);
        currentScores.put("Alice", 896);    
        addToScore(currentScores, "Bob", 500);
        me.sendMessage("Bob's score is " + currentScores.get("Bob"));

        // ...and finish your code before this line.
        return true;
        }
    }
    return false;
  }
}
