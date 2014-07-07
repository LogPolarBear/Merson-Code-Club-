/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package arrayplay;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class ArrayPlay extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
  public void onEnable() {
    log.info("[ArrayPlay] Start up.");
  }
  public void onReload() {
    log.info("[ArrayPlay] Server reloaded.");
  }
  public void onDisable() {
    log.info("[ArrayPlay] Server stopping.");
  }
  
  public void playWithArrays(Player me) {
    String[] grades = {"A", "B", "C", "D", "F", "Inc"};
    String yourGrade = grades[2];
    
    me.sendMessage("Your grade is " + yourGrade);
    
    int numGrades = grades.length;
    
    me.sendMessage("There are " + numGrades + " grades");
    
    int[] quizScores = new int[5];
    quizScores[0] = 85;
    quizScores[1] = 92;
    quizScores[2] = 63;
    
    int myBestQuiz = quizScores[1];
    int aBadDay = quizScores[2];
    
    me.sendMessage("Your best quiz is " + myBestQuiz);
    me.sendMessage("Your worst quiz is " + aBadDay);

    me.sendMessage("All quizzes:");
    for (int i=0; i < 5; i++) {
      me.sendMessage("Quiz score #" + i + ": " + quizScores[i]);
    }
  }
  
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("arrayplay")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        // Put your code after this line:
        playWithArrays(me);
        // ...and finish your code before this line.
        return true;
        }
    }
    return false;
  }
}
