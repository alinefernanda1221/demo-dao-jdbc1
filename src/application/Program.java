package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.println("Test #3 - FindAll");
		
		System.out.println("--------------------\n");		

//		System.out.println("Test #4 - InsertSeller");
//		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4600.00);
//		newSeller.setDepartment(new Department(2, null));
//		sellerDao.insert(newSeller);
//		System.out.println("New id: " + newSeller.getId());
//		
//		System.out.println("--------------------\n");		

//		System.out.println("Test #5 - UpdateSeller");
//		seller = sellerDao.findById(1);
//		seller.setName("Aline Carvalho");
//		sellerDao.update(seller);
//		System.out.println("Update completed");
//		System.out.println("--------------------\n");		

		System.out.println("Test #6 - DeleteSeller");
		System.out.println("Insert code to delete: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		sc.close();
		System.out.println("Delete completed");
		System.out.println("--------------------\n");		
	}

}
