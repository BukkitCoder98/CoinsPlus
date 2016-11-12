package me.Plugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommands implements CommandExecutor {

	public static CoinsFile c = CoinsFile.getFile();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(label.equalsIgnoreCase("coins")){
				if(args.length == 0){
					if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
						p.sendMessage(Main.PREFIX + "§9§lVoce possui §6§l " + CoinsMySQL.getCoins(p) + " coins!");
						p.sendMessage(Main.PREFIX + "§cVoce tambem pode usar /coins [(Jogador)/Top] !");
					} else {
						p.sendMessage(Main.PREFIX + "§9§lVoce possui §6§l" + c.getCoins(p) + " coins!");
						p.sendMessage(Main.PREFIX + "§cVoce tambem pode usar /coins [(Jogador)/Top] !");
					}
					return true;
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("top")){
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							mysqlRanking(p);
						} else {
							normalRanking(p);
						}
						return true;
					}
					Player t = Bukkit.getPlayer(args[0]);
					if(t == null){
						@SuppressWarnings("deprecation")
						OfflinePlayer offt = Bukkit.getOfflinePlayer(args[0]);
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							if(CoinsMySQL.playerExiste(offt)){
								p.sendMessage(Main.PREFIX + "§9§l" + offt.getName() + " possui §6§l " + CoinsMySQL.getCoins(offt) + " coins!");
								p.sendMessage(Main.PREFIX + "§cVoce tambem pode usar /coins top !");
							} else {
								p.sendMessage(Main.PREFIX + "§cEste jogador nao foi encontrado em nosso banco de dados!");
							}
						} else {
							p.sendMessage(Main.PREFIX + "§9§l" + offt.getName() + " possui §6§l " + c.getCoins(offt) + " coins!");
							p.sendMessage(Main.PREFIX + "§cVoce tambem pode usar /coins top !");
						}
					} else {
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							p.sendMessage(Main.PREFIX + "§9§l" + t.getName() + " possui §6§l " + CoinsMySQL.getCoins(t) + " coins!");
							p.sendMessage(Main.PREFIX + "§cVoce tambem pode usar /coins top !");
						} else {
							p.sendMessage(Main.PREFIX + "§9§l" + t.getName() + " possui §6§l " + c.getCoins(t) + " coins!");
							p.sendMessage(Main.PREFIX + "§cVoce tambem pode usar /coins top !");
						}
					}
					return true;
				}
				p.sendMessage(Main.PREFIX + "§cUse /coins ou /coins [(Jogador)/Top]");
			}
		} else {
			sender.sendMessage(Main.PREFIX + "§cEsse comando so pode ser utilizado por jogadores!");
		}
		if(label.equalsIgnoreCase("admincoins")){
			if(sender.hasPermission("coins.admin")){
				if(args.length == 0){
					sender.sendMessage(Main.PREFIX + "§cUse /admincoins [Jogador] [Dar/Remover/Set] [Quantia] !");
					return true;
				}
				Player t = Bukkit.getPlayer(args[0]);
				int coins = Integer.parseInt(args[2]);
				if(t != null){
					if(args[1].equalsIgnoreCase("dar")){
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							CoinsMySQL.addCoins(t, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce enviou §6" + coins + " coins §9 para o(a) " + t.getDisplayName());
						} else {
							c.addCoins(t, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce enviou §6" + coins + " coins §9 para o(a) " + t.getDisplayName());
						}
					}
					if(args[1].equalsIgnoreCase("remover")){
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							CoinsMySQL.removeCoins(t, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce retirou §6" + coins + " coins §9 do(a) " + t.getDisplayName());
						} else {
							c.removeCoins(t, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce retirou §6" + coins + " coins §9 do(a) " + t.getDisplayName());
						}
					}
					if(args[1].equalsIgnoreCase("set")){
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							CoinsMySQL.setCoins(t, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce setou §6" + coins + " coins §9 no(a) " + t.getDisplayName());
						} else {
							c.setCoins(t, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce setou §6" + coins + " coins §9 no(a) " + t.getDisplayName());
						}
					}
				} else {
					@SuppressWarnings("deprecation")
					OfflinePlayer toff = Bukkit.getOfflinePlayer(args[0]);
					if(args[1].equalsIgnoreCase("dar")){
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							CoinsMySQL.addCoins(toff, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce enviou §6" + coins + " coins §9 para o(a) " + toff.getName());
						} else {
							c.addCoins(toff, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce enviou §6" + coins + " coins §9 para o(a) " + toff.getName());
						}
					}
					if(args[1].equalsIgnoreCase("remover")){
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							CoinsMySQL.removeCoins(toff, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce retirou §6" + coins + " coins §9 do(a) " + toff.getName());
						} else {
							c.removeCoins(toff, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce retirou §6" + coins + " coins §9 do(a) " + toff.getName());
						}
					}
					if(args[1].equalsIgnoreCase("set")){
						if(Main.instance.getConfig().getBoolean("MySQL.Ativar")){
							CoinsMySQL.setCoins(toff, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce setou §6" + coins + " coins §9 no(a) " + toff.getName());
						} else {
							c.setCoins(toff, coins);
							sender.sendMessage(Main.PREFIX + "§9Voce setou §6" + coins + " coins §9 no(a) " + toff.getName());
						}
					}
				}
			} else {
				sender.sendMessage(Main.PREFIX + "§cVoce nao tem permissao para usar este comando!");
			}
		}
		return false;
	}
	
	public static void mysqlRanking(Player p){
		try {
			Connection con = new SQLite().getConnection();
			String s = "SELECT * FROM Coins ORDER BY Coins DESC LIMIT " + Main.instance.getConfig().getInt("TotalDePlayersNoRanking");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(s);
			p.sendMessage("             §4§lRanking             ");
			int i = 0;
			while(rs.next()){
				i++;
				p.sendMessage("§b§l" + i + "°  §e§l" + rs.getString("Nome") + " - §6§l" + rs.getInt("Coins") + " coins");
			}
		} catch(Exception e) {
			System.out.println("[Coins+] Erro ao gerar ranking: " + e.getMessage());
		}
	}
	
	public static void normalRanking(Player p){
		String[] Nome = new String[Bukkit.getOfflinePlayers().length];
		int[] Coins = new int[Bukkit.getOfflinePlayers().length];
		int n = 0;
		OfflinePlayer[] Offps = Bukkit.getServer().getOfflinePlayers();
		for(int i = 0; i < Offps.length; i++){
			Coins[n] = c.config.getInt("Coins." + Offps[i].getUniqueId());
			Nome[n] = Offps[i].getName();
			n++;
		}
		for(int i = 0; i < n - 1; i++){
			for(int j = i + 1; j < n; j++){
				int Coins_1 = Coins[i];
				int Coins_2 = Coins[j];
				if((Coins_1 < Coins_2) || (Coins_1 == Coins_2)){
					int h = Coins[j];
					Coins[j] = Coins[i];
					Coins[i] = h;
					String s = Nome[j];
					Nome[j] = Nome[i];
					Nome[i] = s;
				}
			}
		}
		p.sendMessage("             §4§lRanking             ");
		for(int i = 0; (i < n) && (i < Main.instance.getConfig().getInt("TotalDePlayersNoRanking")); i++){
			int coins = Coins[i];
			coins = Math.round(coins);
			p.sendMessage("§b§l" + Integer.valueOf(i + 1) + "°  §e§l" + Nome[i] + " - §6§l" + coins  + " coins");
		}
	}
}
