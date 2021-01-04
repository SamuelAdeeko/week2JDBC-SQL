package com.app.service.impl;

import java.sql.Date;
import java.util.List;

import com.app.dao.PlayerSearchDAO;
import com.app.dao.Impl.PlayerSearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Player;
import com.app.service.PlayerSearchService;

public class PlayerSearchServiceImpl implements PlayerSearchService {
	
	private PlayerSearchDAO  playerSearchDao = new PlayerSearchDAOImpl();

	@Override
	public Player getPlayerById(int id) throws BusinessException {
		Player player = null;
		System.out.println("PlayerService within getPlayerById() with id: "+ id);
		if(id > 1 && id < 1000) {
			//code here for DAO
			System.out.println("in if before DAO call");
			player = playerSearchDao.getPlayerById(id);
		} else {
			System.out.println("In else");
			throw new BusinessException("Entered Player Id \" + id + \" is INVALID......"  + id);
		}
		
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BusinessException {
		Player player = null;
		String number = contact + "" ;
		System.out.println("PlayerService within getPlayerByContact() with id: "+ contact);
		if(number != null && number.matches("\\+1 [0-9]{10}") ) {
			//code here for DAO
			System.out.println("in if before DAO call");
			Long newNumber = Long.parseLong(number);
			player = playerSearchDao.getPlayerByContact(newNumber);
		} else {
			System.out.println("In else");
			throw new BusinessException("Entered Player Id \" + id + \" is INVALID......"  + contact);
		}
		return player;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException {
		List<Player> listOfAllPlayers = null;
		listOfAllPlayers = playerSearchDao.getAllPlayers();
		return listOfAllPlayers;
	}

	@Override
	public List<Player> getPlayersByAge(int age) throws BusinessException {
		List<Player> agePlayersList = null;
		if (age > 18 && age < 60) {
			// code here for DAO
			agePlayersList = playerSearchDao.getPlayersByAge(age);
		} else {
			throw new BusinessException("Entered Player Age " + age + " is INVALID......");
		}
		return agePlayersList;
		
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BusinessException {
		List<Player> genderPlayersList = null;
		genderPlayersList = playerSearchDao.getPlayersByGender(gender);
		return genderPlayersList;
	}

	@Override
	public List<Player> getPlayersByTeam_id(int team_id) throws BusinessException {
		List<Player> playersByTeamId = null;
		if (team_id > 100 && team_id < 1000) {
			// code here for DAO
			System.out.println("in if before DAO call");
			playersByTeamId = playerSearchDao.getPlayersByTeam_id(team_id);
		} else {
			System.out.println("In else");
			throw new BusinessException("Entered Team Id " + team_id + " is INVALID......");
		}
		return playersByTeamId;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException {
		List<Player> playerListByName = null;
		if(name.matches("^[a-zA-Z\\s]*$")) {   
			System.out.println("In if DAO");
		playerListByName = playerSearchDao.getPlayersByName(name);
	} else {
		System.out.println("In else");
		throw new BusinessException("Entered name " + name + " is INVALID......");
	}
		return playerListByName;
	}

	

	@Override
	public List<Player> getPlayersByDob(Date dob) throws BusinessException {
		List<Player> playersByDobList = null;
		String sDate = dob + "" ;
		if(sDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			System.out.println("In if DAO");
			playersByDobList = playerSearchDao.getPlayersByDob(dob);
		} else {
			System.out.println("In else");
			throw new BusinessException("Entered DOB " + dob + " is INVALID......");
		}
		return playersByDobList;
	}
	
	/*
	 * 1

[A-Z] match a single capital letter (first letter of name)

[a-z]* match any number of small letters (other letters or name)

(\s) match 1 whitespace character (the space between names)

+ one or more of the previous expression (to match more than one name)
	 * 
	 * 
	 */

}
