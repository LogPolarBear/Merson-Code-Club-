#!/bin/sh
#---
# Excerpted from "Learn to Program with Minecraft Plugins",
# published by The Pragmatic Bookshelf.
# Copyrights apply to this code. It may not be used to create training material, 
# courses, books, articles, and the like. Contact us if you are in doubt.
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/ahmine for more book information.
#---

#
# Simple script to create a plugin skeleton
#

if [ "$1" = "" ]
then
  echo "Usage: $0 NewPluginName"
  exit 1
fi

PLUGIN_NAME=`echo $* | sed 's/[ ]*//g'`
LC_PLUGIN_NAME=`echo $PLUGIN_NAME | tr [A-Z] [a-z]`

if [ -d "$PLUGIN_NAME" ]
then
  echo "Directory $PLUGIN_NAME already exists."
  exit 1
fi

echo "Making directories under $PLUGIN_NAME"

mkdir $PLUGIN_NAME
mkdir $PLUGIN_NAME/src
mkdir $PLUGIN_NAME/src/$LC_PLUGIN_NAME
mkdir $PLUGIN_NAME/bin
mkdir $PLUGIN_NAME/dist

echo "Creating build.sh"

cat > $PLUGIN_NAME/build.sh <<EOF
#!/bin/sh

# Set the variable MCSERVER to ~/Desktop/server
# unless it's already set
: \${MCSERVER:="\$HOME"/Desktop/server}

BUKKIT="\$MCSERVER/bukkit.jar"

# Make sure that the bukkit jar
# exists and is readable
if [ ! -r \$BUKKIT ]; then
    echo "\$BUKKIT doesn't seem to exist.  Make sure you have bukkit.jar installed at \$MCSERVER and run again.  If your server is not at \$MCSERVER, set your MCSERVER environment variable to point to the correct directory."
    exit 1
fi

# Make the build directories if they aren't there.
# Throw away any error if they are.
mkdir bin 2>/dev/null
mkdir dist 2>/dev/null

# Remove any previous build products
rm -f bin/*/*.class
rm -f dist/*.jar

# Get the name of this plugin
# from the directory it's in
HERE=\`pwd\`
NAME=\`basename "\$HERE"\`

# 1. Compile
echo "Compiling with javac..."
javac -Xlint:deprecation src/*/*.java -d bin -classpath "\$BUKKIT" -sourcepath src -target 1.6 -g:lines,vars,source -source 1.6 || exit 2

# 2. Build the jar
echo "Creating jar file..."
jar -cf dist/"\$NAME.jar" *.yml -C bin . || exit 3

# 3. Copy to server
echo "Deploying jar to \$MCSERVER/plugins..."
test ! -d "\$MCSERVER/plugins" && mkdir -p "\$MCSERVER/plugins" 
cp dist/\$NAME.jar "\$MCSERVER/plugins" || exit 4

echo "Completed Successfully."


EOF

chmod +x $PLUGIN_NAME/build.sh

echo "Creating Java template"

cat > $PLUGIN_NAME/src/$LC_PLUGIN_NAME/$PLUGIN_NAME.java <<EOF
package $LC_PLUGIN_NAME;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class $PLUGIN_NAME extends JavaPlugin {
  public static Logger log = Logger.getLogger("Minecraft");
  public void onEnable() {
    log.info("[$PLUGIN_NAME] Start up.");
  }
  public void onReload() {
    log.info("[$PLUGIN_NAME] Server reloaded.");
  }
  public void onDisable() {
    log.info("[$PLUGIN_NAME] Server stopping.");
  }

  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("$LC_PLUGIN_NAME")) {
      if (sender instanceof Player) { 
        Player me = (Player)sender;
        // Put your code after this line:

        // ...and finish your code before this line.
        return true;
        }
    }
    return false;
  }
}
EOF

echo "Creating configuration template"

cat > $PLUGIN_NAME/plugin.yml <<EOF
name: $PLUGIN_NAME

author: yourname

main: $LC_PLUGIN_NAME.$PLUGIN_NAME

commands:
    $LC_PLUGIN_NAME:
        description: Your description goes here
        usage: Usage of the command goes here
    ${LC_PLUGIN_NAME}_admin:
        description: Your description goes here
        usage: Usage of the command goes here

version: 0.1

EOF

cat > $PLUGIN_NAME/.gitignore <<EOF
bin
dist
EOF


