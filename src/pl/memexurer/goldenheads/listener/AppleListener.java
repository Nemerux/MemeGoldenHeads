package pl.memexurer.goldenheads.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import pl.memexurer.goldenheads.MemeGoldenHeads;
import pl.memexurer.goldenheads.mgr.AppleManager;
import pl.memexurer.goldenheads.mgr.HeadType;

public class AppleListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(!AppleManager.isItemGoldenHead(e.getItem())) return;
        if(MemeGoldenHeads.getConfiguration().getHeadType() == HeadType.RIGHT_CLICK) {
            if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if(!AppleManager.eatApple(e.getPlayer())) {
                    e.getPlayer().sendMessage(ChatColor.RED + "Nastepne jablko mozesz zjesc za ");
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerItemConsumeEvent e) {
        if(!AppleManager.isItemGoldenHead(e.getItem())) return;
        if(MemeGoldenHeads.getConfiguration().getHeadType() == HeadType.EAT) {
            if(!AppleManager.eatApple(e.getPlayer())) {
                e.getPlayer().sendMessage(ChatColor.RED + "Nastepne jablko mozesz zjesc za ");
                e.setCancelled(true);
            }
        }
    }
}
