package model.dto;


import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idClient;
	private String CIN;
	private String nom;    
	private String prenom; 	
	private String tel;
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
//		@JoinColumn(name="CIN")
@JoinColumn(name="CIN", referencedColumnName = "CIN", insertable = false, updatable = false)
	private Location location;
	
	
	
	
	public Client(){
		this(0,null,null,null,null);
	}
	
	
	
	
	public Client(int idClient, String cIN, String nom, String prenom, String tel) {
		super();
		this.idClient = idClient;
		CIN = cIN;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		
		
	}




	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", CIN=" + CIN + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel
				+ "]";
	}
	
	

    
  
	

 
}
