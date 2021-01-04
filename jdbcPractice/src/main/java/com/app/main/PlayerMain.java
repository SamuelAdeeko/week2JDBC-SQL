package com.app.main;

import java.util.List;

import com.app.dao.PlayerCrudDAO;
import com.app.dao.impl.PlayerCrudDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Player;

public class PlayerMain {

	public static void main(String[] args) {
	PlayerCrudDAO dao = new PlayerCrudDAOImpl();
		
		Player p = new Player(200, "Banks" , 101, 46, "m", 22533423l, new Date() );
		
		try {
			if(dao.createPlayer(p)!=0) {
				System.out.println("Player created succcessfully");
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
	
//		try {
//			dao.deletePlayer(204);
//			System.out.println("Player deleted succcessfully");
//		} catch (BusinessException e) {
//			
//			e.printStackTrace();
//		}
		
		
//		try {
//			dao.updatePlayerContact(2, 60856540l);
//			System.out.println("Player contact updated successfully");
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//		}
		
//		try {
//			Player player = dao.getPlayerById(2);
//			if(player!= null) {
//				System.out.println("Details of player with Id : " + player.getId());
//				System.out.println(player);
//			}
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//		}
		
//		try {
//			List<Player> playerList=dao.getAllPlayers();
//			if(playerList!=null && playerList.size()!=0) {
//				System.out.println("Found "+ playerList.size()+" no of players in Database....Printing them all");
//				for(Player p:playerList) {
//					System.out.println(p);
//				}
//			}
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//		}
//		
	}




}
