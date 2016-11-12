package me.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLite {
	
	public Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://" + Main.instance.getConfig().getString("MySQL.Host") + ":" + Main.instance.getConfig().getString("MySQL.Porta") + "/" + Main.instance.getConfig().getString("MySQL.Database") + "?user=" + Main.instance.getConfig().getString("MySQL.Usuario") + "&password=" + Main.instance.getConfig().getString("MySQL.Senha"));
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}
	}
}
