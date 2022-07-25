package proj;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

//import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

abstract class sql{
    abstract void createcon(String sql);
    abstract int createcon(String sql,int a);
}


class Details extends sql{
    int n=10; 
    Label top = new Label();
    Label id= new Label("Enter Id");
    Label name = new Label("Name");
    Label password = new Label("Password");
    Label age = new Label("Age");
    Label email = new Label("Email");
    Label phone_no = new Label("Phone no");
    Label address = new Label("Address");
    Label course = new Label("Course");
    Label fee = new Label("Fee");
    Label paid = new Label("Paid");
    Label due = new Label("Due");
    TextArea enter_add= new TextArea();
    TextField enter_id = new TextField();
    TextField enter_age = new TextField();
    TextField enter_email = new TextField();
    TextField enter_phone = new TextField();
    TextField enter_name = new TextField();
    TextField enter_course = new TextField();
    Text enter_due = new Text();
    TextField enter_fee = new TextField();
    TextField enter_paid = new TextField();
    PasswordField pass = new PasswordField();
    GridPane tp = new GridPane();
    Button any = new Button();
    Button previous = new Button("Back");
 
    @Override
    void createcon(String sql)
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","nandan");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    int createcon(String sql,int a)
    {
        String user;
        String p;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","nandan");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(a==1)
            {    
                while(rs.next())
                {
                    user=enter_id.getText();
                    p=pass.getText();
                    if(user.equals(rs.getString(a+1)) && p.equals(rs.getString(a+2)))
                    {
                        con.close();
                        return 1;
                    }
                }
            }
            //if a== 2 for students
            if(a==2)
            {
                while(rs.next())
                {
                    System.out.println(rs.getString(1)+"\t"+rs.getString(2));
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    protected void template(Stage second)
    {
        
        // setting properties
        top.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        enter_add.setMaxHeight(25);
        enter_add.setWrapText(true);

        
        tp.setAlignment(Pos.CENTER);
        tp.setHgap(15);tp.setVgap(15);
        tp.addRow(0, top);
        tp.addRow(1, id,enter_id);
        tp.addRow(2,name,enter_name);
        tp.addRow(3,password,pass);
        tp.addRow(4,age,enter_age);
        tp.addRow(5,email,enter_email);
        tp.addRow(6,phone_no,enter_phone);
        tp.addRow(7,address,enter_add);
        tp.addRow(8,any,previous);
        Scene scene = new Scene(tp,700,500);
        second.setScene(scene);
        second.show();
    }
    
    protected void template(Stage nxt,int a)
    {
        any.setText("Add");
        tp.getChildren().removeAll(any,previous);
        tp.addRow(9,course,enter_course);
        tp.addRow(10,fee,enter_fee);
        tp.addRow(11,paid,enter_paid);
        tp.addRow(12, any);
    }

    void dialog(String dialog)
    {
        
        Dialog<String> dia = new Dialog<String>();
        //dia.setTitle("error");
        dia.setContentText(dialog);
        dia.getDialogPane().getButtonTypes().add(new ButtonType("ok",ButtonData.OK_DONE));
        dia.show();
    }

    String getdetails()
    {
        return(enter_id.getText()+",\""+enter_name.getText()+"\",\""+pass.getText()+"\","+Integer.parseInt(enter_age.getText())+",\""+enter_email.getText()+"\","+Integer.parseInt(enter_phone.getText())+",\""+enter_add.getText()+"\"");
    }

    String getdetails(int a)
    {
        int temp=Integer.parseInt(enter_fee.getText())-Integer.parseInt(enter_paid.getText());
        return(enter_id.getText()+",\""+enter_name.getText()+"\",\""+pass.getText()+"\","+Integer.parseInt(enter_age.getText())+",\""+enter_email.getText()+"\","+Integer.parseInt(enter_phone.getText())+",\""+enter_add.getText()+"\","+Integer.parseInt(enter_fee.getText())+","+Integer.parseInt(enter_paid.getText())+","+temp);
    }

}

