package projet_hospitalier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import dao.CreneauDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * Vue de la creation d'un creneau
 *
 */

public class CreerCreneau {
	private Connection conn1;
   
	// etablir la connexion avec la bdd
	public CreerCreneau(){
		try {
			conn1=Connexion.getInstance();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur de connexion");
		}
	}
   // creation de la scene
	public Scene getScene(Stage PrimaryStage) {
		BorderPane borderPane = new BorderPane();
		//le menu 
		HBox menu = new HBox(10);
		Button btn1 = new Button("Liste du personnel");
		Button btn2 = new Button("creer un créneau");
		Button btn3 = new Button("Accueil");
		Button btn4 = new Button("L'emploi hebdomadaire");
		menu.getChildren().addAll(btn1, btn2, btn3, btn4);
		menu.getStyleClass().add("hbox");
		borderPane.setTop(menu);
		//les evenements
		btn2.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CreerCreneau cc= new CreerCreneau();
				Scene creneauScene = cc.getScene(PrimaryStage);
				PrimaryStage.setScene(creneauScene);
			}});
		btn1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) { 
				ListePersonnelle employeListe = new ListePersonnelle(); // on cree une instance de la classe liste
				Scene listeScene = employeListe.getScene(PrimaryStage);
				PrimaryStage.setScene(listeScene); 
			}
		});
		btn3.setOnAction(e -> {
			Accueil accueil = new Accueil();
			try {
				accueil.start(PrimaryStage);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				EmploiActuel actuel = new EmploiActuel();
				Scene actuelscene = actuel.getScene(PrimaryStage);	
				PrimaryStage.setScene(actuelscene);
			}

		});
		//le contenu du creneau 
		GridPane formulaire=new GridPane();
		Label message1=new Label("Date debut");
		DatePicker datePicker = new DatePicker();
		formulaire.add(message1, 0, 0); //on place a la ligne 0 colonne O
		formulaire.add(datePicker, 1, 0); 
		Label message2=new Label("Date fin");
		DatePicker datePicker2 = new DatePicker();
		formulaire.add(message2, 0, 1);  
		formulaire.add(datePicker2, 1, 1); 
		Label message3=new Label("heure debut");
		TextField ecrit=new TextField();
		formulaire.add(message3, 0, 2); 
		formulaire.add(ecrit, 1, 2);
		Label message4=new Label("heure fin");
		TextField ecrit2=new TextField();
		formulaire.add(message4, 0, 3);  
		formulaire.add(ecrit2, 1, 3); 
		Label message5=new Label("Spécialité");
		ChoiceBox<String> choix=new ChoiceBox<>();
		formulaire.add(message5, 0, 4);
		formulaire.add(choix, 1, 4); 
		Label creneaunom=new Label("Nom du creneau");
		TextField nom=new TextField();
		formulaire.add(creneaunom, 0, 5);
		formulaire.add(nom, 1, 5);
		Label message6= new Label("les containtes violées");
		ComboBox<String> comboBox = new ComboBox<>();
		Label message7=new Label("le id emploi");
		TextField id=new TextField();
		formulaire.add(message7,0,7);
		formulaire.add(id,1,7);
		//interaction avec la bdd pour specialite et contraintes
		try  {
			String query = "SELECT nom_specialite FROM specialite";  
			PreparedStatement stmt = conn1.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				choix.getItems().add(rs.getString("nom_specialite"));
			}
			String query1 = "SELECT explication_contrainte FROM contraintes";  
			PreparedStatement stmt1 = conn1.prepareStatement(query1);
			ResultSet rs1 = stmt1.executeQuery();

			while (rs1.next()) {
				comboBox.getItems().add(rs1.getString("explication_contrainte"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		formulaire.add(message6, 0, 6);
		formulaire.add(comboBox, 1, 6);
		formulaire.getStyleClass().add("grid-pane");
		borderPane.setCenter(formulaire);
		Button soumission = new Button("creer creneau");
		formulaire.add(soumission, 1, 8);
		// envoie du nouveau creneau a la bdd
		soumission.setOnAction(e -> {

			LocalDate dateDebut = datePicker.getValue();
			String nomcrn=nom.getText();
			LocalDate dateFin = datePicker2.getValue();
			LocalTime heureDebut = LocalTime.parse(ecrit.getText());
			LocalTime heureFin = LocalTime.parse(ecrit2.getText());
			int idEmploi = Integer.parseInt(id.getText());
			//ca ne marche pas  sans la conversion le constructeur a les types basiques
			java.sql.Date sqlDateDebut =  java.sql.Date.valueOf(dateDebut);
			java.sql.Date sqlDateFin =  java.sql.Date.valueOf(dateFin);
			java.sql.Time sqlHeureDebut = java.sql.Time.valueOf(heureDebut);
			java.sql.Time sqlHeureFin = java.sql.Time.valueOf(heureFin);
			Creneau nwcreneau=new Creneau(sqlDateDebut,sqlDateFin,sqlHeureDebut,sqlHeureFin,nomcrn,idEmploi);
			CreneauDAO creneauDao = new CreneauDAO();
			creneauDao.ajouter(nwcreneau);
		});
		Scene scene = new Scene(borderPane, 800, 600);
		scene.getStylesheets().add("/style.css");  
		return scene ;	  	    
	}
}	 


