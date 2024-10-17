package com.spectrasonic.playerfreeze.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class FreezeManager {

    private final Set<Player> frozenPlayers = new HashSet<>();

    public boolean isPlayerFrozen(Player player) {
        return frozenPlayers.contains(player);
    }

    public void freezePlayer(Player player) {
        frozenPlayers.add(player);
        player.sendMessage("You have been frozen!");
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
    }

    public void unfreezePlayer(Player player) {
        frozenPlayers.remove(player);
        player.sendMessage("You have been unfrozen!");
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 1.0f);
    }

    public void freezeAllPlayers(CommandSender sender) {
        int count = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("playerfreeze.bypass") && !isPlayerFrozen(player)) {
                freezePlayer(player);
                count++;
            }
        }
        sender.sendMessage("Frozen " + count + " player(s).");
    }

    public void unfreezeAllPlayers(CommandSender sender) {
        int count = frozenPlayers.size();
        for (Player player : new HashSet<>(frozenPlayers)) {
            unfreezePlayer(player);
        }
        sender.sendMessage("Unfroze " + count + " player(s).");
    }
}
