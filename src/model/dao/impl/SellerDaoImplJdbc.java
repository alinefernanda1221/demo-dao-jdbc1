package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

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
					Seller seller = instantiateSeller(resultSet);	
					seller.setDepartment(department);
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
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department(resultSet.getInt("ID_DEPARTMENT"), resultSet.getString("NAME"));
		return department;
	}

	private Seller instantiateSeller(ResultSet resultSet) throws SQLException {
		Seller seller = new Seller(	resultSet.getInt("ID"),
				resultSet.getString("NAME"), 
				resultSet.getString("EMAIL"), 
				resultSet.getDate("BIRTHDATE"), 
				resultSet.getDouble("BASESALARY"));
		return seller;
	}
}
