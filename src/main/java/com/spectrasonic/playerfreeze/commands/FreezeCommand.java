package com.spectrasonic.playerfreeze.commands;

import com.spectrasonic.playerfreeze.PlayerFreeze;
import com.spectrasonic.playerfreeze.utils.FreezeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {

    private final PlayerFreeze plugin;
    private final FreezeManager freezeManager;

    public FreezeCommand(PlayerFreeze plugin, FreezeManager freezeManager) {
        this.plugin = plugin;
        this.freezeManager = freezeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /freeze <player> | /freeze all | /freeze version");
            return true;
        }

        if (args[0].equalsIgnoreCase("all")) {
            freezeManager.freezeAllPlayers(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("version")) {
            sender.sendMessage("PlayerFreeze version: " + plugin.getVersion());
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("Player not found.");
            return true;
        }

        if (freezeManager.isPlayerFrozen(target)) {
            sender.sendMessage("Player is already frozen.");
            return true;
        }

        if (target.hasPermission("playerfreeze.bypass")) {
            sender.sendMessage("You cannot freeze this player. They have the bypass permission.");
            return true;
        }

        freezeManager.freezePlayer(target);
        sender.sendMessage("Player " + target.getName() + " has been frozen.");
        return true;
    }
}
