package io.example.explodingsnowballs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;

public class explodingsnowballs extends JavaPlugin implements Listener{

	ItemMeta meta;
	public String customBallName= "Stevie";
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		
		//crafting
		// item stack
		ItemStack snowball = new ItemStack(Material.SNOWBALL, 1);
		
		// enchantment
		snowball.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.ARROW_FIRE, 1);
		
		// item meta
		meta = snowball.getItemMeta();
		meta.setDisplayName("§e[§6§lMagic Snowball§r§e]");
		List<String> lore = new ArrayList<String>();
		lore.add("§e------------------------------");
		lore.add("§o§eDon't stop the magic snowball");
		lore.add("§o§eThey're having a ball");
		lore.add("§e------------------------------");
		meta.setLore(lore);
		snowball.setItemMeta(meta);
		
		// recipe
		NamespacedKey key = new NamespacedKey(this,"snowballtnt");
		ShapedRecipe recipe = new ShapedRecipe(key, snowball);
		recipe.shape("aaa","aba","aaa");
		recipe.setIngredient('a',Material.SNOW_BLOCK);
		recipe.setIngredient('b',Material.REDSTONE);
		getServer().addRecipe(recipe);
	}
	
	@EventHandler
	void OnSnowballThrow(ProjectileLaunchEvent e) {
	    if(e.getEntity() instanceof Snowball) {
	        Snowball snow = (Snowball) e.getEntity();
	        if(snow.getShooter() instanceof Player) {
	            ItemMeta i = ((Player)snow.getShooter()).getInventory().getItemInMainHand().getItemMeta();
	            if(i.equals(meta)) snow.setCustomName(customBallName);
	        }
	    }
	}
	
	
	@EventHandler
	void OnHit(ProjectileHitEvent e) {
		if(e.getEntity() instanceof Snowball){
			if(e.getEntity().getCustomName()!=null) {
				if(e.getEntity().getCustomName().equals((customBallName))){
					Entity tnt = e.getEntity().getWorld().spawn(e.getEntity().getLocation(),TNTPrimed.class);
					((TNTPrimed)tnt).setFuseTicks(0);
				}
			}
		}
	}
	
	
}
