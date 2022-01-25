package io.example.waternausea;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class waternausea extends JavaPlugin implements Listener {
		
	PotionEffectType peffecttype= PotionEffectType.CONFUSION;
	PotionEffect peffect;
	Material[] Watermaterials = {Material.WATER, Material.KELP_PLANT, Material.KELP,Material.SEAGRASS,Material.TALL_SEAGRASS};
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		peffect = new PotionEffect(peffecttype,Integer.MAX_VALUE,0);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Block b = e.getPlayer().getLocation().getBlock();
		boolean iswaterlogged = false;
		if(b.getBlockData() instanceof Waterlogged) {
			Waterlogged w = (Waterlogged)b.getBlockData();
			iswaterlogged = w.isWaterlogged();
		}
		if(Arrays.asList(Watermaterials).contains(b.getType()) || iswaterlogged==true) {
			e.getPlayer().addPotionEffect(peffect);
		}
		else {
			e.getPlayer().removePotionEffect(peffecttype);
		}
	}
}
