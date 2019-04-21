package pl.memexurer.goldenheads.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.memexurer.goldenheads.MemeGoldenHeads;

public class GoldenHeadsReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("goldenheads.reload")) {
            MemeGoldenHeads.reload();
            commandSender.sendMessage(ChatColor.GREEN + "Config pluginu zostal przeladowany!");
        }
        return true;
    }
}
