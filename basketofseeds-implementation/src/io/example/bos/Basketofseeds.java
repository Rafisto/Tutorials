package io.example.bos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Basketofseeds extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.WHEAT) {
			e.getPlayer().sendMessage("starting wheat recursion");
			PlaceOnNearFarmland(e.getBlock().getLocation().add(0, -1, 0), e.getPlayer().getWorld(), e.getPlayer());
		}

	}

	public void PlaceOnNearFarmland(Location location, World w, Player p) {
		Location l1 = new Location(location.getWorld(), location.getBlockX(), location.getBlockY(),
				location.getBlockZ());
		l1.add(1, 0, 0);
		Location l2 = new Location(location.getWorld(), location.getBlockX(), location.getBlockY(),
				location.getBlockZ());
		l2.add(-1, 0, 0);
		Location l3 = new Location(location.getWorld(), location.getBlockX(), location.getBlockY(),
				location.getBlockZ());
		l3.add(0, 0, 1);
		Location l4 = new Location(location.getWorld(), location.getBlockX(), location.getBlockY(),
				location.getBlockZ());
		l4.add(0, 0, -1);
		// getServer().getConsoleSender().sendMessage(l1.toString());
		if (l1.getBlock().getType() == Material.FARMLAND && l1.add(0, 1, 0).getBlock().getType() != Material.WHEAT) {
			if (p.getInventory().containsAtLeast(new ItemStack(Material.WHEAT_SEEDS), 1)) {
				p.getInventory().removeItem(new ItemStack(Material.WHEAT_SEEDS, 1));
				p.updateInventory();
				// getServer().getConsoleSender().sendMessage("farmland found");
				l1.getBlock().setType(Material.WHEAT);
				w.spawnParticle(Particle.WATER_SPLASH, l1, 1);
				l1.add(0, -1, 0);

				Bukkit.getScheduler().runTaskLater(this, () -> {
					PlaceOnNearFarmland(l1, w, p);
				}, 4L);
			}
		}
		// getServer().getConsoleSender().sendMessage(l2.toString());

		if (l2.getBlock().getType() == Material.FARMLAND && l2.add(0, 1, 0).getBlock().getType() != Material.WHEAT) {
			if (p.getInventory().containsAtLeast(new ItemStack(Material.WHEAT_SEEDS), 1)) {
				p.getInventory().removeItem(new ItemStack(Material.WHEAT_SEEDS, 1));
				p.updateInventory();
				// getServer().getConsoleSender().sendMessage("farmland found");
				l2.getBlock().setType(Material.WHEAT);
				w.spawnParticle(Particle.WATER_SPLASH, l2, 1);

				l2.add(0, -1, 0);

				Bukkit.getScheduler().runTaskLater(this, () -> {
					PlaceOnNearFarmland(l2, w, p);
				}, 4L);
			}
		}
		// getServer().getConsoleSender().sendMessage(l3.toString());
		if (l3.getBlock().getType() == Material.FARMLAND && l3.add(0, 1, 0).getBlock().getType() != Material.WHEAT) {
			if (p.getInventory().containsAtLeast(new ItemStack(Material.WHEAT_SEEDS), 1)) {
				p.getInventory().removeItem(new ItemStack(Material.WHEAT_SEEDS, 1));
				p.updateInventory();
				// getServer().getConsoleSender().sendMessage("farmland found");
				l3.getBlock().setType(Material.WHEAT);
				w.spawnParticle(Particle.WATER_SPLASH, l3, 1);
				l3.add(0, -1, 0);

				Bukkit.getScheduler().runTaskLater(this, () -> {
					// code
					PlaceOnNearFarmland(l3, w, p);
				}, 4L);
			}
		}
		// getServer().getConsoleSender().sendMessage(l4.toString());
		if (l4.getBlock().getType() == Material.FARMLAND && l4.add(0, 1, 0).getBlock().getType() != Material.WHEAT) {
			if (p.getInventory().containsAtLeast(new ItemStack(Material.WHEAT_SEEDS), 1)) {
				p.getInventory().removeItem(new ItemStack(Material.WHEAT_SEEDS, 1));
				p.updateInventory();
				// getServer().getConsoleSender().sendMessage("farmland found");
				l4.getBlock().setType(Material.WHEAT);
				w.spawnParticle(Particle.WATER_SPLASH, l4, 1);
				l4.add(0, -1, 0);

				Bukkit.getScheduler().runTaskLater(this, () -> {
					PlaceOnNearFarmland(l4, w, p);
				}, 4L);
			}
		}

	}

}
