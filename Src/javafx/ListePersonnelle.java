package projet_hospitalier;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import dao.PersonnelDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * la vue liste du personnel 
 *
 */

public class ListePersonnelle  {

	public Scene getScene(Stage PrimaryStage)  {
		// Création des éléments de la barre de navigation
		ToggleButton toggleButton1 = new ToggleButton("Liste du personnel");
		ToggleButton toggleButton2 = new ToggleButton("Créer un créneau");
		ToggleButton toggleButton3 = new ToggleButton("Accueil");
		ToggleButton toggleButton4 = new ToggleButton("L'emploi hebdomadaire");

		// Création de la barre de navigation
		ToolBar navBar = new ToolBar(toggleButton1, toggleButton2, toggleButton3, toggleButton4);
        // les evenements des boutons du menu 
		toggleButton3 .setOnAction(e -> {
			Accueil accueil = new Accueil();
			try {
				accueil.start(PrimaryStage);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		toggleButton2.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CreerCreneau cc= new CreerCreneau();
				Scene creneauScene = cc.getScene(PrimaryStage);
				PrimaryStage.setScene(creneauScene);
			}});
		toggleButton4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				 EmploiActuel actuel = new EmploiActuel();
		         Scene actuelscene = actuel.getScene(PrimaryStage);	
		         PrimaryStage.setScene(actuelscene);
			}
            
        });
		// Création du champ de texte pour la recherche
		TextField searchField = new TextField();
		searchField.setPromptText("Rechercher un infirmier");

		// Création du bouton de recherche
		Button searchButton = new Button("Rechercher");

		// Action du bouton de recherche
		searchButton.setOnAction(e -> {
			// Insérer ici la logique de recherche en fonction du texte saisi
			String searchText = searchField.getText();
			System.out.println("Recherche : " + searchText);
		});

		// Création de la disposition horizontale pour la barre de recherche
		HBox searchBar = new HBox(10); // Espacement entre les éléments
		searchBar.setPadding(new Insets(10)); // Marge autour des éléments
		searchBar.getChildren().addAll(searchField, searchButton);

		// Création des colonnes pour le TableView
		TableColumn<Personnel, Integer> idColumn = new TableColumn<>("ID");
		TableColumn<Personnel, String> nomColumn = new TableColumn<>("Nom");
		TableColumn<Personnel, String> prenomColumn = new TableColumn<>("Prénom");
		TableColumn<Personnel, String> dateNaissanceColumn = new TableColumn<>("Date de Naissance");
		TableColumn<Personnel, String> tempsTravailColumn = new TableColumn<>("Temps de Travail Mensuel");
		TableColumn<Personnel, Integer> fonctionColumn = new TableColumn<>("Fonction");
		TableColumn<Personnel, Integer> specialiteColumn = new TableColumn<>("Spécialité");


		// Définition des propriétés pour les colonnes
		idColumn.setCellValueFactory(new PropertyValueFactory<>("idPersonnel"));
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
		tempsTravailColumn.setCellValueFactory(new PropertyValueFactory<>("tempsTravailMensuel"));
		fonctionColumn.setCellValueFactory(new PropertyValueFactory<>("idFonction"));
		specialiteColumn.setCellValueFactory(new PropertyValueFactory<>("idSpecialite"));


		// Ajout des colonnes à la table
		TableView<Personnel> tableView = new TableView<>();

		// Récupération de la liste de personnel depuis la base de données via le DAO
		PersonnelDAO personnelDAO = new PersonnelDAO(); // Assurez-vous d'instancier votre DAO correctement
		List<Personnel> listePersonnel = null;
		try {
			listePersonnel = personnelDAO.lister();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Remplissage du TableView avec la liste de personnel obtenue
		ObservableList<Personnel> data = FXCollections.observableArrayList(listePersonnel);
		tableView.setItems(data);
		tableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, dateNaissanceColumn, tempsTravailColumn, fonctionColumn, specialiteColumn);

		// Création des champs de texte
		TextField IdField=new TextField();
		IdField.setPromptText("id");
		TextField nomField = new TextField();
		nomField.setPromptText("Nom");

		TextField prenomField = new TextField();
		prenomField.setPromptText("Prénom");

		// Création du champ de date de naissance
		DatePicker dateNaissancePicker = new DatePicker();
		dateNaissancePicker.setPromptText("Date de Naissance");

		TextField tempsTravailField = new TextField();
		tempsTravailField.setPromptText("Temps de Travail Mensuel");

		TextField fonctionField = new TextField();
		fonctionField.setPromptText("Fonction");

		TextField specialiteField = new TextField();
		specialiteField.setPromptText("Spécialité");

		// Création du GridPane pour les champs de texte et le DatePicker
		GridPane bottomFields = new GridPane();
		bottomFields.setHgap(10); // Espacement horizontal entre les éléments
		bottomFields.setPadding(new Insets(10)); // Marge autour des éléments

		// Gestionnaire d'événements pour la sélection de ligne dans le TableView
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				// Récupérer les informations du personnel de la ligne sélectionnée
				int ID=newSelection.getIdPersonnel();
				String nom = newSelection.getNom();
				String prenom = newSelection.getPrenom();
				Date dateNaissance = newSelection.getDateNaissance();
				int tempsTravail = newSelection.getTempsTravailMensuel();
				int fonction = newSelection.getIdFonction();
				int specialite = newSelection.getIdSpecialite();
                
				// Afficher les informations dans les champs correspondants
				IdField.setText(String.valueOf(ID));
				nomField.setText(nom);
				prenomField.setText(prenom);
				// conversion necessaire 
				LocalDate localDateNaissance = dateNaissance.toLocalDate();
		        dateNaissancePicker.setValue(localDateNaissance); 
		        tempsTravailField.setText(String.valueOf(tempsTravail));
		        fonctionField.setText(String.valueOf(fonction));
		        specialiteField.setText(String.valueOf(specialite));
			}
		});
		// Définition des contraintes pour les colonnes du GridPane
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setMinWidth(150); // Largeur fixe pour la colonne 1
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setMinWidth(150); // Largeur fixe pour la colonne 2

		// Ajout des contraintes aux colonnes du GridPane
		bottomFields.getColumnConstraints().addAll(col1, col2);

		// Ajout des marges intérieures pour chaque élément
		GridPane.setMargin(IdField, new Insets(0, 0, 10, 0));
		GridPane.setMargin(nomField, new Insets(0, 0, 10, 0)); // Marge bas de 10 px pour le champ nomField
		GridPane.setMargin(prenomField, new Insets(0, 0, 10, 0)); // Marge bas de 10 px pour le champ prenomField
		GridPane.setMargin(dateNaissancePicker, new Insets(0, 0, 10, 0)); // Marge bas de 10 px pour le DatePicker
		GridPane.setMargin(tempsTravailField, new Insets(0, 0, 10, 0)); // Marge bas de 10 px pour le champ tempsTravailField
		GridPane.setMargin(fonctionField, new Insets(0, 0, 10, 0)); // Marge bas de 10 px pour le champ fonctionField
		GridPane.setMargin(specialiteField, new Insets(0, 0, 0, 0)); // Pas de marge bas pour le champ specialiteField

		// Ajout des champs de texte au GridPane
		bottomFields.addRow(0,
				new Label("ID :"),
				IdField
				);
		bottomFields.addRow(1,
				new Label("Nom :"),
				nomField
				);
		bottomFields.addRow(2,
				new Label("Prénom :"),
				prenomField
				);
		bottomFields.addRow(3,
				new Label("Date de Naissance :"),
				dateNaissancePicker
				);
		bottomFields.addRow(4,
				new Label("Temps de Travail Mensuel :"),
				tempsTravailField
				);
		bottomFields.addRow(5,
				new Label("Fonction :"),
				fonctionField
				);
		bottomFields.addRow(6,
				new Label("Spécialité :"),
				specialiteField
				);



		// Création de la HBox pour les boutons Supprimée, Modifié,Enregistrer
		Button deleteButton = new Button("Supprimer");
		Button modifyButton = new Button("Modifier");
		Button saveButton = new Button("Enregistrer");
		Button clearButton = new Button("Effacer");
        // evenements des boutons pour ineragir avec la bdd 
		saveButton.setOnAction(e -> {
			String Nom = nomField.getText();
			String Prenom=prenomField.getText();
			LocalDate dateNaissance = dateNaissancePicker.getValue();
			int tempstravail=Integer.parseInt(tempsTravailField.getText());
			int Fonction= Integer.parseInt(fonctionField.getText());
			int Specialite= Integer.parseInt(specialiteField.getText());
			//ca ne marche sans la conversion le constructeur a les types basiques
			 java.sql.Date sqlDateNaissance =  java.sql.Date.valueOf(dateNaissance);
			Personnel nwinfirmier=new Personnel(Nom,Prenom,sqlDateNaissance,tempstravail,Fonction, Specialite);
			PersonnelDAO personnelDao = new PersonnelDAO();
			try {
				personnelDao.ajouter(nwinfirmier);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		deleteButton.setOnAction(e -> {
			String Nom = nomField.getText(); 
			PersonnelDAO personnelDao = new PersonnelDAO();
			try {
				personnelDao.supprimer(Nom);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		modifyButton.setOnAction(e -> {
            int id=Integer.parseInt(IdField.getText());
			String Nom = nomField.getText();
			String Prenom=prenomField.getText();
			LocalDate dateNaissance = dateNaissancePicker.getValue();
			int tempstravail=Integer.parseInt(tempsTravailField.getText());
			int Fonction= Integer.parseInt(fonctionField.getText());
			int Specialite= Integer.parseInt(specialiteField.getText());
			
			//ca ne marche sans la conversion le constructeur a les types basiques
			 java.sql.Date sqlDateNaissance =  java.sql.Date.valueOf(dateNaissance);
			Personnel nwinfirmier=new Personnel(id,Nom,Prenom,sqlDateNaissance,tempstravail,Fonction, Specialite);
			PersonnelDAO personnelDao = new PersonnelDAO();
			try {
				personnelDao.modifier(nwinfirmier);
	            // bottomFields.add(alert,6,1);
			} catch (SQLException e1) {
				 
				e1.printStackTrace();
			}
		});
		clearButton.setOnAction(e -> {
		    IdField.clear();
		    nomField.clear();
		    prenomField.clear();
		    dateNaissancePicker.setValue(null);
		    tempsTravailField.clear();
		    fonctionField.clear();
		    specialiteField.clear();
		});
		deleteButton.setOnAction(e -> {
			String Nom = nomField.getText();
			String Prenom=prenomField.getText();
			LocalDate dateNaissance = dateNaissancePicker.getValue();
			int tempstravail=Integer.parseInt(tempsTravailField.getText());
			int Fonction= Integer.parseInt(fonctionField.getText());
			int Specialite= Integer.parseInt(specialiteField.getText());
			//ca ne marche sans la conversion le constructeur a les types basiques
			 java.sql.Date sqlDateNaissance =  java.sql.Date.valueOf(dateNaissance);
			Personnel nwinfirmier=new Personnel(Nom,Prenom,sqlDateNaissance,tempstravail,Fonction, Specialite);
			PersonnelDAO personnelDao = new PersonnelDAO();
			try {
				personnelDao.supprimer(nwinfirmier.getNom());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		// Création de la disposition horizontale pour les boutons
		HBox buttonBox = new HBox(10); // Espacement entre les boutons
		buttonBox.getChildren().addAll(deleteButton, modifyButton, saveButton,clearButton);
		buttonBox.setPadding(new Insets(10)); // Marge autour des boutons

		// Création de la disposition principale
		VBox root = new VBox();
		root.getChildren().addAll(navBar, searchBar); // Ajouter d'abord la barre de navigation et de recherche
		root.getChildren().add(tableView); // Ajouter le TableView
		root.getChildren().add(bottomFields); // Ajouter les champs de texte en bas
		root.getChildren().add(buttonBox); // Ajouter les champs de button en bas




		// Création de la scène principale

		Scene scene = new Scene(root, 800, 600);
		 scene.getStylesheets().add("/style.css");// Ajout d'une feuille de style CSS

		PrimaryStage.setScene(scene);
		PrimaryStage.setTitle("Tableau du Personnel");
		PrimaryStage.show();

		// Application de styles CSS aux éléments
		toggleButton1.getStyleClass().add("toggle-button");
		toggleButton2.getStyleClass().add("toggle-button");
		toggleButton3.getStyleClass().add("toggle-button");
		toggleButton4.getStyleClass().add("toggle-button");
		navBar.getStyleClass().add("hbox");
	
 return scene;
}
}

