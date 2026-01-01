package adhdmc.sleepmessages;

import adhdmc.sleepmessages.util.Defaults;
import adhdmc.sleepmessages.util.SMMessage;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public static @NotNull TagResolver papi(@Nullable Player player) {
        return TagResolver.resolver("papi", (ArgumentQueue args, Context ctx) -> {
            if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) return null;
            if (!args.hasNext()) return null;
            String placeholder = "%" + args.pop().value() + "%";
            String parsed = PlaceholderAPI.setPlaceholders(player, placeholder);
            if (parsed.equals(placeholder)) return null;
            Component component = LegacyComponentSerializer.legacySection().deserialize(parsed);
            return Tag.selfClosingInserting(component);
        });
    }
}
