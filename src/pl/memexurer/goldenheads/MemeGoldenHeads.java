package pl.memexurer.goldenheads;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.memexurer.goldenheads.commands.GoldenHeadsReload;
import pl.memexurer.goldenheads.listener.AppleListener;
import pl.memexurer.goldenheads.mgr.Config;

public class MemeGoldenHeads extends JavaPlugin {
    private static Config config;
    @Override
    public void onEnable() {
        config = new Config(this.getConfig());
        Bukkit.getPluginManager().registerEvents(new AppleListener(), this);
        getCommand("goldenheads").setExecutor(new GoldenHeadsReload());
        Bukkit.addRecipe(config.getRecipe());
    }

    public static void reload() {
        MemeGoldenHeads.getProvidingPlugin(MemeGoldenHeads.class).reloadConfig();
        config = new Config( MemeGoldenHeads.getProvidingPlugin(MemeGoldenHeads.class).getConfig());
    }

    public static Config getConfiguration() {
        return config;
    }
}
