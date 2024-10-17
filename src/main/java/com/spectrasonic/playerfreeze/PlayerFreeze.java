package com.spectrasonic.playerfreeze;

import com.spectrasonic.playerfreeze.commands.FreezeCommand;
import com.spectrasonic.playerfreeze.commands.UnfreezeCommand;
import com.spectrasonic.playerfreeze.events.PlayerMovementListener;
import com.spectrasonic.playerfreeze.utils.FreezeManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.Objects;

public class PlayerFreeze extends JavaPlugin {

    // Variables
    private static final String divider = "------------------------------";
    private static final String prefix = ChatColor.AQUA + "[PlayerFreeze]" + ChatColor.RESET + " ";

    public final String pluginVersion = getDescription().getVersion();
    public final String pluginName = getDescription().getName();
    public final String pluginAuthor = getDescription().getAuthors().toString();

    @Override
    public void onEnable() {
        // Initialize the freeze manager
        FreezeManager freezeManager = new FreezeManager();

        // Register commands
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new FreezeCommand(this, freezeManager));
        Objects.requireNonNull(getCommand("unfreeze")).setExecutor(new UnfreezeCommand(this, freezeManager));

        // Register event listeners
        getServer().getPluginManager().registerEvents(new PlayerMovementListener(freezeManager), this);

        // Enable
        getServer().getConsoleSender().sendMessage(divider);
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "GiveHead plugin enabled!");
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Version: " + ChatColor.AQUA + pluginVersion);
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "Developed by " + ChatColor.RED + pluginAuthor);
        getServer().getConsoleSender().sendMessage(divider);
    }

    @Override
    public void onDisable() {
        // Disable
        getServer().getConsoleSender().sendMessage(divider);
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.RED + pluginName + "plugin Disabled!");
        getServer().getConsoleSender().sendMessage(divider);

    }

    public String getVersion() {
        return getDescription().getVersion();
    }
}
