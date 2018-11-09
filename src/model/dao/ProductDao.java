package model.dao;

import java.util.ArrayList;
import org.hibernate.Query;
import model.dto.Product;


public class ProductDao extends Dao<Product> {

	@Override
	public void create(Product prod) {
		try{
			openSession();
			session.save(prod);
			closeSession();
		}catch (Exception e) {
			e.printStackTrace();

		}
		
	}

	@Override
	public Product retrieve(int id) {
		Product prod = new Product();
		try{
			openSession();
			prod = (Product) session.get(Product.class, id);
			closeSession();
		}catch (Exception e) {
			e.printStackTrace();
		}

				return prod;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> retrieveAll() {
		openSession();
		Query query = session.createQuery("from Product");
		ArrayList<Product> prods = (ArrayList<Product>) query.list();
		closeSession();

		return prods;
	}

	@Override
	public void update(Product prod) {
		try{
			openSession();
			session.update(prod);
			closeSession();
		}catch (Exception e) {
			e.printStackTrace();

		}			
	}

	@Override
	public void delete(int id) {
		try{
			Product prod = retrieve(id);
			if(prod!=null){
				openSession();
				session.delete(prod);
				closeSession();
			}
		}catch (Exception e) {
			e.printStackTrace();

		}		
	}

}
