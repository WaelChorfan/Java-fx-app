package controller;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.dao.LocationDao;

import model.dto.Location;


public class SuiviLocationsController implements Initializable {

	ObservableList<Location> locs = FXCollections.observableArrayList();
    @FXML
    private JFXButton findLoc;

    @FXML
    private JFXButton deleteLoc;

    @FXML
    private JFXButton populateTableLoc;

    @FXML
    private ProgressBar pb;


    @FXML
    private Label Apayer;

    @FXML
    private Label MatriculeCol;

    @FXML
    private Label deCol;

    @FXML
    private Label juska;

    @FXML
    private Label IDLocationCol;


    @FXML
    private Label CIN;

    @FXML
    private Label nomC;

    @FXML
    private Label prenomC;

    @FXML
    private JFXComboBox<Integer> IdLocationsCB;

	void popcbIdLocations() {
   	 LocationDao locDao = new LocationDao();
         locs.addAll(locDao.retrieveAll());
   	 locs.forEach(c->IdLocationsCB.getItems().add(c.getIdLocation()));
   	 System.out.println("popcbIdLocations");
   }
   
    @FXML
    void trouverLoc(ActionEvent event) {
    	//trouver
    	
    	int idToFind =IdLocationsCB.getSelectionModel().getSelectedItem();
    	LocationDao dao = new LocationDao();
    	Location loc = (Location) dao.retrieve(idToFind);
    
     IDLocationCol.setText(String.valueOf(loc.getIdLocation()) );

  MatriculeCol.setText(loc.getProduct().getMatricule());
  
deCol.setText(loc.getDebut().toString());
  juska.setText(loc.getFin().toString());
  Apayer.setText(String.valueOf(loc.getProduct().getCoutLocation()));
  CIN.setText(loc.getClient().getCIN());
  nomC.setText(loc.getClient().getNom());
  prenomC.setText(loc.getClient().getPrenom());
  
  
    	
    	
    	
    	
    	
    	
    	
    	
    	/*
    	Date d1 = loc.getDebut();
        Date d2 = loc.getFin();
        LocalDate ld ;
        ld = LocalDate.now();
        Date now = java.sql.Date.valueOf(ld);
        System.out.println(ld.getDayOfMonth());
        	    long deb =d1.getTime()/(60*60*1000);
        	    long fi = d2.getTime()/(60*60*1000);
        	    long nn =  now.getTime()/(60*60*1000);
        	 
        	    System.out.println("x=" +deb);
        	    System.out.println("y=" +fi);
        	    System.out.println("n=" +nn);
        	    
        	    long nomi = deb-nn;
        	    long denomi = deb-fi;
        	


double per = (nomi /denomi) * 100;

System.out.println("per="+ per);*/



    }

    
    @FXML
    void deleteLoc(ActionEvent event) {
    	
    	
    	int idToFind =IdLocationsCB.getSelectionModel().getSelectedItem();
    	LocationDao dao = new LocationDao();
    	 dao.delete(idToFind);
    
    	 System.out.println("Supprimé");
    	 
    	 
        
    }


    @FXML
    void retour(MouseEvent event) {
    	
    	Parent root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GestionDeLocation.fxml"));
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
  /*  
    @FXML
void populateTableLocBySelectedId(int idSelected) {
	System.out.println("populateTableLocBySelectedId");
	LocationDao dao = new LocationDao();
	ArrayList<Location> locs = dao.retrieveAll();
	ObservableList<Location> locsById = FXCollections.observableArrayList();
	locs.forEach(l->{
		if(l.getIdLocation()==idSelected)
			locsById.add(l);
		
	});
	
	IDLocationCol.setCellValueFactory(new PropertyValueFactory<Location, Integer>("	idLocation"));
	
	/*MatriculeCol.setCellFactory(new PropertyValueFactory<Location, String>(locsById.get(idSelected).getProduct().getMatricule()));
	LoueaCol
	deCol
	aPayerCol
	IdLocationsCB
	
	
	
}*/
		
		
 
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize");
		popcbIdLocations();
		
		
		
	}

}
