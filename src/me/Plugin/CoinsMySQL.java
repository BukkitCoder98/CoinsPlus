package me.Plugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class CoinsMySQL {

	private static Connection con = new SQLite().getConnection();
	
	public static void gerarTabelas(){
		try {
			String query = "CREATE TABLE IF NOT EXISTS Coins (UUID varchar(255), Nome varchar(32), Coins int(32));";
		    Connection con = new SQLite().getConnection();
		    Statement statement = con.createStatement();
		    statement.executeUpdate(query);
		    statement.close();
		} catch (Exception e) {
			System.out.println("[Coins+] MySQL: Erro ao iniciar tabelas!");
		}
	}
	
	public static void criarPlayer(Player p){
		try {
			String query = "INSERT INTO Coins (UUID, Nome, Coins) VALUES ('" + p.getUniqueId() + "' , '" + p.getName() + "' , '0')";
			Statement s = con.createStatement();
			s.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("[Coins+] MySQL: Erro ao criar jogador: " + e.getMessage());
		}
	}
	
	public static void criarPlayer(OfflinePlayer p){
		try {
			String query = "INSERT INTO Coins (UUID, Nome, Coins) VALUES ('" + p.getUniqueId() + "' , '" + p.getName() + "' , '0')";
			Statement s = con.createStatement();
			s.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("[Coins+] MySQL: Erro ao criar jogador: " + e.getMessage());
		}
	}
	
	public static boolean playerExiste(Player p){
		try {
			String query = "SELECT * FROM Coins WHERE UUID='" + p.getUniqueId() + "'";
		    Statement s = con.createStatement();
		    ResultSet rs = s.executeQuery(query);
		    if(rs.next()){
		    	return rs.getString("UUID") != null;
		    }
		    return false;
		} catch (Exception e) {
			throw new Error("[Coins+] MySQL: Erro ao verificar o jogador: " + e.getLocalizedMessage());
		}
	}
	
	public static boolean playerExiste(OfflinePlayer p){
		try {
			String query = "SELECT * FROM Coins WHERE UUID='" + p.getUniqueId() + "'";
		    Statement s = con.createStatement();
		    ResultSet rs = s.executeQuery(query);
		    if(rs.next()){
		    	return rs.getString("UUID") != null;
		    }
		    return false;
		} catch (Exception e) {
			throw new Error("[Coins+] MySQL: Erro ao verificar o jogador: " + e.getLocalizedMessage());
		}
	}
	
	public static Integer getCoins(Player p){
		Integer i = 0;
		if(playerExiste(p)){
			try {
				String query = "SELECT * FROM Coins WHERE UUID='" + p.getUniqueId() + "'";
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query);
				if((!rs.next()) || (Integer.valueOf(rs.getInt("Coins")) == null));
				i = rs.getInt("Coins");
			} catch (Exception e) {
				System.out.println("[Coins+] MySQL: Erro ao pegar coins: " + e.getMessage());
			}
		} else {
			criarPlayer(p);
			getCoins(p);
		}
		return i;
	}
	
	public static Integer getCoins(OfflinePlayer p){
		Integer i = 0;
		if(playerExiste(p)){
			try {
				String query = "SELECT * FROM Coins WHERE UUID='" + p.getUniqueId() + "'";
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query);
				if((!rs.next()) || (Integer.valueOf(rs.getInt("Coins")) == null));
				i = rs.getInt("Coins");
			} catch (Exception e) {
				System.out.println("[Coins+] MySQL: Erro ao pegar coins: " + e.getMessage());
			}
		} else {
			criarPlayer(p);
			getCoins(p);
		}
		return i;
	}
	
	public static void setCoins(Player p, Integer coins){
		if(playerExiste(p)){
			try {
				String query = "UPDATE Coins SET Coins ='" + coins + "' WHERE UUID='" + p.getUniqueId() + "'";
		        Statement statement = con.createStatement();
		        statement.executeUpdate(query);
			} catch (Exception e) {
				System.out.println("[Coins+] MySQL: Erro ao setar coins: " + e.getMessage());
			}
		}
	}
	
	public static void setCoins(OfflinePlayer p, Integer coins){
		if(playerExiste(p)){
			try {
				String query = "UPDATE Coins SET Coins ='" + coins + "' WHERE UUID='" + p.getUniqueId() + "'";
		        Statement statement = con.createStatement();
		        statement.executeUpdate(query);
			} catch (Exception e) {
				System.out.println("[Coins+] MySQL: Erro ao setar coins: " + e.getMessage());
			}
		}
	}
	
	public static void addCoins(Player p, Integer coins){
		if(playerExiste(p)){
			setCoins(p, Integer.valueOf(getCoins(p).intValue() + coins.intValue()));
		} else {
			criarPlayer(p);
			addCoins(p, coins);
		}
	}
	
	public static void addCoins(OfflinePlayer p, Integer coins){
		if(playerExiste(p)){
			setCoins(p, Integer.valueOf(getCoins(p).intValue() + coins.intValue()));
		} else {
			criarPlayer(p);
			addCoins(p, coins);
		}
	}
	
	public static void removeCoins(Player p, Integer coins){
		if(playerExiste(p)){
			setCoins(p, Integer.valueOf(getCoins(p).intValue() - coins.intValue()));
		} else {
			criarPlayer(p);
			removeCoins(p, coins);
		}
	}
	
	public static void removeCoins(OfflinePlayer p, Integer coins){
		if(playerExiste(p)){
			setCoins(p, Integer.valueOf(getCoins(p).intValue() - coins.intValue()));
		} else {
			criarPlayer(p);
			removeCoins(p, coins);
		}
	}
}
