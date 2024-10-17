package com.spectrasonic.playerfreeze.events;

import com.spectrasonic.playerfreeze.utils.FreezeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovementListener implements Listener {

    private final FreezeManager freezeManager;

    public PlayerMovementListener(FreezeManager freezeManager) {
        this.freezeManager = freezeManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (freezeManager.isPlayerFrozen(player)) {
            event.setTo(event.getFrom()); // Prevent the player from moving
        }
    }
}
