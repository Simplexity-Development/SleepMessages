package adhdmc.sleepmessages;

import io.papermc.paper.event.player.PlayerDeepSleepEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.TimeSkipEvent;

import java.util.List;

public class SleepListener implements Listener {

    @EventHandler
    public void onPlayerSleep(PlayerDeepSleepEvent event) {
        FileConfiguration config = SleepMessages.plugin.getConfig();
        String sleepMessage = config.getString("player-sleep-message", "");
        if (sleepMessage.isEmpty()) return;
        World world = event.getPlayer().getWorld();
        String playerName = event.getPlayer().getName();
        String worldName = world.getName();
        int currentSleepCount = 0;
        int worldOnlineTotal = world.getPlayerCount();
        Integer worldSleepPercent = world.getGameRuleValue(GameRule.PLAYERS_SLEEPING_PERCENTAGE);
        Integer neededSleepers = (worldSleepPercent / 100) * worldOnlineTotal;
        List<Player> playerList = world.getPlayers();
        for (Player player : playerList) {
            if (player.isDeeplySleeping()) currentSleepCount += 1;
        }
        world.sendMessage(MiniMessage.miniMessage().deserialize(sleepMessage,
                Placeholder.unparsed("playername", playerName),
                Placeholder.unparsed("sleeping", String.valueOf(currentSleepCount)),
                Placeholder.unparsed("needed", String.valueOf(neededSleepers)),
                Placeholder.unparsed("worldname", worldName)));
    }

    @EventHandler
    public void nightSkip(TimeSkipEvent event) {
        FileConfiguration config = SleepMessages.plugin.getConfig();
        if (!event.getSkipReason().equals(TimeSkipEvent.SkipReason.NIGHT_SKIP)) return;
        String skipMessage = config.getString("night-skip-message", "");
        if (skipMessage.isEmpty()) return;
        String worldName = event.getWorld().getName();
        event.getWorld().sendMessage(MiniMessage.miniMessage().deserialize(skipMessage, Placeholder.unparsed("worldname", worldName)));
    }
}
