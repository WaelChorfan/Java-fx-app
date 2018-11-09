package model.dto;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Categorie {

	@Id
	private int codeCat;
	private String nomCat;
	@OneToMany(targetEntity=Product.class, mappedBy="categorie", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Product> products;
	
	public Categorie(){
		this(0,null);
	}
	
	public Categorie(int codeCat, String nomCat){
		products = new HashSet<Product>();
		setCodeCat(codeCat);
		setNomCat(nomCat);
	}
	
	
	
	@Override
	public String toString() {
		return getNomCat();
	}

	
	public int getCodeCat() {
		return codeCat;
	}

	public void setCodeCat(int codeCat) {
		
		if(codeCat>0)
		this.codeCat = codeCat;
	}

	public String getNomCat() {
		return nomCat;
	}

	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}

	

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}

