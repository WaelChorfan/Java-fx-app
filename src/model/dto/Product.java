package model.dto;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;






@Entity

public class Product {
	

	@Id
	private int idVoiture;
	private String matricule;
	private double coutLocation;
	private int quantite;
	
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	private Location location;
	
	@ManyToOne
	@JoinColumn(name="CodeCat")
	private Categorie categorie;
	
	
	public Product(){
		this(0,null,0,0,0);
	}
	
	public Product(int idVoiture, String matricule ,double coutLocation, 
			 int quantite, int codeCat) {
		
		setIdVoiture(idVoiture);
		setMatricule(matricule);
		setCoutLocation(coutLocation);
		
		Categorie cat = new Categorie();
		cat.setCodeCat(codeCat);
	}

	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(int idVoiture) {
		this.idVoiture = idVoiture;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public double getCoutLocation() {
		return coutLocation;
	}

	public void setCoutLocation(double coutLocation) {
		this.coutLocation = coutLocation;
	}

	

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Voiture [idVoiture=" + idVoiture + ", matricule=" + matricule + ", coutLocation=" + coutLocation
				+ ", categorie=" + categorie + "]";
	}





	
}
