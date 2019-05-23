package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
		
		Department obj = new Department(1, "teste");
		System.out.println(obj);
		
		Seller sel = new Seller(1, "John", "john@gmail.com", new Date(), 5000.0);
		System.out.println(sel);
		
		//Dependecy injection
		SellerDao sellerDao = DaoFactory.createSellerDao();
	}

}
