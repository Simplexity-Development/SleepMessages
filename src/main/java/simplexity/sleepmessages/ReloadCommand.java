package simplexity.sleepmessages;

import simplexity.sleepmessages.util.SMMessage;
import simplexity.sleepmessages.util.SMPerm;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ReloadCommand implements CommandExecutor, TabCompleter {
    MiniMessage miniMessage = MiniMessage.miniMessage();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(SMPerm.RELOAD.getPerm())) return false;
        SleepMessages.getInstance().reloadConfig();
        SMMessage.reloadMessages();
        sender.sendMessage(miniMessage.deserialize(SMMessage.CONFIG_RELOADED.getMessage()));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
