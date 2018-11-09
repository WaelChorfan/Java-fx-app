package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.dao.*;
import model.dto.*;

public class ManipulationController implements Initializable{
    
    private ObservableList<Product> prodsData = FXCollections.observableArrayList();
    private ObservableList<Categorie> catsData = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Product> tableProdByCat;
    @FXML
    private TableColumn<Product, Integer> refProdCatColmn;
    @FXML
    private TableColumn<Product, String> nameProdCatColmn;
    @FXML
    private TableView<Categorie> tableCat;
    @FXML
    private TableColumn<Categorie, Integer> codeCatColmn;
    @FXML
    private TableColumn<Categorie, String> nameCatColmn;
    @FXML
    private TableView<Product> tableProd;
    @FXML
    private TableColumn<Product, Integer> refProdColmn;
    @FXML
    private TableColumn<Product, String> nameProdColmn;
    @FXML
    private TableColumn<Product, Double> priceProdColmn;
    @FXML
    private TableColumn<Product, Integer> qtProdColmn;
    @FXML
    private TableColumn<Product, String> catProdColmn;
    @FXML
    private JFXTextField refProd;
    @FXML
    private JFXTextField nameProd;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXTextField quantity;
    @FXML
    private JFXComboBox<String> categorieCB;
    @FXML
    private JFXButton findProd;
    @FXML
    private JFXButton deleteProd;
    @FXML
    private JFXButton commitProd;
    @FXML
    private JFXButton cancelProd;
    @FXML
    private JFXTextField codeCat;
    @FXML
    private JFXTextField nameCat;
    @FXML
    private JFXButton deleteCat;
    @FXML
    private JFXButton commitCat;
    @FXML
    private JFXButton cancelCat;
    @FXML
    private Label userLabel;

    
    @FXML
    void findProductByProductName(MouseEvent event) {
        try{
        String nameToFind = nameProd.getText();
        int idToFind = Integer.parseInt(refProd.getText());
        ProductDao dao = new ProductDao();
        ArrayList<Product> prods = (ArrayList<Product>) dao.retrieveAll();
        HashMap<String, Product> mapProdName = new HashMap<>();
        
        prods.forEach(p->mapProdName.put(p.getMatricule(), p));
        
        if (mapProdName.containsKey(nameToFind)){
            Product p = mapProdName.get(nameToFind);
            refProd.setText(String.valueOf(p.getIdVoiture()));
            price.setText(String.valueOf(p.getCoutLocation()));
            quantity.setText(String.valueOf(p.getQuantite()));
            categorieCB.getSelectionModel().select(p.getCategorie().getNomCat());
        }
        
        HashMap<Integer, Product> mapProdRef = new HashMap<>();
        
        prods.forEach(p->mapProdRef.put(p.getIdVoiture(), p));
        
        if (mapProdRef.containsKey(idToFind)){
            Product p = mapProdRef.get(idToFind);
            nameProd.setText(p.getMatricule());
            price.setText(String.valueOf(p.getCoutLocation()));
            quantity.setText(String.valueOf(p.getQuantite()));
            categorieCB.getSelectionModel().select(p.getCategorie().getNomCat());
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Reference inexistante !");
            alert.show();
        }
        }catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Empty fields !");
            alert.show();
        }
    }

    @FXML
    void commitModifOrInsertCategorie(MouseEvent event) {
        try{
        int ref = Integer.parseInt(codeCat.getText());
        Categorie cat = new Categorie();
        cat.setCodeCat(ref);
        cat.setNomCat(nameCat.getText());
        
        CategorieDao dao = new CategorieDao();
        Categorie oldCat =dao.retrieve(ref);
        categorieCB.getItems().add(cat.getNomCat());

        if(oldCat!=null){
            categorieCB.getItems().remove(oldCat.getNomCat());
            dao.update(cat);
            ObservableList<Categorie> newCatsData = FXCollections.observableArrayList();
            catsData.forEach(c->{
                if(c.getCodeCat()==cat.getCodeCat()){
                    newCatsData.add(cat);
                }else{
                    newCatsData.add(c);
                }
            catsData = newCatsData;
            });
        }else{
            dao.create(cat);
            catsData.add(cat);
        }
        tableCat.setItems(catsData);
        populateProdTable();
        }catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Empty fields !");
            alert.show();       }
    }

    @FXML
    void updateOrInsertProduct(MouseEvent event) {
        try{
        int ref = Integer.parseInt(refProd.getText());
        Product prod = new Product();
        prod.setIdVoiture(ref);
        prod.setMatricule(nameProd.getText());
        prod.setCoutLocation(Double.parseDouble(price.getText()));
        prod.setQuantite((Integer.parseInt(quantity.getText())));
        
        String catName = categorieCB.getSelectionModel().getSelectedItem();
        
        
        Categorie cat = new Categorie();
        CategorieDao catDao = new CategorieDao();
        cat.setNomCat(catName);
        cat.setCodeCat(catDao.retrieveCatByName(catName));
        
        prod.setCategorie(cat);
        
        ProductDao prodDao = new ProductDao();
        Product oldProd = prodDao.retrieve(ref);
        if(oldProd !=null){
            prodDao.update(prod);
            
            ObservableList<Product> newProdsData = FXCollections.observableArrayList();
            prodsData.forEach(p->{
            if(p.getIdVoiture()==oldProd.getIdVoiture()){
                newProdsData.add(prod);
            }else{
                newProdsData.add(p);
            }
            prodsData = newProdsData;
        });
            
        }else{
            prodDao.create(prod);
            prodsData.add(prod);
        }
        tableProd.setItems(prodsData);
        }catch (Exception e) {

            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Empty fields !");
            alert.show();               
            }

    }

    @FXML
    void deleteCategorie(MouseEvent event) {
        try{
        int ref = Integer.parseInt(codeCat.getText());
        CategorieDao dao = new CategorieDao();
        Categorie oldCat = dao.retrieve(ref);
        if(oldCat !=null){
            dao.delete(ref);
            categorieCB.getItems().remove(oldCat.getNomCat());
            ObservableList<Categorie> newCatsData = FXCollections.observableArrayList();
            catsData.forEach(c->{
            if(c.getCodeCat()!=oldCat.getCodeCat()){
                newCatsData.add(c);
            }
        });
            catsData = newCatsData;
            
        }
        tableCat.setItems(catsData);
        }catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Invalide selection !");
            alert.show();               }
    }

    @FXML
    void deleteProduct(MouseEvent event) {
        try{
        int ref = Integer.parseInt(refProd.getText());
        ProductDao prodDao = new ProductDao();
        Product oldProd = prodDao.retrieve(ref);
        if(oldProd !=null){
            prodDao.delete(ref);
            ObservableList<Product> newProdsData = FXCollections.observableArrayList();
            prodsData.forEach(p->{
            if(p.getIdVoiture()!=oldProd.getIdVoiture()){
                newProdsData.add(p);
            }
        });
            prodsData = newProdsData;
            
        }
        tableProd.setItems(prodsData);
        }catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Invalide selection !");
            alert.show();       
        }
        
    }
    
    @FXML
    void cancelModifCat(MouseEvent event) {
        codeCat.clear();
        nameCat.clear();
    }

    @FXML
    void cancelProduct(MouseEvent event) {
        refProd.setText("");
        nameProd.clear();
        price.clear();
        quantity.clear();
        categorieCB.getSelectionModel().selectFirst();
    }
    
    private void formatReferencePriceAndQuantity(){
        quantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                quantity.setText(oldValue);
            }
        });
        
        price.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("([0-9]*)(\\.)?([0-9]*)")){
                price.setText(oldValue);
            }
        });
        refProd.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[0-9]*")){
                refProd.setText(oldValue);
            }
        });
    }

    private void populateCategorieCBox(){
        CategorieDao catDao = new CategorieDao();
        ArrayList<Categorie> cats = (ArrayList<Categorie>) catDao.retrieveAll();
        categorieCB.getItems().add("-- Select Categorie --");
        categorieCB.getSelectionModel().selectFirst();
        cats.forEach(c->categorieCB.getItems().add(c.getNomCat()));
    }
    
    private void populateProdTable(){

        ProductDao prodDao = new ProductDao();
        prodsData.clear();
        prodsData.addAll(prodDao.retrieveAll());
        
        refProdColmn.setCellValueFactory(
              new PropertyValueFactory<Product, Integer>("idVoiture"));
        nameProdColmn.setCellValueFactory(
              new PropertyValueFactory<Product, String>("matricule"));
        priceProdColmn.setCellValueFactory(
              new PropertyValueFactory<Product, Double>("coutLocation"));
        qtProdColmn.setCellValueFactory(
              new PropertyValueFactory<Product, Integer>("quantite"));
        catProdColmn.setCellValueFactory(
                new PropertyValueFactory<Product,String>("categorie"));
        
        tableProd.setItems(prodsData);
    }
    
    private void populateCatTable(){

        CategorieDao dao = new CategorieDao();
        catsData.addAll(dao.retrieveAll());
        
        codeCatColmn.setCellValueFactory(
                new PropertyValueFactory<Categorie, Integer>("codeCat"));
        nameCatColmn.setCellValueFactory(
                new PropertyValueFactory<Categorie, String>("nomCat"));
        
        tableCat.setItems(catsData);
            
    }
    
    private void populateProdByCatTable(int idCatSelected){

        ProductDao dao = new ProductDao();
        ArrayList<Product> prods = dao.retrieveAll();
        ObservableList<Product> prodsByCatData = FXCollections.observableArrayList();
        prods.forEach(p->{
            if(p.getCategorie().getCodeCat()==idCatSelected)
                prodsByCatData.add(p);
        });
        
        refProdCatColmn.setCellValueFactory(
              new PropertyValueFactory<Product, Integer>("idVoiture"));
        nameProdCatColmn.setCellValueFactory(
              new PropertyValueFactory<Product, String>("matricule"));
        
        tableProdByCat.setItems(prodsByCatData);
        
    }
    

    @FXML
    void selectRowCat(MouseEvent event) {
        Categorie catSelected = tableCat.getSelectionModel().getSelectedItem();
        if(catSelected!=null){
        codeCat.setText(String.valueOf(catSelected.getCodeCat()));
        nameCat.setText(catSelected.getNomCat());
        populateProdByCatTable(catSelected.getCodeCat());
        }
        
    }

    @FXML
    void selectRowProds(MouseEvent event) {
        Product prodSelected = tableProd.getSelectionModel().getSelectedItem();
        if(prodSelected!=null){
        refProd.setText(String.valueOf(prodSelected.getIdVoiture()));
        nameProd.setText(prodSelected.getMatricule());
        price.setText(String.valueOf(prodSelected.getCoutLocation()));
        quantity.setText(String.valueOf(prodSelected.getQuantite()));
        categorieCB.getSelectionModel().select(prodSelected.getCategorie().getNomCat());
        }
    }
    
    @FXML
    void retour(MouseEvent event) {
    	
    	Parent root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GestionDeLocation.fxml"));
	    	Scene scene = new Scene(root);
	    	
			//scene.getStylesheets().add(getClass().getResource("/view/MainStyle.css").toExternalForm());

	    	
	    	Main.getStage().setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	System.out.println("buttonpressed");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatReferencePriceAndQuantity();
        populateCategorieCBox();
        populateCatTable();
        populateProdTable();
        TreeSet<String> autoCompletionProd = new TreeSet<String>();
        prodsData.forEach(p->autoCompletionProd.add(p.getMatricule()));
        TextFields.bindAutoCompletion(nameProd, autoCompletionProd);
        TreeSet<String> autoCompletionCat = new TreeSet<String>();
        catsData.forEach(c->autoCompletionCat.add(c.getNomCat()));
        TextFields.bindAutoCompletion(nameCat, autoCompletionCat);

        
    }

}
