package io.example.structures;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.World;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;

import net.minecraft.world.item.enchantment.Enchantment;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class structures extends JavaPlugin implements Listener {
	

	public File file; 
	public String filename = "house.schem";
	public ItemMeta meta;
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		file = new File(getServer().getPluginManager().getPlugin("WorldEdit").getDataFolder()+"/schematics",filename);
		//crafting
		// item stack
		ItemStack pot = new ItemStack(Material.FLOWER_POT, 1);
		
		// enchantment
		pot.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LUCK, 10);
		
		// item meta
		meta = pot.getItemMeta();
		meta.setDisplayName("§e[§6§lHouse§r§e]");
		List<String> lore = new ArrayList<String>();
		lore.add("§e------------------------------");
		lore.add("§o§ePlace on the ground using your mouse");
		lore.add("§o§eThe pot will summon a tiny house");
		lore.add("§e------------------------------");
		meta.setLore(lore);
		pot.setItemMeta(meta);
		
		// recipe
		NamespacedKey key = new NamespacedKey(this,"pot_house");
		ShapedRecipe recipe = new ShapedRecipe(key, pot);
		recipe.shape("aba");
		recipe.setIngredient('a',Material.DIAMOND);
		recipe.setIngredient('b',Material.COBBLESTONE);
		getServer().addRecipe(recipe);
	}	
	public String getDirection(Location loc) {
		  double rot = loc.getYaw() % 360;
		  if (rot < 0) {
		    rot += 360.0;
		  }
		  if (0 <= rot && rot < 45) {
		    return "S";
		  } else if (45 <= rot && rot < 135) {
		    return "W";
		  } else if (135 <= rot && rot < 225) {
		    return "N";
		  } else if (225 <= rot && rot < 315) {
		    return "E";
		  } else if (315 <= rot && rot < 360.0) {
		    return "S";
		  } else {
		    return "E";
		  }
		}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(e.getBlock().getType() == Material.FLOWER_POT && e.getItemInHand().getItemMeta().equals(meta)) {
			ReadPlace(e.getPlayer().getWorld(),e.getBlock().getLocation(),file,getDirection(e.getPlayer().getLocation()));
			e.getBlock().setType(Material.AIR);
		}
	}
	
	public void ReadPlace(World world, Location location, File pastefile, String direction) {
		world.spawnParticle(Particle.ENCHANTMENT_TABLE, location, 100);
		try {
		getServer().getConsoleSender().sendMessage(pastefile.toString());
		ClipboardFormat format = ClipboardFormats.findByFile(pastefile);
		ClipboardReader reader = format.getReader(new FileInputStream(pastefile));
	    Clipboard clipboard = reader.read();	
	    com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(world);
	    try (EditSession editSession = com.sk89q.worldedit.WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(location.getWorld()), -1)){
			ClipboardHolder c = new ClipboardHolder(clipboard);
			switch(direction) {
				case "N":
					c.setTransform(new AffineTransform().rotateY(0));
			    break;
			    case "S":
					c.setTransform(new AffineTransform().rotateY(180));
			    break;
			    case "W":
					c.setTransform(new AffineTransform().rotateY(90));
				    break;
			    case "E":
					c.setTransform(new AffineTransform().rotateY(-90));
				    break;
			}
	    	Operation operation = c			
					.createPaste(editSession)	
					.to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
					.build();			
			Operations.complete(operation);
	    }
		}
		catch (Exception e){
			getServer().getConsoleSender().sendMessage("Error pasting structure.");
		}
	}
	
}

