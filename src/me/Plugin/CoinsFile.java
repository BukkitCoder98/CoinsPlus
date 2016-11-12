package me.Plugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CoinsFile {
	
	private static CoinsFile classe = new CoinsFile();
	
	public static CoinsFile getFile(){
		return classe;
	}
	
	public File file;
	public FileConfiguration config;
	
	public void setup(Plugin p){
		if(!p.getDataFolder().exists()) p.getDataFolder().mkdir();
		file = new File(p.getDataFolder(), "coins.yml");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch(IOException e) {
				System.out.println("[Coins+] Erro ao gerar arquivo coins.yml !");
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save(){
		try {
			config.save(file);
		} catch(IOException e) {
			System.out.println("[Coins+] Erro ao salvar arquivo coins.yml !");
		}
	}
	
	public int getCoins(Player p){
		return config.getInt("Coins." + p.getUniqueId());
	}
	
	public int getCoins(OfflinePlayer p){
		return config.getInt("Coins." + p.getUniqueId());
	}
	
	public void setCoins(Player p, int coins){
		config.set("Coins." + p.getUniqueId(), coins);
		save();
	}
	
	public void setCoins(OfflinePlayer p, int coins){
		config.set("Coins." + p.getUniqueId(), coins);
		save();
	}
	
	public void addCoins(Player p, int coins){
		config.set("Coins." + p.getUniqueId(), getCoins(p) + coins);
		save();
	}
	
	public void addCoins(OfflinePlayer p, int coins){
		config.set("Coins." + p.getUniqueId(), getCoins(p) + coins);
		save();
	}
	
	public void removeCoins(Player p, int coins){
		if(getCoins(p) - coins < 0){
			config.set("Coins." + p.getUniqueId(), 0);
			save();
			return;
		}
		config.set("Coins." + p.getUniqueId(), getCoins(p) - coins);
		save();
	}
	
	public void removeCoins(OfflinePlayer p, int coins){
		if(getCoins(p) - coins < 0){
			config.set("Coins." + p.getUniqueId(), 0);
			save();
			return;
		}
		config.set("Coins." + p.getUniqueId(), getCoins(p) - coins);
		save();
	}
}
