/***
 * Excerpted from "Learn to Program with Minecraft Plugins",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
***/
package locationsnapshot;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.plugin.java.JavaPlugin;

public class PermaMap {

    @SuppressWarnings("unchecked") //(1)
    public static HashMap<String, Object> load(
                JavaPlugin plugin, String aFileName) {    
      try {
        String filename = plugin.getDataFolder().getAbsolutePath() + 
                  File.separator + aFileName;
        File file = new File(filename);  
        FileInputStream finput = new FileInputStream(file);  
        ObjectInputStream stream = new ObjectInputStream(finput);
        Object pileOfBits = stream.readObject();
        stream.close();
        if (pileOfBits instanceof HashMap) {//(2)
          return (HashMap<String,Object>)pileOfBits;
        }        
      } catch (Exception e) {
        // ignore read error
        System.err.println(e);
      }  
      return new HashMap<String,Object>(); // Empty
    }

    public static boolean save(
          JavaPlugin plugin, String aFileName, Map<String, Object> data) {
      boolean result = true;
      try {
        String filename = plugin.getDataFolder().getAbsolutePath() + 
                  File.separator + aFileName;
        File file = new File(filename);
        file.getParentFile().mkdirs(); // Make parent dir if needed
        FileOutputStream foutput = new FileOutputStream(file);//(3)
        ObjectOutputStream stream = new ObjectOutputStream(foutput);//(4)
        stream.writeObject(data);
        stream.close();
      } catch (Exception e) {
        result = false;
        // Report error to log
        // if needed
        System.err.println(e);
      }
      return result;
    }
}
