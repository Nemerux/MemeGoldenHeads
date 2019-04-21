package pl.memexurer.goldenheads.mgr;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import pl.memexurer.goldenheads.MemeGoldenHeads;

import java.util.concurrent.ConcurrentHashMap;

public class AppleManager {
    private static ConcurrentHashMap<String, Long> lastAppleEat = new ConcurrentHashMap<>();
    public static boolean eatApple(Player p) {
        if(!lastAppleEat.containsKey(p.getName())) {
            lastAppleEat.put(p.getName(), System.currentTimeMillis());
        } else {
            long diff = (System.currentTimeMillis() - lastAppleEat.get(p.getName())) / 1000;
            if(diff < MemeGoldenHeads.getConfiguration().getHeadDelay()) {
                return false;
            }
        }
        for(PotionEffect e: MemeGoldenHeads.getConfiguration().getHeadEffects()) {
            p.addPotionEffect(e);
        }
        p.sendMessage(ChatColor.GREEN + "Zjadles super jablko!");
        return true;
    }
    public static boolean isItemGoldenHead(ItemStack item) {
        if(item.getItemMeta().getDisplayName() == null) return false;
        if(item.getType() == MemeGoldenHeads.getConfiguration().getHeadItem().getType()) return true;
        if(item.getItemMeta().getDisplayName().equals(MemeGoldenHeads.getConfiguration().getHeadItem().getItemMeta().getDisplayName())) return true;
        return false;
    }
}
