package model.dao;
/**
 * Create dao's implementations
 */
import model.dao.impl.SellerDaoImplJdbc;

public class DaoFactory {
	
	//Return implementation
	public static SellerDao createSellerDao() {
		return new SellerDaoImplJdbc();
	}
}
