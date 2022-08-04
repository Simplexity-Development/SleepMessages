package adhdmc.sleepmessages;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SleepCommands implements CommandExecutor {
    MiniMessage mM = MiniMessage.miniMessage();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0) {
            sender.sendMessage(mM.deserialize("<green><click:open_url:'https://github.com/illogicalsong/SleepMessages'><hover:show_text:'<gray>Click here to visit the GitHub!</gray>'>SleepMessages | [_Rhythmic]"));
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
