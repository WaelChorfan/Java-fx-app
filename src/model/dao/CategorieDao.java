package model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import org.hibernate.Query;
import model.dto.Categorie;

public class CategorieDao extends Dao<Categorie> {

	@Override
	public void create(Categorie cat) {

		try{
			openSession();
			session.save(cat);
			closeSession();
		}catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public Categorie retrieve(int id) {
		Categorie cat = new Categorie();
		try{
			openSession();
			cat = (Categorie) session.get(Categorie.class, id);
			closeSession();
		}catch (Exception e) {
			e.printStackTrace();
		}

				return cat;
	}
	
	public int retrieveCatByName(String name){
    	CategorieDao catDao = new CategorieDao();
    	ArrayList<Categorie> cats = (ArrayList<Categorie>) catDao.retrieveAll();
    	HashMap<String,Integer> mapCats = new HashMap<>();
    	cats.forEach(c->{
    		mapCats.put(c.getNomCat(), c.getCodeCat());
    	});
    	
    	return mapCats.get(name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Categorie> retrieveAll() {
		openSession();
		Query query = session.createQuery("from Categorie");
		ArrayList<Categorie> cats = (ArrayList<Categorie>) query.list();
		closeSession();

		return cats;
	}

	@Override
	public void update(Categorie cat) {
		try{
			if(retrieve(cat.getCodeCat())!=null){
				openSession();
				session.update(cat);
				closeSession();
			}else{
				openSession();
				create(cat);
				closeSession();
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();

		}	
		
	}

	@Override
	public void delete(int id) {
		try{
			Categorie cat = retrieve(id);
			if(cat!=null){
				openSession();
				session.delete(cat);
				closeSession();				
			}

		}catch (Exception e) {
			e.printStackTrace();

		}		
	}

}
