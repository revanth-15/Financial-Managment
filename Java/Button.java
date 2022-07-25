package proj;

// import java.io.IOException;
// public class Button extends HttpServlet{
//     private static final long serialVersion;

//     public Servlet2()
//     {
//         super();
//     }

//     protected void doGet(hyypServletRequest request,httpServletResponse response)//something else is there is here
//     {
//         response.getWriter().append("Served at: ").append(request.getContextPath());
//     }

//     protected void doPost(HttpServletRequest request,HttpServletResponse response)
//     {
//         doGet(request,response);
//         response.setContentType("text/html");
//         PrintWriter outt
//     }
// }

/*import javafx.application.Application;
//import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


class Person{
    private String fname = null;
    private String lname = null;
    public Person()
    {

    }
    public Person(String fname,String lname)
    {
        this.fname = fname;
        this.lname = lname;
    }
}

public class Button extends Application{

    private TableView<Person> table = new TableView<>();
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        //vbox.setPadding(new Insets());
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
    
}*/
