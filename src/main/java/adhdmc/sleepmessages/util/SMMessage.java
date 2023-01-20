package adhdmc.sleepmessages.util;

import adhdmc.sleepmessages.SleepMessages;
import org.bukkit.configuration.file.FileConfiguration;

public enum SMMessage {
    PLAYER_SLEEPING("<grey><playername> has fallen asleep. <sleeping> out of <needed> required players in <worldname> are sleeping."),
    PLAYER_STOPPED_SLEEPING("<grey><playername> has gotten out of bed. <sleeping> out of <needed> required players in <worldname> now sleeping."),
    NIGHT_SKIP("<grey>Enough players have slept! Skipping through the night in <worldname>."),
    CONFIG_RELOADED("<white>[<yellow>SleepMessages</yellow>]</white> <gray>»</gray> <gold>Sleep Message config has been reloaded!</gold>"),
    NO_PERMISSION("<red>Sorry, you do not have permission to use this command");
    String message;
    SMMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void reloadMessages(){
        FileConfiguration config = SleepMessages.getInstance().getConfig();
        PLAYER_SLEEPING.setMessage(config.getString("player-sleep-message"));
        NIGHT_SKIP.setMessage(config.getString("night-skip-message"));
        CONFIG_RELOADED.setMessage(config.getString("config-reloaded"));
    }

}
