package com.app.main;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.app.exception.BusinessException;
import com.app.model.Player;
import com.app.service.PlayerSearchService;
import com.app.service.impl.PlayerSearchServiceImpl;

public class PlayerSearchMain {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to PlayerSearch App V1.0");
		System.out.println("------------------------");
		int choice = 0;
		PlayerSearchService  playerSearchService = new PlayerSearchServiceImpl();
		do {
			System.out.println("PLAYER SEARCH MENU");
			System.out.println("===========================");
			System.out.println("1)By Id");
			System.out.println("2)By Contact Number");
			System.out.println("3)By Age");
			System.out.println("4)By Gender");
			System.out.println("5)By Team_id");
			System.out.println("6)By Player Name");
			System.out.println("7)By Dob");
			System.out.println("8)Show All Players");
			System.out.println("9)EXIT");
			System.out.println("Please enter appropriate choice between 1-9");
		try {
			choice = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			
		}
			switch (choice) {
			case 1: 
				System.out.println("case-1 was selected");
				System.out.println("Enter Player Id to get Player Details... ");
				try {
					int id = Integer.parseInt(sc.nextLine());
					// code to service
					Player player = playerSearchService.getPlayerById(id);
					if (player != null) {
						System.out.println("Player found with id : " + id + " details of the player is : ");
						System.out.println(player);
					}
				} catch (NumberFormatException e) {
					System.out.println("Player Id cannot be special characters or symbols or white spaces it is numeric");
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("Case-2 was selected");
				System.out.println("Enter the player contact number...");
			
				try {
					long contact = Long.parseLong(sc.nextLine());
					// code to service
					Player player = playerSearchService.getPlayerByContact(contact);
					if(player != null) {
						System.out.println("Player found with contact number: " + contact + " details of player is: ");
						System.out.println(player);
					} 
				}catch (NumberFormatException e) {
					System.out.println("Player Contact cannot be special characters or symbols or white spaces it is numeric");
				
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 3:
				System.out.println("Case-3 was selected");
				System.out.println("Enter the player age...");
				
				try {
					int age = Integer.parseInt(sc.nextLine());
					// code to service
					
					List<Player> playerByAge = playerSearchService.getPlayersByAge(age);
					if(playerByAge != null && playerByAge.size() > 0 ) {
						System.out.println("Total number of players in Database by age: " + playerByAge.size());
						System.out.println("Printing all players in the database found...");
						for(Player p : playerByAge ) {
							System.out.println(p);
						}
						}
					
				} catch (BusinessException | NumberFormatException e) {
					System.out.println("Player Id cannot be special characters or symbols or white spaces it is numeric");
					System.out.println(e);
				}
				

				break;
			case 4:
				System.out.println("Case-4 was selected");
				System.out.println("Enter the player gender...");
			
				try {
					String gender = sc.nextLine();
					List<Player> listByGender = playerSearchService.getPlayersByGender(gender);
					if(listByGender != null && listByGender.size() > 0) {
						System.out.println("Total number of players in Database by gender: " + listByGender.size());
						System.out.println("Printing all players in the database found...");
						for(Player p : listByGender) {
							System.out.println(p);
						}
					}
				} catch (BusinessException e1) {
	
					e1.printStackTrace();
				}

				break;
			case 5:
				System.out.println("Case-5 was selected");
				System.out.println("Enter the player team_id...");
				try {
					int team_id = Integer.parseInt(sc.nextLine());
					List<Player> listByTeamId = playerSearchService.getPlayersByTeam_id(team_id);
					if(listByTeamId != null && listByTeamId.size() > 0) {
						System.out.println("Total number of players in Database by team_id: " + listByTeamId.size());
						System.out.println("Printing all players in the database found...");
						for(Player p : listByTeamId) {
							System.out.println(p);
						}
					}
				} catch (BusinessException e1) {
	
					e1.printStackTrace();
				}

				break;
			case 6:
				System.out.println("Case-6 was selected");
				System.out.println("Enter the player name...");
				try {
					String name = sc.nextLine();
					List<Player> listByName = playerSearchService.getPlayersByName(name);
					if(listByName != null && listByName.size() > 0) {
						System.out.println("Total number of players in Database by name: " + listByName.size());
						System.out.println("Printing all players in the database found...");
						for(Player p : listByName) {
							System.out.println(p);
						}
					}
				} catch (BusinessException e1) {
	
					e1.printStackTrace();
				}


				break;
			case 7:
				System.out.println("Case-7 was selected");
				System.out.println("Enter the player Dob...");
//				try {
//					DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//					Date newDob = formatter.parse(dob);
		
//					Date dob = sc.nextLine();
//					List<Player> listByDob = playerSearchService.getPlayersByName(dob);
//					if(listByDob != null && listByDob.size() > 0) {
//						System.out.println("Total number of players in Database by name: " + listByDob.size());
//						System.out.println("Printing all players in the database found...");
//						for(Player p : listByDob) {
//							System.out.println(p);
//						}
//					}
//				} catch (BusinessException e1) {
//	
//					e1.printStackTrace();
//				}
//
//				break;
			case 8:
				System.out.println("Retrieving all the players from backend");
				try {
					List<Player> listOfAllPlayers = playerSearchService.getAllPlayers();
					if(listOfAllPlayers != null && listOfAllPlayers.size() > 0) {
						System.out.println("Total number of players in Database is: " + listOfAllPlayers.size());
						System.out.println("Printing all players in the database found...");
						for(Player p : listOfAllPlayers) {
							System.out.println(p);
						}	
					}
				} catch (BusinessException e) {
					System.out.println(e);
				}

				break;
			case 9:
				System.out.println("Thanks for your interest this option is still under construction");

				break;
			default:
				System.out.println("Invalid menu option...Kindly retry");

				break;
			}
		} while ( choice != 9);
	

}
}
