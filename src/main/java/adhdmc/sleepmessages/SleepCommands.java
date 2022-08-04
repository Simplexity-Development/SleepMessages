package adhdmc.sleepmessages;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;


public class SleepCommands implements CommandExecutor {
    MiniMessage mM = MiniMessage.miniMessage();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0) {
            sender.sendMessage(mM.deserialize("<green><click:open_url:'https://github.com/illogicalsong/SleepMessages'><hover:show_text:'<gray>Click here to visit the GitHub!</gray>'>SleepMessages Version: <version> | Authors: [_Rhythmic]", Placeholder.unparsed("version", String.valueOf(SleepMessages.version))));
            return true;
        }
        if(args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("sleepmessages.commands")){
            SleepMessages.plugin.reloadConfig();
            sender.sendMessage(mM.deserialize("<gold>SleepMessages config has been reloaded!"));
            return true;
        }
        sender.sendMessage(mM.deserialize("<red>unknown command, please check that you have entered this command correctly"));
        return false;
    }
}
