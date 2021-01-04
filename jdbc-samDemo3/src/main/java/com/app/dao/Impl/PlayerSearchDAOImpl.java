package com.app.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.PlayerSearchDAO;
import com.app.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Player;

public class PlayerSearchDAOImpl implements PlayerSearchDAO {

	@Override
	public Player getPlayerById(int id) throws BusinessException {
		Player player = null;
		System.out.println("In DAO within getPlayerById() with id = " + id);
		try ( Connection connection = PostgresqlConnection.getConnection()) {
			String sql = "select name, team_id, age, gender, contact, dob from revaturetest.player where id= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			System.out.println("Query executed");
			if(resultset.next()) {
				System.out.println("If in DAO");
				player = new Player();
				player.setId(id);
				player.setName(resultset.getString("name"));
				player.setTeam_id(resultset.getInt("team_id"));
				player.setAge(resultset.getInt("age"));
				player.setGender(resultset.getString("gender"));
				player.setContact(resultset.getLong("contact"));
				player.setDob(resultset.getDate("dob"));
				
			} else {
				System.out.println("else in dao");
				throw new BusinessException("No player found with Id: " + id);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Exception in DAO");
			System.out.println(e); // Take off this line when app is live
			throw new BusinessException("Internal error occured contact SYS Admin");
		} 
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BusinessException {
		Player player = null;
		System.out.println("In DAO getPlayerByContact(): " + contact );
		try (Connection connection = PostgresqlConnection.getConnection()) {
			String sql = "select id, name, team_id, age, gender, dob from revaturetest.player where contact = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, contact);
			ResultSet resultset = preparedStatement.executeQuery();
			System.out.println("Query Executed");
			if(resultset.next()) {
				System.out.println("If in DAO");
				player = new Player();
				player.setContact(contact);
				player.setId(resultset.getInt("id"));
				player.setName(resultset.getString("name"));
				player.setTeam_id(resultset.getInt("team_id"));
				player.setAge(resultset.getInt("age"));
				player.setGender(resultset.getString("gender"));
				player.setDob(resultset.getDate("dob"));
			} else {
				System.out.println("else in DAO");
				throw new BusinessException("No Player found with the contact number: " + contact);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Exception in DAO");
			System.out.println(e);  // TAKE THIS OFF WHEN LIVE
			throw new BusinessException("Internal error occured, contact SYS Admin");
		} 
	
		return player;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = PostgresqlConnection.getConnection()) {
			String sql = "select id, name, team_id, age, gender, contact, dob from revaturetest.player";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultset = preparedStatement.executeQuery();
			System.out.println("Query executed");
			while(resultset.next()) {
				System.out.println("In DAO");
				Player player = new Player();
				player.setId(resultset.getInt("id"));
				player.setName(resultset.getString("name"));
				player.setTeam_id(resultset.getInt("team_id"));
				player.setAge(resultset.getInt("age"));
				player.setGender(resultset.getString("gender"));
				player.setContact(resultset.getLong("contact"));
				player.setDob(resultset.getDate("dob"));
				playerList.add(player);
			}
			if(playerList.size() == 0 ) {
				throw new BusinessException("No player in the DB so far");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("In DAO exception");
			System.out.println(e);  // take off when live
			throw new BusinessException("Internal error occured contact SYS Admin");
		} 
		
		return playerList;
	}

	@Override
	public List<Player> getPlayersByAge(int age) throws BusinessException {
		List<Player> listOfPlayersByAge = new ArrayList<>();
		
		try (Connection connection = PostgresqlConnection.getConnection()) {
			String sql = "select id, name, team_id, gender, contact, dob from revaturetest.player where age = ?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, age);
			ResultSet resultset = prepareStatement.executeQuery();
			System.out.println("Query executed");
			while(resultset.next()) {
				System.out.println("In DAO");
				Player player = new Player();
				player.setAge(age);
				player.setId(resultset.getInt("id"));
				player.setName(resultset.getString("name"));
				player.setTeam_id(resultset.getInt("team_id"));
				player.setGender(resultset.getString("gender"));
				player.setContact(resultset.getLong("contact"));
				player.setDob(resultset.getDate("dob"));
				listOfPlayersByAge.add(player);
			} if(listOfPlayersByAge.size() == 0) {
				System.out.println("else in DAO");
				throw new BusinessException("No player found with age: " + age);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("In DAO Exception");
			System.out.println(e); // take out when live
			throw new BusinessException("Internal error occurred contact SYS Admin");
			
		}
		

		return listOfPlayersByAge;
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BusinessException {
		
		List<Player> playersByGenderList = new ArrayList<>();
		try (Connection connection = PostgresqlConnection.getConnection()) {
			String sql = "select id, name, team_id, age, contact, dob from revaturetest.player where gender = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, gender);
			ResultSet resultset = preparedStatement.executeQuery();
			System.out.println("Query executed");
			while(resultset.next()) {
				System.out.println("in DAO");
				Player player = new Player();
				player.setGender(gender);
				player.setId(resultset.getInt("id"));
				player.setName(resultset.getString("name"));
				player.setTeam_id(resultset.getInt("team_id"));
				player.setAge(resultset.getInt("age"));
				player.setContact(resultset.getLong("contact"));
				player.setDob(resultset.getDate("dob"));
				playersByGenderList.add(player);
			} if(playersByGenderList.size() == 0) {
				throw new BusinessException("No player found with gender: " + gender);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("In DAO exception");
			System.out.println(e);
			throw new BusinessException("Internal error occurred contact SYS Admin");
		}
		
		return playersByGenderList;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamname) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getPlayersByDob(String dob) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
