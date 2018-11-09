package model.dto;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity

public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idLocation;
	private Date debut;
	private Date fin;
	
	
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="CIN")
	private Client client;
	
	
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="Matricule")
	private Product product;
	
	
	public Location(){ this(0,0,null,null,null,null);}
	
	

	public Location(int idLocation,int coutLocation, Date debut, Date fin, String CIN, String matricule) {
		
		this.idLocation = idLocation;
		this.debut = debut;
		this.fin = fin;
		Client client =new Client();
		client.setCIN(CIN);
		Product product = new  Product();
		product.setMatricule(matricule);
		 product.setCoutLocation(coutLocation);
		 
		 
	}
	
	

	public int getIdLocation() {
		return idLocation;
	}



	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}



	public Date getDebut() {
		return debut;
	}



	public void setDebut(Date debut) {
		this.debut = debut;
	}



	public Date getFin() {
		return fin;
	}



	public void setFin(Date fin) {
		this.fin = fin;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	@Override
	public String toString() {
		return "Location [idLocation=" + idLocation + ", debut=" + debut + ", fin=" + fin + ", client=" + client
				+ ", voiture=" + product + "]";
	}
	
	
	
	
}
