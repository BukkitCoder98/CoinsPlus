# Como utilizar ele de API em seus plugins:
  
  Aqui vai um exemplo de como utilizar, vou mostrar exemplos de adição de coins, retirada de coins e checar os coins de cada jogador
  
  Adição:
           
           public static CoinsFile c = CoinsFile.getFile();
  
			if(CoinsAPI.instance.getConfig().getBoolean("MySQL.Ativar")){
				CoinsMySQL.addCoins(p, coins);
			} else {
				c.addCoins(p, coins);
			}
  
  Retirada:
  
            public static CoinsFile c = CoinsFile.getFile();
  
			if(CoinsAPI.instance.getConfig().getBoolean("MySQL.Ativar")){
				CoinsMySQL.removeCoins(p, coins);
			} else {
				c.removeCoins(p, coins);
			}
   
  Checar:
  
            public static CoinsFile c = CoinsFile.getFile();
  
			if(CoinsAPI.instance.getConfig().getBoolean("MySQL.Ativar")){
				if(CoinsMySQL.getCoins(p) < 1000){
                p.sendMessage("§cVoce não pode comprar isto!");
                return;
            }
            CoinsMySQL.removeCoins(p, 1000);
            p.sendMessage("§9Compra efetuada com sucesso!");
			} else {
				if(c.getCoins(p) < 1000){
                p.sendMessage("§cVoce não pode comprar isto!");
                return;
            }
            c.removeCoins(p, 1000);
            p.sendMessage("§9Compra efetuada com sucesso!");
		}
            
            
        Ou
        
          if(CoinsAPI.instance.getConfig().getBoolean("MySQL.Ativar")){
				p.sendMessage(CoinsAPI.PREFIX + "§9§lVoce possui §6§l " + CoinsMySQL.getCoins(p) + " coins!");
			} else {
				p.sendMessage(CoinsAPI.PREFIX + "§9§lVoce possui §6§l" + c.getCoins(p) + " coins!");
			}
            
   
   É sempre importante deixar desta forma, pois foi a única testada. Você pode tentar arriscar de outra forma mas essa é a única que eu posso falar que funciona 100%
