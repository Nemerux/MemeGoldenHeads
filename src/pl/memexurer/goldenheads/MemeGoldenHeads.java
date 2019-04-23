package pl.memexurer.goldenheads;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.memexurer.goldenheads.commands.GoldenHeadsReload;
import pl.memexurer.goldenheads.listener.AppleListener;
import pl.memexurer.goldenheads.listener.KillListener;
import pl.memexurer.goldenheads.mgr.Config;

import java.io.File;

public class MemeGoldenHeads extends JavaPlugin {
    private static Config config;
    @Override
    public void onEnable() {
        if(!new File(getDataFolder(), "config.yml").exists()) this.saveDefaultConfig();
        config = new Config(this.getConfig());
        Bukkit.getPluginManager().registerEvents(new AppleListener(), this);
        getCommand("goldenheads").setExecutor(new GoldenHeadsReload());
        Bukkit.addRecipe(config.getRecipe());
        if(config.autoGiveSkulls()) {
            Bukkit.getPluginManager().registerEvents(new KillListener(), this);
        }
    }

    public static void reload() {
        MemeGoldenHeads.getProvidingPlugin(MemeGoldenHeads.class).reloadConfig();
        config = new Config( MemeGoldenHeads.getProvidingPlugin(MemeGoldenHeads.class).getConfig());
    }

    public static Config getConfiguration() {
        return config;
    }
}
