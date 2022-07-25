package proj;
//import proj.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//project fee report

public class App  extends Application{

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception {
      stage.setTitle("Login");
      Pane root = new Pane();
      VBox vb = new VBox(25);

      //Button
      Button bt1 = new Button("Admin");
      Button bt2 = new Button("Accountant");
      bt1.setLayoutX(60);
      bt1.setLayoutY(60);
      bt2.setLayoutX(60);
      bt2.setLayoutY(69);
      bt1.setPrefSize(70, 70);
      bt2.setPrefSize(70, 70);
      //end of button

      vb.getChildren().addAll(bt1,bt2);
      vb.setLayoutX(50);
      root.getChildren().add(vb);
      //alignment not working
      //TilePane.setAlignment(vb, Pos.BOTTOM_CENTER);

      //adding events to button
      bt1.setOnAction(new EventHandler<ActionEvent>(){

         @Override
         public void handle(ActionEvent as) {
            // calling another class from the same package
            //implement this method in another java file
            //Stage ad = new Stage();
            Admin second = new Admin();
            try {
               second.start(stage);//calling another function in another file but in same package
            } catch (Exception e) {
               e.printStackTrace();
            }
            
         }
         
      });

      bt2.setOnAction(new EventHandler<ActionEvent>(){
         @Override
         public void handle(ActionEvent acc)
         {
            Account sec = new Account();
            try {
               sec.start(stage);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });

      //scene 
      Scene scene = new Scene(root,300,180); 
      stage.setScene(scene);
      stage.show();
   }
}