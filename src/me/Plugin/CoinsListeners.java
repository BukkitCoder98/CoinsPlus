package me.Plugin;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Horse;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class CoinsListeners implements Listener {
	
	public CoinsFile c = CoinsFile.getFile();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
			if(!CoinsMySQL.playerExiste(p)){
				CoinsMySQL.criarPlayer(p);
			}
		}
	}
	
	@EventHandler
	public void onPvP(PlayerDeathEvent e){
		if(e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity();
			Player k = (Player)p.getKiller();
			if(Main.instance.getConfig().getBoolean("GanharCoinsAoMatar")){
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(k, Main.instance.getConfig().getInt("GanharCoinsAoMatar.Quantia"));
					k.sendMessage(Main.PREFIX + "§cVoce matou o(a) " + p.getDisplayName());
					k.sendMessage("§c-" + Main.instance.getConfig().getInt("GanharCoinsAoMatar.Quantia") + " coins!");
				} else {
					c.addCoins(k, Main.instance.getConfig().getInt("GanharCoinsAoMatar.Quantia"));
					k.sendMessage(Main.PREFIX + "§cVoce matou o(a) " + p.getDisplayName());
					k.sendMessage("§c-" + Main.instance.getConfig().getInt("GanharCoinsAoMatar.Quantia") + " coins!");
				}
			}
			if(Main.instance.getConfig().getBoolean("PerderCoinsAoMorrer")){
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.removeCoins(p, Main.instance.getConfig().getInt("PerderCoinsAoMorrer.Quantia"));
					p.sendMessage(Main.PREFIX + "§cVoce morreu para o(a) " + k.getDisplayName());
					p.sendMessage("§c-" + Main.instance.getConfig().getInt("PerderCoinsAoMorrer.Quantia") + " coins!");
				} else {
					c.removeCoins(p, Main.instance.getConfig().getInt("PerderCoinsAoMorrer.Quantia"));
					p.sendMessage(Main.PREFIX + "§cVoce morreu para o(a) " + k.getDisplayName());
					p.sendMessage("§c-" + Main.instance.getConfig().getInt("PerderCoinsAoMorrer.Quantia") + " coins!");
				}
			}
		}
	}
	
	@EventHandler
	public void onMob(EntityDeathEvent e){
		if(e.getEntity() instanceof Blaze && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsBlaze");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsBlaze")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um blaze!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Spider && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsAranha");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsAranha")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar uma aranha!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Chicken && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsGalinha");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsGalinha")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar uma galinha!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Cow && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsVaca");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsVaca")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar uma vaca!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Creeper && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsCreeper");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsCreeper")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um creeper!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Enderman && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsEnderman");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsEnderman")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um enderman!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof IronGolem && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsIronGolem");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsIronGolem")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um golem de ferro!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof MagmaCube && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsMagmaCube");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsMagmaCube")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um cubo de magma!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof MushroomCow && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsVacaCogu");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsVacaCogu")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar uma vaca cogumelo!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Pig && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsPorco");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsPorco")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um porco!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Sheep && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsOvelha");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsOvelha")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar uma ovelha!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Slime && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsSlime");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsSlime")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um slime!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Skeleton && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsEsqueleto");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsEsqueleto")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um esqueleto!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Zombie && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsZumbi");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsZumbi")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um zumbi!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof PigZombie && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsPorcoZumbi");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsPorcoZumbi")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um porco zumbi!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Horse && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsCavalo");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsCavalo")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar um cavalo!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
		if(e.getEntity() instanceof Squid && e.getEntity().getKiller() instanceof Player){
			Player p = (Player)e.getEntity().getKiller();
			int coins = Main.instance.getConfig().getInt("CoinsLula");
			if(Main.instance.getConfig().getBoolean("AtivarCoinsLula")){
				p.sendMessage(Main.PREFIX + "§9Voce ganhou §6" + coins + " coins §9por matar uma lula!");
				if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
					CoinsMySQL.addCoins(p, coins);
				} else {
					c.addCoins(p, coins);
				}
			}
		}
	}
}
