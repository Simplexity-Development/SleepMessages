package adhdmc.sleepmessages;

import adhdmc.sleepmessages.util.Defaults;
import adhdmc.sleepmessages.util.SMMessage;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public final class SleepMessages extends JavaPlugin {
    private static SleepMessages instance;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private boolean purpurEnabled = true;

    @Override
    public void onEnable() {
        instance = this;
        Defaults.setConfigDefaults();
        SMMessage.reloadMessages();
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new SleepListener(), this);
        this.getCommand("smreload").setExecutor(new ReloadCommand());
        try {
            Class.forName("org.purpurmc.purpur.event.PlayerAFKEvent");
        } catch (ClassNotFoundException e) {
            if (this.getConfig().getBoolean("purpur-afk-count-as-sleeping")) {
                this.getLogger().warning("You have set \"purpur-afk-count-as-sleeping\" to \"true\" in your config, but you do not have the essential purpur classes to use this option. This purpur-specific option will not be usable until you are on a purpur build that support it.");
            }
            purpurEnabled = false;
        }
    }

    public static SleepMessages getInstance() {
        return instance;
    }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }

    public boolean isPurpurEnabled() {
        return purpurEnabled;
    }
}
