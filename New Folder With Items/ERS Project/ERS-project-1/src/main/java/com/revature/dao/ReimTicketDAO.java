package com.revature.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.ReimTicket;
import com.revature.utility.JDBCUtility;

public class ReimTicketDAO {

	//private String reimInfo;

	public List<ReimTicket> getAllReimTickets() throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			List<ReimTicket> reimtickets = new ArrayList<>();
			
			String sql = "SELECT reimticket_id, reimticket_type, reimamount, resolver_id, reimauthor_id, reiminfo FROM reimtickets";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int reimauthorId = rs.getInt("reimticket_id");
				String reimticketType = rs.getString("reimticket_type");
				int reimamount = rs.getInt("reimamount");
				int resolverId = rs.getInt("resolver_id");
				String reimInfo = rs.getString("reiminfo");
				int reimticketId = rs.getInt("reimauthor_id");
				
				ReimTicket reimticket = new ReimTicket(reimticketId, reimticketType, reimamount, resolverId, reimInfo, reimauthorId);
				
				reimtickets.add(reimticket);
			}
			
			return reimtickets;
		}
	}
	
	public List<ReimTicket> getAllReimTicketsByEmployee(int reimauthorId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			List<ReimTicket> reimtickets = new ArrayList<>();
			
			String sql = "SELECT reimticket_id, reimticket_type, reimamount, resolver_id, reimauthor_id, reiminfo FROM reimtickets WHERE reimauthor_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reimauthorId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//int reimauthorId = rs.getInt("reimauthor_id");
				String reimticketType = rs.getString("reimticket_type");
				int reimamount = rs.getInt("reimamount");
				int resolverId = rs.getInt("resolver_id");
				String reimInfo = rs.getString("reiminfo"); //reim descip added here
				int reimticketId = rs.getInt("reimticket_id");
				
				
				
				ReimTicket reimticket = new ReimTicket(reimauthorId, reimticketType, reimamount, resolverId, reimInfo, reimticketId); //reim descrip added here
				
				reimtickets.add(reimticket);
			}
			
			return reimtickets;
		}
	}

	public ReimTicket getReimTicketById(int reimticketId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			
			String sql = "SELECT reimticket_id, reimticket_type, reiminfo, reimamount, resolver_id, reimauthor_id FROM reimtickets WHERE reimticket_id= ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reimticketId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("reimticket_id");
				String reimticketType = rs.getString("reimticket_type");
				int reimamount = rs.getInt("reimamount");
				int resolverId = rs.getInt("resolver_id");
				String reimInfo = rs.getString("reiminfo");
				int reimauthorId = rs.getInt("reimauthor_id");
				
				
				return new ReimTicket(id, reimticketType, reimamount, resolverId,reimInfo, reimauthorId);
			} else {
				return null;
			}
			
		}
	}

	public void changeReimAmount( String reimticketId, int reimamount, String reimInfo, int resolverId ) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE reimtickets "
					+ "SET "
					+ "reimamount = ?, "
					+ "resolver_id = ?, "
					+ "reiminfo = ? "
					+ "WHERE reimticket_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, reimamount);
			pstmt.setInt(2, resolverId);
			pstmt.setString(3, reimInfo); //may need to be changed or deleted. 
			pstmt.setInt(4, Integer.parseInt(reimticketId));
		
			
			int updatedCount = pstmt.executeUpdate();
			
			if (updatedCount != 1) {
				throw new SQLException("Something bad occurred when trying to update reimamount");
			}
		}
		
	}
	
	public ReimTicket addReimTicket(String reimticketType, int reimauthorId, String reimInfo, InputStream image) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			con.setAutoCommit(false); // Turn off autocommit
			
			String sql = "INSERT INTO reimtickets (reimticket_type, reimauthor_id, reiminfo, reimticket_image)"
					+ " VALUES (?,?,?,?);";
			
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, reimticketType);
			pstmt.setInt(2, reimauthorId);
			pstmt.setString(3, reimInfo); //reiminfo added
			pstmt.setBinaryStream(4, image);
			
			
			
			int numberOfInsertedRecords = pstmt.executeUpdate();
			
			if (numberOfInsertedRecords != 1) {
				throw new SQLException("Issue occurred when adding reimticket");
			}
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			rs.next();
			int generatedId = rs.getInt(1);
			
			con.commit(); // COMMIT
			
			return new ReimTicket(generatedId, reimticketType, 0, 0, reimInfo, reimauthorId); //reiminfo added
		}
	}

	public InputStream getImageFromReimTicketById(int reimticketId) throws SQLException {
		try(Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT reimticket_image FROM reimtickets WHERE reimticket_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, reimticketId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				InputStream image = rs.getBinaryStream("reimticket_image");
				
				return image;
			}
			
			return null;
		}
	}
	
}