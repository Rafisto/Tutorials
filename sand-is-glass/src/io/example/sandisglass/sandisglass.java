package io.example.sandisglass;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class sandisglass extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Block block = e.getPlayer().getLocation().add(0,-1,0).getBlock();
		if(block.getType() == Material.SAND) {
			block.getWorld().spawnParticle(Particle.LAVA, block.getLocation().add(0.5,1,0.5), 10);
			block.setType(Material.GLASS);
		}
	}
}
