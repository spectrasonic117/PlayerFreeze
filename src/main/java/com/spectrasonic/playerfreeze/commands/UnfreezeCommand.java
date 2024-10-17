package com.spectrasonic.playerfreeze.commands;

import com.spectrasonic.playerfreeze.PlayerFreeze;
import com.spectrasonic.playerfreeze.utils.FreezeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnfreezeCommand implements CommandExecutor {

    private final FreezeManager freezeManager;

    public UnfreezeCommand(PlayerFreeze playerFreeze, FreezeManager freezeManager) {
        this.freezeManager = freezeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /unfreeze <player> | /unfreeze all");
            return true;
        }

        if (args[0].equalsIgnoreCase("all")) {
            freezeManager.unfreezeAllPlayers(sender);
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("Player not found.");
            return true;
        }

        if (!freezeManager.isPlayerFrozen(target)) {
            sender.sendMessage("Player is not frozen.");
            return true;
        }

        freezeManager.unfreezePlayer(target);
        sender.sendMessage("Player " + target.getName() + " has been unfrozen.");
        return true;
    }
}
