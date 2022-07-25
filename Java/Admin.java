package proj;


import java.sql.*;
//import com.mysql.cj.xdevapi.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Admin extends Application{

    public static void main(String[] args) {
        launch(args);
    }
   
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Login");

        //declaring all the textfields and labels
        Label ad= new Label("Admin");
        Label pass = new Label("Password");
       // Text show = new Text();
        TextField a = new TextField();
        PasswordField p = new PasswordField();
        Button log = new Button("Login");
        //end of creating all nodes
        
        //button for esc
        Button esc = new Button();
        esc.setCancelButton(true);

        
        HBox hb = new HBox();
       // HBox down = new HBox();
        hb.getChildren().add(esc);
        
        GridPane tp = new GridPane();
        tp.setMinSize(500, 400);
        //down.setPrefWidth(300);
        tp.setPadding(new Insets(10,10,10,10));
        tp.setVgap(5);
        tp.setHgap(5);
        
        //adding all nodes
        tp.add(hb,0,0);
        tp.add(ad,10,8);//10 represents the horizontal space  8 represents the vertical spacing
        tp.add(a,15,8);//(node,horizontal,verical)
        tp.add(pass,10,12);//label
        tp.add(p,15,12);//textfield
        tp.add(log,13,15);//button
       // tp.add(show,0,17);
        //end of adding all nodes
        
        //event for button

        //for login button
            log.setOnAction(new EventHandler<ActionEvent>(){

                int value;
                @Override
                public void handle(ActionEvent l) 
                {
                    String user,pass;
                    String user_field=a.getText(),pass_field=p.getText();
                    try{  
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","nandan");
                            java.sql.Statement stmt=con.createStatement();
                            ResultSet rs=stmt.executeQuery("select * from admin;");  
                            //System.out.println(rs);
                            while(rs.next())
                            {
                                user=rs.getString(1);pass=rs.getString(2);
                                if(user.equals(user_field) && pass.equals(pass_field))
                                {   value=1;
                                    break;
                                }
                                else{
                                    value=0;//0 means user not found
                                    // System.out.println("Invalid username or password");
                                }
                            }
                            con.close();
                        }
                        catch(Exception e){ System.out.println(e);}  
                        Details d =new Details();
                        if(value==1)
                        {  
                            d.dialog("Login approved");  
                            try {
                                Adminlogin al = new Adminlogin();
                                al.start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            d.dialog("INvalid username or password");
                        }
                    }
            });

        //for esc button
            esc.setOnAction(new EventHandler<ActionEvent>(){
            
                @Override
                public void handle(ActionEvent eve) {
                    App ret = new App();
                        try {
                            ret.start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
                
            });
        
        //end button


        Scene scene = new Scene(tp);
        stage.setScene(scene);
        stage.show();
    }
}
