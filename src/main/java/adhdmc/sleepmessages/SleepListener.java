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
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.world.TimeSkipEvent;

import java.util.List;
import java.util.Objects;

public class SleepListener implements Listener {

    @EventHandler
    public void onPlayerSleep(PlayerDeepSleepEvent event){
        FileConfiguration config = SleepMessages.plugin.getConfig();
        if(config.getString("player-sleep-message") == null || Objects.equals(config.getString("player-sleep-message"), "")) return;
        String sleepMessage = config.getString("player-sleep-message");
        World world = event.getPlayer().getWorld();
        String playerName = event.getPlayer().getName();
        String worldName = world.getName();
        int currentSleepCount = 0;
        int worldOnlineTotal = world.getPlayerCount();
        Integer worldSleepPercent = world.getGameRuleValue(GameRule.PLAYERS_SLEEPING_PERCENTAGE);
        Integer neededSleepers = (worldSleepPercent/100)*worldOnlineTotal;
        List<Player> playerList = world.getPlayers();
        for (Player player : playerList){
            if(player.isDeeplySleeping()) currentSleepCount += 1;
        }
        world.sendMessage(MiniMessage.miniMessage().deserialize(sleepMessage,
                Placeholder.unparsed("playername", playerName),
                Placeholder.unparsed("sleeping", String.valueOf(currentSleepCount)),
                Placeholder.unparsed("needed", String.valueOf(neededSleepers)),
                Placeholder.unparsed("worldname", worldName)));
    }
    @EventHandler
    public void nightSkip(TimeSkipEvent event){
        FileConfiguration config = SleepMessages.plugin.getConfig();
        if(!event.getSkipReason().equals(TimeSkipEvent.SkipReason.NIGHT_SKIP)) return;
        if(config.getString("night-skip-message") == null || config.getString("night-skip-message").equals("")) return;
        String skipMessage = config.getString("night-skip-message");
        String worldName = event.getWorld().getName();
        event.getWorld().sendMessage(MiniMessage.miniMessage().deserialize(skipMessage, Placeholder.unparsed("worldname", worldName)));
    }

}
