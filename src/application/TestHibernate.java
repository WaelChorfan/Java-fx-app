
package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import model.dao.*;

import model.dto.*;



public class TestHibernate {

	public static void main(String[] args) throws ParseException {
		
	CategorieDao dao =new CategorieDao();
	Categorie obj = new Categorie();
	obj.setCodeCat(828);
	obj.setNomCat("testcajt2");
	dao.create(obj);

	ProductDao dao1 =new ProductDao();
	Product obj1 = new Product();
	obj1.setCategorie(obj);
	obj1.setCoutLocation(500450);
	obj1.setQuantite(4);
	obj1.setMatricule("55TN9598");
	dao1.create(obj1);
		
		ClientDao dao2 =new ClientDao();
		Client obj2 = new Client();
		obj2.setCIN("50455040");
		obj2.setNom("name");
		obj2.setPrenom("last name");
		obj2.setTel("555 888 999 888");
		dao2.create(obj2);
	
	
		
		LocationDao daoloc = new LocationDao();
		Location loc = new Location();
		loc.setClient(obj2);
		loc.setProduct(obj1);
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String debut = "2013-05-20 17:07:21";
		  String fin = "2013-05-20 01:07:21";
           Date dt1 = sdf.parse(debut);  
           Date dt2 = sdf.parse(fin);  

	loc.setDebut(dt1);
        loc.setFin(dt2);

        daoloc.create(loc);
	}
}
		