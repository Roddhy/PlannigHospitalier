package projet_hospitalier;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.CreneauDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * vue emploi du temps du javafx
 *ceci est l'afficahge voulu si c'etait fonctionnel (le tableau est statique)
 */
public class EmploiActuel {
	public Scene getScene(Stage PrimaryStage) {

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

		//creation de la partie emploie
		// la partie la plus à gauche 
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

		// le tableau representatif de l'emploi 
		ScrollPane scroll=new ScrollPane();
		GridPane emploiDuTemps = new GridPane();
		emploiDuTemps.setHgap(10); // Espacement horizontal
		emploiDuTemps.setVgap(10); // Espacement vertical
		emploiDuTemps.getStyleClass().add("gridpane");

		//entete verticale du tableau
		String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi","Samedi","Dimanche"};
		for (int i = 0; i < jours.length; i++) {
			Label jour = new Label(jours[i]);
			jour.getStyleClass().add("case");
			emploiDuTemps.add(jour, i + 1, 0); 
		}
		// entete horizentale du tableau
		for (int i = 0; i <24; i++) {
			Label heure = new Label(i + "h");
			heure.getStyleClass().add("case");
			emploiDuTemps.add(heure, 0, i+1 );  
		}
		// les cases vides
		for (int i = 1; i <= jours.length; i++) {
			for (int j = 1; j <= 24; j++) { 
				Label caseVide = new Label("");
				caseVide.getStyleClass().add("case");
				emploiDuTemps.add(caseVide, i, j);
			}
		}
		// scroller le tableau de l'emploi pour un affichage complet
		scroll.setContent(emploiDuTemps);
		borderPane.setCenter(scroll);
		borderPane.setLeft(options);
        

		//la partie du bas
		HBox bottomPanel = new HBox();
		Button nouveauCreneauBtn = new Button("Créer un nouveau créneau");
		nouveauCreneauBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				CreerCreneau creer = new CreerCreneau();
				Scene creation=creer.getScene(PrimaryStage);
				PrimaryStage.setScene(creation);
			}
		});

        // trier l'emploi a afficher (en cours .....)
		Button voirEmploiBtn = new Button("Voir un seul emploi du temps");           
		bottomPanel.getChildren().addAll(nouveauCreneauBtn, voirEmploiBtn);
		bottomPanel.getStyleClass().add("hbox");
		borderPane.setBottom(bottomPanel);
		
		//les  evenements des boutons 
		Modifier.setOnAction(e -> {
            long id=Integer.parseInt(IdField.getText());
			String Nomcrneau = nomcreneau.getText();
			LocalDate dateDebut = dateDebutPicker.getValue();
			LocalDate dateFin= dateFinPicker.getValue();
			LocalTime heureDebut = LocalTime.parse( heure_debut.getText());
			LocalTime heureFin = LocalTime.parse(heure_fin.getText());
			int Idemploi= Integer.parseInt( idemploiField.getText());
			
			//ca ne marche sans la conversion le constructeur a les types basiques
			 java.sql.Date sqlDateDebut =  java.sql.Date.valueOf(dateDebut);
			 java.sql.Date sqlDateFin =  java.sql.Date.valueOf(dateFin);
			 java.sql.Time sqlHeuredebut=java.sql.Time.valueOf(heureDebut);
			 java.sql.Time sqlHeureFin=java.sql.Time.valueOf(heureFin);
			 Creneau nwcreneau=new Creneau(id,sqlDateDebut,sqlDateFin,sqlHeuredebut,sqlHeureFin,Nomcrneau,Idemploi);
			CreneauDAO creneauDao = new CreneauDAO();
			try {
				creneauDao.modifier(nwcreneau);
	            // bottomFields.add(alert,6,1);
			} catch (SQLException e1) {
				 
				e1.printStackTrace();
			}
		});
		Supprimer.setOnAction(e -> {
			int  num_creneau = Integer.parseInt(IdField.getText()); 
			CreneauDAO creneauDao = new CreneauDAO();
			creneauDao.supprimer(num_creneau);		 
		});
		
       // la scene 
		Scene scene = new Scene(borderPane, 800, 600);
		scene.getStylesheets().add("/style.css");  
		return scene;
	}


}


