package io.example.tntspleef;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class spleef extends JavaPlugin implements Listener{

	String spleeftntname = "no-explosion";
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	void OnHit(ProjectileHitEvent e) {
		if(e.getHitEntity()!=null) return;
		Block b = e.getHitBlock();
		if(b.getType().equals(Material.TNT)) {
			TNTPrimed tnt = b.getWorld().spawn(b.getLocation().add(new Vector(0.5,0,0.5)), TNTPrimed.class);			
			tnt.setCustomName(spleeftntname);
			tnt.setVelocity(new Vector(0,0,0));
		    b.setType(Material.AIR);
		    tnt.setFuseTicks(100);
			e.getEntity().remove();
		}
	}
	
    @EventHandler
    public void onExplosion(EntityExplodeEvent e){
    	if(e.getEntity().getType() == EntityType.PRIMED_TNT){
    		if(e.getEntity().getCustomName()!=null) {
				if(e.getEntity().getCustomName().equals((spleeftntname))){
						e.setCancelled(true);
				}
    		}
    	}
    }
}
