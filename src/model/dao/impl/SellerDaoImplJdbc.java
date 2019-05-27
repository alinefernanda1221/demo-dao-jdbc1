package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoImplJdbc implements SellerDao {

	private Connection connection;

	public SellerDaoImplJdbc(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller seller) {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(
					"INSERT INTO seller " + 
					"(Name, Email, BirthDate, BaseSalary, ID_Department) " + 
					"VALUES " + 
					"(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, seller.getName());
			pstmt.setString(2, seller.getEmail());
			pstmt.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			pstmt.setDouble(4, seller.getBaseSalary());
			pstmt.setInt(5, seller.getDepartment().getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet resultSet = pstmt.getGeneratedKeys();
				if(resultSet.next()) {
					int id = resultSet.getInt(1);
					seller.setId(id);
					DB.closeResultSet(resultSet);
				}
			}else {
				throw new DbException("No row(s) affected");
			}
		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			//Close Resources
			DB.closePreparedStatement(pstmt);
		}
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
				pstmt = connection.prepareStatement(
						"SELECT seller.*,department.Name as DepName "+
						"FROM seller INNER JOIN department "+
						"ON seller.ID_DEPARTMENT = department.ID_DEPARTMENT "+
						"WHERE seller.ID = ?");
				pstmt.setInt(1, id);
				resultSet = pstmt.executeQuery();
				if(resultSet.next()) {
					Department department = instantiateDepartment(resultSet);	
					Seller seller = instantiateSeller(resultSet , department);	
					return seller;
				}
				return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			//Close Ressources
			DB.closePreparedStatement(pstmt);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.ID_DEPARTMENT = department.ID_DEPARTMENT " + 
					"WHERE seller.ID_DEPARTMENT = ? ");
			pstmt.setInt(1, department.getId());
			resultSet = pstmt.executeQuery();
			List<Seller> sellers = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>();
			while(resultSet.next()) {
				Department dep = map.get(resultSet.getInt("ID_DEPARTMENT"));
				if(dep == null) {
					dep = instantiateDepartment(resultSet);
					map.put(resultSet.getInt("ID_DEPARTMENT"), dep);
				}
				Seller seller = instantiateSeller(resultSet, dep);	
				sellers.add(seller);
			}
			return sellers;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			//Close Ressources
			DB.closePreparedStatement(pstmt);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department(resultSet.getInt("ID_DEPARTMENT"), resultSet.getString("NAME"));
		return department;
	}

	private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
		Seller seller = new Seller(	resultSet.getInt("ID"),
				resultSet.getString("NAME"), 
				resultSet.getString("EMAIL"), 
				resultSet.getDate("BIRTHDATE"), 
				resultSet.getDouble("BASESALARY"));
		seller.setDepartment(department);
		return seller;
	}

}
