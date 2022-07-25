//to do add logout and view student details button and respecctive events to it
package proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



class accounts{

}


public class Adminlogin extends Application{

    public static void main(String[] args) {
        launch(args);    
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Admin");

        //creating all the nodes
        GridPane rt = new GridPane();
        Label name = new Label("Adding accountant");
        name.setFont(new Font("Arial", 24));
        /*Label name = new Label("Username");
        Label pa = new Label("Password");
        TextField username = new TextField();
        PasswordField pass = new PasswordField();*/
        Button add = new Button("Add");
        Button remove = new Button("Remove");
        Button logout = new Button("Logout");
        Button view = new Button("View");
        //end of creating all nodes

        //setting all properties
        rt.setVgap(25);
        rt.setHgap(26);
        rt.setAlignment(Pos.CENTER);
        //end of setting all properties

        //adding all the nodes to the root
        rt.addRow(0, name);
        rt.addRow(1, add,remove);
        rt.addRow(2,view,logout);
        //end of adding

        //button events
        //connection opened and closed 
        //for adding user
        add.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent ad) {
                Details adding = new Details();
                adding.template(stage);
                adding.top.setText("Adding accountant");
                adding.any.setText("ADD");
                adding.any.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent ad1)
                    {
                        String sql = adding.getdetails();
                        //String sql = "insert into accountant values(\""+adding.enter_id.getText()+"\",\""+adding.enter_name.getText()+"\",\""+adding.pass.getText()+"\",\""+adding.enter_age.getText()+"\",\""+adding.enter_email.getText()+"\",\""+adding.enter_phone.getText()+"\",\""+adding.enter_phone.getText()+"\");";
                       try {
                           Details d = new Details();
                           d.createcon("insert into accountant values("+sql+");");
                            d.dialog("Successfully added record");
                           //System.out.println("Succesfully added");
                           start(stage);
                           
                       } catch (Exception e) {
                           e.printStackTrace();
                       } 
                    }
                });
                
                adding.previous.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent bakc)
                    {
                        try {
                            start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        //for remove button
        remove.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent rem)
            {
                Details d = new Details();
                d.template(stage);
                d.top.setText("Removing Accountant");
                d.any.setText("Remove");
                d.tp.getChildren().removeAll(d.pass,d.password,d.age,d.enter_age);
                d.tp.getChildren().removeAll(d.email,d.enter_email,d.phone_no,d.enter_phone);
                d.tp.getChildren().removeAll(d.address,d.enter_add);

                //for removing button event
                d.any.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent removing) { 
                        // System.out.println("delete from accountant where id="+Integer.parseInt(d.enter_id.getText())+";");
                        String sql = "delete from accountant where id= "+Integer.parseInt(d.enter_id.getText())+ ";";
                        d.createcon(sql);
                        d.dialog("Successfully deleted reocrd");
                        //System.out.println("Successfully deleted record "+d.enter_id.getText());
                        try {
                            start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    
                }); 
            
                //for going back to presvious menu
                d.previous.setOnAction(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent back) {
                        try {
                            start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    
                });
            }
        });
        
        view.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent vw)
            {
                String sq = "Select * from accountant;";
                try {
                    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","nandan");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sq);
                    while(rs.next())
                    {
                        System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getInt(6));
                    }
                    con.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent log)
            {
                Details d = new Details();
                d.dialog("Logout Successful");
                Admin back = new Admin();
                try {
                    back.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //end of button events

        //adding scene and setting it
        Scene scene = new Scene(rt,600,400);
        stage.setScene(scene);
        stage.show();
    }
    
}
