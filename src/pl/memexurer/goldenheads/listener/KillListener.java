package pl.memexurer.goldenheads.listener;

import net.dzikoysk.funnyguilds.basic.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import pl.memexurer.goldenheads.MemeGoldenHeads;
import pl.memexurer.goldenheads.mgr.Config;
import pl.memexurer.goldenheads.util.Color;

public class KillListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if(MemeGoldenHeads.getConfiguration().fgAntiSkulls()) {
            Player p1 = e.getEntity();
            Player p2 = e.getEntity().getKiller();
            if(p2 != null) {
                if(User.get(p1).hasGuild() && User.get(p2).hasGuild()) {
                    User u1 = User.get(p1);
                    User u2 = User.get(p2);
                    if(u1.getGuild().getTag().equals(u2.getGuild().getTag())) {
                        return;
                    }
                }
            }

        }
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta sk = (SkullMeta) head.getItemMeta();
        sk.setOwner(e.getEntity().getName());
        sk.setDisplayName(Color.color("&8\u00BB &7Glowka gracza " + e.getEntity().getName() + " &8\u00AB"));
        head.setItemMeta(sk);
        e.getEntity().getWorld().dropItem(e.getEntity().getLocation(),head );
    }
}
