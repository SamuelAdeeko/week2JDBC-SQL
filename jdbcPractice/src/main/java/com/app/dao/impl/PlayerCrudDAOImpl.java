package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.PlayerCrudDAO;
import com.app.dao.dbutil.PostgressqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Player;

public class PlayerCrudDAOImpl implements PlayerCrudDAO {
	
	@Override
	public int createPlayer(Player player) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgressqlConnection.getConnection()){
			String sql = "insert into revaturetest.player(id, name, team_id , age , gender, contact, dob) values(?,?,?,?,?,?,?)";    // SQL Injection
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, player.getId());
			preparedStatement.setString(2, player.getName());
			preparedStatement.setInt(3, player.getTeam_id());
			preparedStatement.setInt(4, player.getAge());
			preparedStatement.setString(5, player.getGender());
			preparedStatement.setLong(6, player.getContact());
			preparedStatement.setDate(7, new java.sql.Date(player.getDob().getTime())); // converting util.date to sql.date
			
			c=preparedStatement.executeUpdate(); 
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //Take off this line when app is live...this is for programmer to track it
			throw new BusinessException("Internal error occured contact SYSADMIN");
		} 
		
		return c;
	}

	@Override
	public void deletePlayer(int id) throws BusinessException {
		
		int c = 0;
		try(Connection connection = PostgressqlConnection.getConnection()){
			String sql = "DELETE FROM revaturetest.player WHERE id=?" ;  
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			c=preparedStatement.executeUpdate(); 
			
	} catch (ClassNotFoundException | SQLException e) {
		System.out.println(e); //Take off this line when app is live...this is for programmer to track it
		throw new BusinessException("Internal error occured contact SYSADMIN");
	} 
		
		
	}

	@Override
	public int updatePlayerContact(int id, long contact) throws BusinessException {
		
		int c = 0;
		try(Connection connection = PostgressqlConnection.getConnection()){
			String sql = "update revaturetest.player set contact = ? where id = ?" ;  
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, contact);
			preparedStatement.setInt(2, id);
			
			c=preparedStatement.executeUpdate(); 
			
	} catch (ClassNotFoundException | SQLException e) {
		System.out.println(e); //Take off this line when app is live...this is for programmer to track it
		throw new BusinessException("Internal error occured contact SYSADMIN");
	} 
		
		
		
		
		
		
		return c;
	}

	@Override
	public Player getPlayerById(int id) throws BusinessException {
		Player player = null ;
		try(Connection connection = PostgressqlConnection.getConnection()) {
			String sql = "select name, team_id, age, gender, contact, dob from revaturetest.player where id = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()) {
				player = new Player();
				player.setId(id);
				player.setName(resultset.getString("name"));
				player.setTeam_id(resultset.getInt("team_id"));
				player.setAge(resultset.getInt("age"));
				player.setGender(resultset.getString("gender"));
				player.setContact(resultset.getLong("contact"));
				player.setDob(resultset.getDate("dob"));
			}else {
				throw new BusinessException("No player for with Id: " + id); 
			}
	
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured contact system Admin");
		}
		
		return player;
	}
	


	@Override
	public List<Player> getAllPlayers() throws BusinessException {
			List<Player> playersList=new ArrayList<>();
			try (Connection connection = PostgressqlConnection.getConnection()) {
				String sql="select id,name, team_id, age, gender, contact, dob from revaturetest.player";
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					Player player =new Player();
					player.setId(resultSet.getInt("id"));
					player.setName(resultSet.getString("name"));
					player.setTeam_id(resultSet.getInt("team_id"));
					player.setAge(resultSet.getInt("age"));
					player.setGender(resultSet.getString("gender"));
					player.setContact(resultSet.getLong("contact"));
					player.setDob(resultSet.getDate("dob"));
					playersList.add(player);
				}
				if(playersList.size()==0)
				{
					throw new BusinessException("No Players in the DB so far");
				}
			}catch (ClassNotFoundException | SQLException e) {
				System.out.println(e); // Take off this line when app is live
				throw new BusinessException("Internal error occured contact SYSADMIN ");
			}
			return playersList;
		
	}

}
