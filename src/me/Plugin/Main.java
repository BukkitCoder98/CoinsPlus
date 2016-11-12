package me.Plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main instance;
	public CoinsFile coinsfile = CoinsFile.getFile();
	public static String PREFIX = "§e§lCoins+>> ";
	
	public void onEnable(){
		instance = this;
		System.out.println("[Coins+] Plugin desenvolvido por Coder98! Skype: jake.newskype");
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new CoinsListeners(), this);
		getCommand("coins").setExecutor(new CoinsCommands());
		getCommand("admincoins").setExecutor(new CoinsCommands());
		if(getConfig().getBoolean("MySQL.Ativar")){
			System.out.print("[Coins+] Sistema MySQL ativado!");
			CoinsMySQL.gerarTabelas();
		} else {
			System.out.print("[Coins+] Sistema Files ativado!");
			coinsfile.setup(this);
		}
	}
}
