package controller;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.dao.ClientDao;
import model.dao.LocationDao;
import model.dao.ProductDao;
import model.dto.Categorie;
import model.dto.Client;
import model.dto.Location;
import model.dto.Product;

public class GestionLocationController implements Initializable{
	ObservableList<Client> clients = FXCollections.observableArrayList();
	 ObservableList<Categorie> cats = FXCollections.observableArrayList();
	 ObservableList<Product> prods = FXCollections.observableArrayList();

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField CIN;

    @FXML
    private JFXTextField tel;

    @FXML
    private DatePicker d1;
    
    

    @FXML
    private DatePicker d2;

    @FXML
    private JFXComboBox<String> IdVoituresCB;

    @FXML
    private JFXComboBox<String> idClientsCB;

    @FXML
    private JFXTextField adr;

    @FXML
    private JFXTextField idClient;
    

    @FXML
    private JFXTextField idLocation;
    
    @FXML
    void Allouer(ActionEvent event) {  
    
    	Location loc = new Location();
            LocalDate ld1;
    		 ld1= d1.getValue();
			 Date d1 = java.sql.Date.valueOf(ld1);
			 loc.setDebut(d1);	
            LocalDate ld2;
      		 ld2= d2.getValue();
  			 Date d2 = java.sql.Date.valueOf(ld2);
  			 loc.setFin(d2);	
  			 ClientDao clientDao = new ClientDao();
      	int r1 = Integer.parseInt( idClientsCB.getSelectionModel().getSelectedItem()); 	
    	loc.setClient(clientDao.retrieve(r1));
    	ProductDao productDao  = new ProductDao();   	
    	int r2 = Integer.parseInt( IdVoituresCB.getSelectionModel().getSelectedItem());   	
    	loc.setProduct(productDao.retrieve(r2));  	
    LocationDao daoLoc = new LocationDao();
    daoLoc.create(loc);
    
	System.out.println("Allouer succes");
    }

    @FXML
    void findClient(MouseEvent event) {
    	  int ref = Integer.parseInt(idClient.getText());
    	  
    	  ClientDao dao = new ClientDao();
    	  
    	  Client cl = dao.retrieve(ref);
    	  
    	  nom.setText(cl.getNom() );
    	  prenom.setText(cl.getPrenom());
    	  CIN.setText(cl.getCIN());
    	  tel.setText(cl.getTel());
	
    }
    @FXML
    void CancelAllouer(MouseEvent event) {
    	IdVoituresCB.getSelectionModel().selectFirst();
    	idClientsCB.getSelectionModel().selectFirst();
    	d1.setAccessibleText(null);
    	d2.setAccessibleText(null);
    	System.out.print("cancelAllouer Succes");
    }


    @FXML
    void ajouterClient(MouseEvent event) {

    	
    		try{
    	        int ref = Integer.parseInt(idClient.getText());
    	        Client cli = new Client();
    	        cli.setIdClient(ref);
    	        cli.setNom(nom.getText());
    	        cli.setPrenom(prenom.getText());
    	        cli.setCIN(CIN.getText());
    	        cli.setTel(tel.getText());    
    	        ClientDao cliDao = new ClientDao();
    	        cliDao.create(cli);  		
    }catch (Exception e) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText("Empty fields !");
        alert.show();               }  	 }

    @FXML
    void cancelAjouterClient(MouseEvent event) {
    	idClient.setText("");
    	nom.clear();
    	prenom.clear();
    	CIN.clear();
    	tel.clear();
  
    	//categorieCB.getSelectionModel().selectFirst();
    }
   
    @FXML
    void popcbIdClients() {
   	
    	
    	 ClientDao cliDao = new ClientDao();
   
    	 clients.addAll(cliDao.retrieveAll());
    	 idClientsCB.getItems().add("-- Select ID Client --");
    	 idClientsCB.getSelectionModel().selectFirst();
		ArrayList< Client> cls = (ArrayList<Client>) cliDao.retrieveAll();	
		cls.forEach(c->idClientsCB.getItems().add(String.valueOf(c.getIdClient())));
    }

    @FXML
    void popcbIdVoitures() {
    	ProductDao prodDao = new ProductDao();
		prods.addAll(prodDao.retrieveAll());
		IdVoituresCB.getItems().add("-- Select ID voiture --");
		IdVoituresCB.getSelectionModel().selectFirst();
		ArrayList<Product> pds = (ArrayList<Product>) prodDao.retrieveAll();	
		pds.forEach(c->IdVoituresCB.getItems().add(String.valueOf(c.getIdVoiture())));
  }
    
    
    @FXML
    void afficherLocations(MouseEvent event) {
    	
    	Parent root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/SuiviDesLocations.fxml"));
	    	Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/view/MainStyle.css").toExternalForm());

	    	
	    	Main.getStage().setScene(scene);
	    	Main.getStage().setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	System.out.println("buttonpressed");
    }
			


    @FXML
    void afficherVoitures(MouseEvent event) {
    	
    	Parent root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GestionDesVoitures.fxml"));
	    	Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/view/MainStyle.css").toExternalForm());

	    	
	    	Main.getStage().setScene(scene);
	    	Main.getStage().setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	System.out.println("buttonpressed");
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.print("SuccesGestionLocation");
		popcbIdVoitures();
		popcbIdClients();
		
		
	}
	
}
