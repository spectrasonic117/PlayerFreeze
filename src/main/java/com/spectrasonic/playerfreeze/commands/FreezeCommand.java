package com.spectrasonic.playerfreeze.commands;

import com.spectrasonic.playerfreeze.Main;
import com.spectrasonic.playerfreeze.utils.FreezeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {

    private final Main plugin;
    private final FreezeManager freezeManager;

    public FreezeCommand(Main plugin, FreezeManager freezeManager) {
        this.plugin = plugin;
        this.freezeManager = freezeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /freeze <player> | /freeze all | /freeze version");
            return true;
        }

        if (args[0].equalsIgnoreCase("all")) {
            freezeManager.freezeAllPlayers(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(ChatColor.AQUA + "PlayerFreeze version: " +ChatColor.LIGHT_PURPLE + plugin.getVersion());
            sender.sendMessage(ChatColor.GOLD + "Developed by:"+ ChatColor.RED + "Spectrasonic");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        if (freezeManager.isPlayerFrozen(target)) {
            sender.sendMessage(ChatColor.AQUA + "Player is already frozen.");
            return true;
        }

        if (target.hasPermission("playerfreeze.bypass")) {
            sender.sendMessage(ChatColor.RED + "You cannot freeze this player. They have the bypass permission.");
            return true;
        }

        freezeManager.freezePlayer(target);
        sender.sendMessage(ChatColor.AQUA +"Player " + target.getName() + " has been frozen.");
        return true;
    }
}
