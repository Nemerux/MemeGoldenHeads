package pl.memexurer.goldenheads.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import pl.memexurer.goldenheads.util.Color;

public class KillListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta sk = (SkullMeta) head.getItemMeta();
        sk.setOwner(e.getEntity().getName());
        sk.setDisplayName(Color.color("&8\u00BB &7Glowka gracza " + e.getEntity().getName() + " &8\u00AB"));
        head.setItemMeta(sk);
        e.getEntity().getWorld().dropItem(e.getEntity().getLocation(),head );
    }
}
