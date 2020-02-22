package rocks.zipcode.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        window = mainStage;


        /*Scene1 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        TextField textField = new TextField();
        FlowPane flowpane = new FlowPane();
        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(e -> window.setScene(scene2));
        Button btnCreateAccount = new Button("Create Account");
        TextArea areaInfo = new TextArea();

        VBox vbox = new VBox(10);
        scene1 = new Scene (vbox, 600, 600);

        flowpane.getChildren().add(btnLogin);
        flowpane.getChildren().add(btnCreateAccount);
        vbox.getChildren().addAll(textField, flowpane, areaInfo, btnLogin, btnCreateAccount);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


        /*Scene2 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        Button btnCreate = new Button("Create");
        Button btnSubmit = new Button("Go Back");
        btnSubmit.setOnAction(e -> window.setScene(scene1));
        TextField idField = new TextField();
        TextField passwordField = new TextField();


        StackPane vbox2 = new StackPane();
        flowpane.getChildren().add(btnCreate);
        flowpane.getChildren().add(btnSubmit);
        vbox2.getChildren().addAll(flowpane, idField, passwordField);
        scene2 = new Scene(vbox2, 600, 600);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


        mainStage.setScene(scene1);
        mainStage.show();

    }

}
