package adhdmc.sleepmessages.util;

import adhdmc.sleepmessages.SleepMessages;
import org.bukkit.configuration.file.FileConfiguration;

public class Defaults {

    public static void setConfigDefaults(){
        FileConfiguration config = SleepMessages.getInstance().getConfig();
        config.addDefault("world-specific-message", true);
        config.addDefault("purpur-afk-count-as-sleeping",false);
        config.addDefault("player-sleep-message", "<grey><playername> has fallen asleep. <sleeping> out of <needed> required players in <worldname> are sleeping.");
        config.addDefault("night-skip-message", "<grey>Enough players have slept! Skipping through the night in <worldname>.");
    }
}
