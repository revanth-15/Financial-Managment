package proj;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Account extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Accountant");
        Button back = new Button("Back");
        Details acc = new Details();
        acc.template(stage);
        acc.top.setText("Accountant Login");
        acc.any.setText("Login");
        acc.id.setText("name");
        //removing e=nodes from the template
        acc.tp.getChildren().removeAll(acc.name,acc.enter_name,acc.age,acc.enter_age);
        acc.tp.getChildren().removeAll(acc.email,acc.enter_email,acc.phone_no,acc.enter_phone);
        acc.tp.getChildren().removeAll(acc.address,acc.enter_add,acc.previous);
        acc.tp.addRow(8,back);

        //adding events for button
        acc.any.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent log)
            {
                int check;
                check=acc.createcon("select * from accountant;",1);
                if(check == 1)
                {
                    acc.dialog("Login Successfull");
                    AccountLogin agl = new AccountLogin();
                    try {
                        agl.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    acc.dialog("Invalid username or password");
                }
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent bac)
            {
                App ret = new App();
                try {
                    ret.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
