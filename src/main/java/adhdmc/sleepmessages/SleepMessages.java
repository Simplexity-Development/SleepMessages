package adhdmc.sleepmessages;

import org.bukkit.plugin.java.JavaPlugin;

public final class SleepMessages extends JavaPlugin {
    public static SleepMessages plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getConfig().addDefault("player-sleep-message", "<grey><playername> has fallen asleep. <sleeping> out of <needed> required players in <worldname> are sleeping.");
        this.getConfig().addDefault("night-skip-message", "<grey>Enough players have slept! Skipping through the night in <worldname>.");
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new SleepListener(), this);
        this.getCommand("sleepmessages").setExecutor(new SleepCommands());
    }
}
