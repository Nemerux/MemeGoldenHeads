package pl.memexurer.goldenheads.mgr;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import pl.memexurer.goldenheads.MemeGoldenHeads;

import java.util.concurrent.ConcurrentHashMap;

public class AppleManager {
    private static ConcurrentHashMap<String, Long> lastAppleEat = new ConcurrentHashMap<>();
    public static long eatApple(Player p) {
        if(!lastAppleEat.containsKey(p.getName())) {
            lastAppleEat.put(p.getName(), System.currentTimeMillis());
        } else {
            long diff = (System.currentTimeMillis() - lastAppleEat.get(p.getName()));
            if(diff / 1000 < MemeGoldenHeads.getConfiguration().getHeadDelay()) {
                long rediff = MemeGoldenHeads.getConfiguration().getHeadDelay() * 1000 - diff;
                return rediff / 1000;
            }
        }
        for(PotionEffect e: MemeGoldenHeads.getConfiguration().getHeadEffects()) {
            p.addPotionEffect(e);
        }
        p.sendMessage(ChatColor.GREEN + "Zjadles super jablko!");
        return 0;
    }
    public static boolean isItemGoldenHead(ItemStack item) {
        if(item == null) return false;
        if(item.getItemMeta().getDisplayName() == null) return false;
        if(item.getType() == MemeGoldenHeads.getConfiguration().getHeadItem().getType()) return true;
        if(item.getItemMeta().getDisplayName().equals(MemeGoldenHeads.getConfiguration().getHeadItem().getItemMeta().getDisplayName())) return true;
        return false;
    }
}
