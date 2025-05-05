package io.github.bigcookie233.blocker;

import io.github.bigcookie233.blocker.entity.EntityListener;
import io.github.bigcookie233.blocker.entity.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ActionBlocker extends JavaPlugin {
    @Override
    public void onEnable() {
        this.registerEventListeners();
    }

    public void registerEventListeners() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        pluginManager.registerEvents(new EntityListener(), this);
        pluginManager.registerEvents(new PlayerListener(), this);
    }
}
