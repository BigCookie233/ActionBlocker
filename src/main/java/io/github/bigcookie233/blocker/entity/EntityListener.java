package io.github.bigcookie233.blocker.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.projectiles.ProjectileSource;

public class EntityListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent event) {
        if (shouldCancelEvent(event)) {
            event.getEntity().setFireTicks(0);
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onAttack(EntityDamageByEntityEvent event) {
        if (shouldCancelEvent(event)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onEntityTarget(EntityTargetEvent event) {
        if (shouldCancelEvent(event)) {
            event.setTarget(null);
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (shouldCancelEvent(event)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void entityRegainHealthEvent(EntityRegainHealthEvent event) {
        if (shouldCancelEvent(event)) {
            event.setAmount(0);
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onLowestEntityInteract(EntityInteractEvent event) {
        if (shouldCancelEvent(event)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        final Projectile projectile = event.getEntity();

        ProjectileSource shooter = projectile.getShooter();
        if (shooter instanceof Player && shouldCancelEvent(((Player) shooter))) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onShoot(EntityShootBowEvent event) {
        if (shouldCancelEvent(event)) {
            event.setCancelled(true);
        }
    }

    private boolean shouldCancelEvent(EntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            return shouldCancelEvent(player);
        }
        return false;
    }

    private boolean shouldCancelEvent(Player player) {
        if (player != null) {
            return !player.hasPermission("actionblocker.bypass");
        }
        return false;
    }
}
