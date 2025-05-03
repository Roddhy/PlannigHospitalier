package projet_hospitalier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * 
 *cette classe est la vue principale du javafx
 */
public class Accueil extends Application  {
  
	@Override
	public void start(Stage primaryStage) throws Exception {
		// creer le menu 
		 BorderPane borderPane = new BorderPane();
		 HBox menu=new HBox();
		 Button btn1 = new Button("Liste du personnel");
	     Button btn2 = new Button("créer un créneau");
	     Button btn3=new Button("Accueil");
	     Button btn4=new Button("L'emploi hebdomadaire");
	     menu.getChildren().addAll(btn1,btn2,btn3,btn4);
	     menu.getStyleClass().add("hbox");
	     borderPane.setTop(menu);
	     
		// le message d'accueil 
	     VBox centre=new VBox(10);
        Label labeldebienvenue = new Label("Bienvenue sur HealerPlan");
        ImageView logo= new ImageView(new Image(getClass().getResourceAsStream("/HEALERPLAN 22).png")));
        centre.getChildren().addAll(logo,labeldebienvenue);  
        centre.getStyleClass().add("vbox");
        labeldebienvenue.setId("labeldebienvenue"); //style unique *
        logo.setId("logo");
        borderPane.setCenter(centre);
   
        
        //l'icone sur la fenetre 
         Image icon = new Image(getClass().getResourceAsStream("/HEALERPLAN.png")); 
         primaryStage.getIcons().add(icon);
         
        
        // Créer une Scene  
        Scene scene = new Scene(borderPane, 1000, 600); 
        scene.getStylesheets().add("/style.css");
        
        // Configurer la fenetre 
        primaryStage.setTitle("HealerPlan");
        primaryStage.setScene(scene);
        primaryStage.show();
		
        // les evenements
        btn1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) { 
				 ListePersonnelle employeListe = new ListePersonnelle(); // on cree une instance de la classe liste
		         Scene listeScene = employeListe.getScene(primaryStage);
		         primaryStage.setScene(listeScene); 
			}
           
        });
        btn2.setOnAction( new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent event) {
        		  CreerCreneau cc= new CreerCreneau();
        		  Scene creneauScene = cc.getScene(primaryStage);
        		  primaryStage.setScene(creneauScene);
        }});
        btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				 Emploiperso actuel = new Emploiperso();
		         Scene actuelscene = actuel.getScene(primaryStage);	
		         primaryStage.setScene(actuelscene);
			}
            
        });
        
        }
	/**
	 * le main pour faire fonctionner le fx 
	 * @param args
	 */
	public static void main(String[] args) {
		 launch(args);
	}
}
