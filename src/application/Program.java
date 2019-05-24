package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("Test #1 - SellerByID");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		System.out.println("--------------------\n");

		System.out.println("Test #2 - SellerByDepartment");
		List<Seller> sellers = new ArrayList<Seller>();
		sellers = sellerDao.findByDepartment(new Department(2, "XPTO"));
		
		for(Seller sel : sellers) {
			System.out.println(sel);
		}
		System.out.println("--------------------\n");
	}

}
