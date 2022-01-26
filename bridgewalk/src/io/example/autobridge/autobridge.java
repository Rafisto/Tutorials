package io.example.autobridge;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class autobridge extends JavaPlugin implements Listener {
	
	ItemMeta meta;
	ItemStack bridgeblock;
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		
		//crafting
		// item stack
		bridgeblock = new ItemStack(Material.COBBLESTONE, 1);
		
		// enchantment
		bridgeblock.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DIG_SPEED, 1);
		
		// item meta
		meta = bridgeblock.getItemMeta();
		meta.setDisplayName("§e[§6§lMagic bridgeblock§r§e]");

		List<String> lore = new ArrayList<String>();
		lore.add("§e------------------------------");
		lore.add("§o§eUse the magic bridgeblock");
		lore.add("§o§eTo never look at the blocks");
		lore.add("§e------------------------------");
		meta.setLore(lore);
		bridgeblock.setItemMeta(meta);
		// recipe
		NamespacedKey key = new NamespacedKey(this,"bridgeblockkey");
		ShapedRecipe recipe = new ShapedRecipe(key, bridgeblock);
		recipe.shape("aaa","aba","aaa");
		recipe.setIngredient('a',Material.COBBLESTONE);
		recipe.setIngredient('b',Material.REDSTONE);
		getServer().addRecipe(recipe);
	}
	double treshold = 0.55;
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Location currentloc = e.getPlayer().getLocation();
		if(e.getPlayer().getInventory().getItemInOffHand()!=null) {
			if(e.getPlayer().getInventory().getItemInOffHand().getItemMeta()!=null) {
				if(e.getPlayer().getInventory().getItemInOffHand().getItemMeta().getLore().equals(meta.getLore())) {	
					//getServer().getConsoleSender().sendMessage("true");
					double front = currentloc.getX()-Math.floor(currentloc.getX());
					double left = currentloc.getZ()-Math.floor(currentloc.getZ());
					//player below
					currentloc = e.getPlayer().getLocation();
					if(currentloc.add(0,-1,0).getBlock().getType()==Material.AIR)
					{
						currentloc.getBlock().setType(Material.COBBLESTONE);
					}
					//front
					currentloc = e.getPlayer().getLocation();
					if(front>treshold) {
						if(currentloc.add(1,-1,0).getBlock().getType()==Material.AIR)
						{
							currentloc.getBlock().setType(Material.COBBLESTONE);
						}
						
					}
					//back
					currentloc = e.getPlayer().getLocation();
					if(1-front>treshold) {
						if(currentloc.add(-1,-1,0).getBlock().getType()==Material.AIR)
						{
							currentloc.getBlock().setType(Material.COBBLESTONE);
						}
					}	
					//left
					currentloc = e.getPlayer().getLocation();
					if(left>treshold) {
						if(currentloc.add(0,-1,-1).getBlock().getType()==Material.AIR)
						{
							currentloc.getBlock().setType(Material.COBBLESTONE);
						}
						
					}
					//right
					currentloc = e.getPlayer().getLocation();
					if(1-left>treshold) {
						if(currentloc.add(0,-1,1).getBlock().getType()==Material.AIR)
						{
							currentloc.getBlock().setType(Material.COBBLESTONE);
						}
					}
					//front left
					currentloc = e.getPlayer().getLocation();
					if(front>treshold && left>treshold) {
						if(currentloc.add(1,-1,-1).getBlock().getType()==Material.AIR)
						{
							currentloc.getBlock().setType(Material.COBBLESTONE);
						}
						
					}
					//back left
					currentloc = e.getPlayer().getLocation();
					if(1-front>treshold && left>treshold) {
						if(currentloc.add(-1,-1,1).getBlock().getType()==Material.AIR)
						{
							currentloc.getBlock().setType(Material.COBBLESTONE);
						}
					}	
					//front right
					currentloc = e.getPlayer().getLocation();
					if(front>treshold && 1-left>treshold) {
						if(currentloc.add(1,-1,1).getBlock().getType()==Material.AIR)
						{
							currentloc.getBlock().setType(Material.COBBLESTONE);
						}
						
					}
					//back right
					currentloc = e.getPlayer().getLocation();
					if(1-front>treshold && 1-left>treshold) {
						if(currentloc.add(-1,-1,1).getBlock().getType()==Material.AIR)
						{
							currentloc.getBlock().setType(Material.COBBLESTONE);
						}
					}	
				}
				else {
					//e.getPlayer().sendMessage("you are not holding the item");
				}
			}
		}
	}
}
