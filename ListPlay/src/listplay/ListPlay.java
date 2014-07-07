/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package listplay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ListPlay extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");

  public void listDemo(Player me) {
    List<String> listOfStrings = new ArrayList<String>(); //(1)
    listOfStrings.add("This"); //(2)
    listOfStrings.add("is");
    listOfStrings.add("a");
    listOfStrings.add("list.");
    
    String third = listOfStrings.get(2);//(3)
    me.sendMessage("The third element is " + third);
    
    me.sendMessage("List contains " + listOfStrings.size() + " elements.");//(4)
    
    listOfStrings.add(3, "fancy"); //(5)
    
    boolean hasIt = listOfStrings.contains("is"); //(6)
    me.sendMessage("Does list contain the word 'is'? " + hasIt);

    hasIt = listOfStrings.contains("kerfluffle");
    me.sendMessage("Does the list contain the word 'kerfluffle'? " + hasIt);
     // Print out each value in the list
    for(String value : listOfStrings) {
      me.sendMessage(value);
    }
     
    listOfStrings.clear(); //(7)
    me.sendMessage("Now it's cleared out, size is " + listOfStrings.size());
    
    hasIt = listOfStrings.contains("is");
    me.sendMessage("List contains the word 'is' now is " + hasIt);
  }

  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("listplay")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        // Put your code after this line:
        listDemo(me);
        // ...and finish your code before this line.
        return true;
        }
    }
    return false;
  }
}
