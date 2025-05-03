package projet_hospitalier;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import dao.CreneauDAO;
import dao.PersonnelDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Emploiperso {

	public Scene getScene(Stage PrimaryStage)  {
		BorderPane borderPane = new BorderPane();
		//le menu 
		HBox menu = new HBox();
		Button btn1 = new Button("Liste du personnel");
		Button btn2 = new Button("créer un créneau");
		Button btn3 = new Button("Accueil");
		Button btn4 = new Button("L'emploi hebdomadaire");
		menu.getChildren().addAll(btn1, btn2, btn3, btn4);
		menu.getStyleClass().add("hbox"); //appliquer le css
		borderPane.setTop(menu);
		// les evenements
		btn1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) { 
				ListePersonnelle employeListe = new ListePersonnelle(); // on cree une instance de la classe liste
				Scene listeScene = employeListe.getScene(PrimaryStage);
				PrimaryStage.setScene(listeScene); 
			}

		});
		btn2.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CreerCreneau cc= new CreerCreneau();
				Scene creneauScene = cc.getScene(PrimaryStage);
				PrimaryStage.setScene(creneauScene);
			}});
		btn3.setOnAction(e -> {
			Accueil accueil = new Accueil();
			try {
				accueil.start(PrimaryStage);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// la partie la plus à gauche 
		HBox milieu= new HBox();
		VBox options=new VBox();
		DatePicker datePicker = new DatePicker();
		//
		TextField IdField=new TextField();
		IdField.setPromptText("num_creneau");
		TextField nomcreneau=new TextField();
		nomcreneau.setPromptText("nom creneau");
		DatePicker dateDebutPicker = new DatePicker();
		dateDebutPicker.setPromptText("Date de debut");	 
		DatePicker dateFinPicker = new DatePicker();
		dateFinPicker.setPromptText("Date de fin");
		TextField heure_debut=new TextField();
		heure_debut.setPromptText("Heure debut");
		TextField heure_fin=new TextField();
		heure_fin.setPromptText("Heure fin");
		TextField idemploiField = new TextField();
		idemploiField.setPromptText("idEmploi");
		Button Modifier=new Button("Modifier creneau");
		Button Supprimer=new Button ("Supprimer creneau");
		ChoiceBox<Integer> choixId=new ChoiceBox<>();
		Label titre=new Label("Fonction creneau");
		titre.setFont(new Font("Arial", 24));

		// Création du GridPane pour les champs de texte et le DatePicker
		GridPane bottomFields = new GridPane();
		bottomFields.setHgap(10);  
		bottomFields.setPadding(new Insets(10));
		options.getChildren().addAll(datePicker,titre,bottomFields,Modifier,Supprimer);
		bottomFields.addRow(0,
				new Label("num_creneau :"),
				IdField
				);
		bottomFields.addRow(1,
				new Label("Nom creneau :"),
				nomcreneau
				);
		bottomFields.addRow(2,
				new Label("Date debut :"),
				dateDebutPicker
				);
		bottomFields.addRow(3,
				new Label("Date de fin :"),
				dateFinPicker
				);
		bottomFields.addRow(4,
				new Label("Heure debut :"),
				heure_debut
				);
		bottomFields.addRow(5,
				new Label("Heure fin :"),
				heure_fin
				);
		bottomFields.addRow(6,
				new Label("id Emploi :"),
				idemploiField
				);
		bottomFields.addRow(7,
				new Label("id Infirmier :"),
				choixId
				);
		milieu.getChildren().add(options);
		// interaction avec la bdd pour la recuperation des id employe
		PersonnelDAO personnelDAO = new PersonnelDAO();
		 List<Integer> liste=null;
		try  {
			 liste=personnelDAO.listerID();
			 choixId.getItems().addAll(liste);
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		TableColumn<Creneau, Integer> numColumn = new TableColumn<>("num_creneau");
		TableColumn<Creneau, String> nomColumn = new TableColumn<>("Nom creneau");
		TableColumn<Creneau, String> datedebutColumn = new TableColumn<>("Date debut");
		TableColumn<Creneau, String> dateFinColumn = new TableColumn<>("Date de fin");
		TableColumn<Creneau, LocalTime> HeuredebutColumn = new TableColumn<>("heure debut");
		TableColumn<Creneau, LocalTime> HeureFinColumn = new TableColumn<>("heure fin");
		TableColumn<Creneau, Integer> IdemploiColumn = new TableColumn<>("id emploi");
		// Définition des propriétés pour les colonnes
		numColumn.setCellValueFactory(new PropertyValueFactory<>("num_creneau"));
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomcreneau"));   // meme nom que la classe creneau
		datedebutColumn.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
		dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("datefin"));
		HeuredebutColumn.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
		HeureFinColumn.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
		IdemploiColumn.setCellValueFactory(new PropertyValueFactory<>("idemploi"));

		// Ajout des colonnes à la table
		TableView<Creneau> tableView = new TableView<>();

		// Récupération de la liste de creneau depuis la base de données via le DAO
		CreneauDAO creneauDAO = new CreneauDAO(); // Assurez-vous d'instancier votre DAO correctement
		List<Creneau> listeCreneau = null;
		try {
			listeCreneau = creneauDAO.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Remplissage du TableView avec la liste de personnel obtenue
		ObservableList<Creneau> data = FXCollections.observableArrayList(listeCreneau);
		tableView.setItems(data);
		tableView.getColumns().addAll(numColumn, nomColumn,datedebutColumn,dateFinColumn,HeuredebutColumn,HeureFinColumn, IdemploiColumn);
		// Gestionnaire d'événements pour la sélection de ligne dans le TableView
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				// Récupérer les informations du creneau de la ligne sélectionnée
				long numcreneau =newSelection.getNum_creneau();
				String nom = newSelection.getNomcreneau();
				Date dateDebut = newSelection.getDatedebut();
				Date dateFin = newSelection.getDatefin();
				Time Heuredebut = newSelection.getHeureDebut();
				Time HeureFin = newSelection.getHeureFin();
				int  idEmploi= newSelection.getIdemploi();

				// Afficher les informations dans les champs correspondants
				IdField.setText(String.valueOf(numcreneau));
				nomcreneau.setText(nom);
				// conversion necessaire 
				LocalDate localDateDebut = dateDebut.toLocalDate();
				LocalDate localDateFin=dateFin.toLocalDate();
				LocalTime localHeuredebut= Heuredebut.toLocalTime();
				LocalTime localHeureFin=HeureFin.toLocalTime();
				dateDebutPicker.setValue(localDateDebut);
				dateFinPicker.setValue(localDateFin);
				heure_debut.setText(localHeuredebut.toString());
				heure_fin.setText(localHeureFin.toString());
				idemploiField.setText(String.valueOf(idEmploi));

			}
		});
		milieu.getChildren().add(tableView);
		borderPane.getChildren().add(milieu);
		borderPane.setLeft(options);
		borderPane.setCenter(tableView);
		Scene scene = new Scene( borderPane, 800, 600);
		scene.getStylesheets().add("/style.css");// Ajout d'une feuille de style CSS
         // les evenements des boutons 
		Modifier.setOnAction(e -> {
			LocalDate dateDebut = dateDebutPicker.getValue();
			String nomcrn=nomcreneau.getText();
			LocalDate dateFin =  dateFinPicker.getValue();
			LocalTime heureDebut = LocalTime.parse(heure_debut.getText());
			LocalTime heureFin = LocalTime.parse(heure_fin.getText());
			int idEmploi = Integer.parseInt(idemploiField.getText());
			long numcreneau= Long.parseLong(IdField.getText());
			//ca ne marche pas  sans la conversion le constructeur a les types basiques
			java.sql.Date sqlDateDebut =  java.sql.Date.valueOf(dateDebut);
			java.sql.Date sqlDateFin =  java.sql.Date.valueOf(dateFin);
			java.sql.Time sqlHeureDebut = java.sql.Time.valueOf(heureDebut);
			java.sql.Time sqlHeureFin = java.sql.Time.valueOf(heureFin);
			Creneau nwcreneau=new Creneau(numcreneau,sqlDateDebut,sqlDateFin,sqlHeureDebut,sqlHeureFin,nomcrn,idEmploi);
			CreneauDAO creneauDao = new CreneauDAO();
			try {
				creneauDao.modifier(nwcreneau);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Supprimer.setOnAction(e -> {
			 
			int numcreneau=Integer.parseInt(IdField.getText());
		  CreneauDAO creneauDao = new CreneauDAO();
			creneauDao.supprimer(numcreneau);
			 
		});
		PrimaryStage.setScene(scene);
		PrimaryStage.setTitle("Tableau du Personnel");
		PrimaryStage.show();
		return scene;
	}
}
