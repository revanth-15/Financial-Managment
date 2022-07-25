package proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AccountLogin extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Accountant");

        //creating all nodes
        Label lb = new Label("Welcome");
        Button add = new Button("Add Student");
        Button remove = new Button("Remove Student");
        Button edit = new Button("Edit");
        Button due = new Button("View Due Students");
        Button log = new Button("Logout");
        GridPane gp= new GridPane();

        //setting all the properties
        gp.setVgap(25);
        gp.setHgap(26);
        gp.setAlignment(Pos.CENTER);

        //adding nodes tp gridpane
        gp.addRow(0,lb);
        gp.addRow(1,add,remove);
        gp.addRow(2,edit,due);
        gp.addRow(3,log);

        Details stud = new Details();
        //adding events to the button
        //add students button
        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ad)
            {
                stud.template(stage);
                stud.top.setText("Adding Student");
                stud.any.setText("Next");
                
                //for nxt button event
                stud.any.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent adding)
                    {
                        stud.template(stage, 1);
                        stud.any.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent add)
                            { 
                                String sql=("insert into students values("+stud.getdetails(1)+");");
                                // System.out.println(sql);
                                stud.createcon(sql);
                                stud.dialog("Successfully added");
                                try {
                                    start(stage);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                //for back button
                stud.previous.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent back)
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
                stud.template(stage);
                stud.tp.getChildren().removeAll(stud.password,stud.pass,stud.age,stud.enter_age,stud.email,stud.enter_email);
                stud.tp.getChildren().removeAll(stud.phone_no,stud.enter_phone,stud.address,stud.enter_add);
                stud.any.setText("Remove");
                stud.top.setText("Removing Student");
                stud.any.setOnAction(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent removing) {
                        String sql = "delete from students where rollno = "+Integer.parseInt(stud.enter_id.getText())+";";
                        stud.createcon(sql);
                        stud.dialog("Successfully Deleted");
                        try {
                            start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }     
                });
                //for back button
                stud.previous.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent back)
                    {
                        try {
                            start(stage);
                        } catch (Exception e) {
                        }
                    }
                });

            }
        });

        //for editing the details of students
        edit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent edit)
            {
                stud.template(stage);
                stud.top.setText("Enter Roll no to get Details");
                stud.any.setText("Get details");
                stud.previous.setText("Update");

                //for getdetails button
                stud.any.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent details) {
                        String sql = "select * from students where rollno = "+Integer.parseInt(stud.enter_id.getText())+";";
                        // System.out.println(sql);
                        try {
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","nandan");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            if(!rs.isBeforeFirst())
                            {
                                stud.dialog("Enter a valid Id");
                            }
                            while(rs.next())
                            {
                                stud.enter_name.setText(rs.getString(2));
                                stud.enter_age.setText(rs.getString(4));
                                stud.enter_email.setText(rs.getString(5));
                                stud.enter_phone.setText(rs.getString(6));
                                stud.enter_add.setText(rs.getString(7));
                            }
                            con.close();
                            
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        
                    }
                    
                });
                
                stud.previous.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent update)
                    {
                        String str="update students set name=\""+stud.enter_name.getText()+"\",age ="+stud.enter_age.getText()+",email=\""+stud.enter_email.getText()+"\",phoneno="+stud.enter_phone.getText()+",address=\""+stud.enter_add.getText()+"\" where rollno="+Integer.parseInt(stud.enter_id.getText())+";";
                        System.out.println(str);
                        
                        stud.createcon(str);
                        stud.dialog("Successfully updated");
                        try {
                            start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        //for view due students
        due.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent due)
            {
                stud.createcon("select * from students where due > 0;", 2);
                //need to display it in list format check that
                
            }

        });

        //for logout button
        log.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                Account back = new Account();
                try {
                    back.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        });

        //adding to scenes
        Scene scene = new Scene(gp,500,300);
        stage.setScene(scene);
        stage.show();

    }


}
