package rocks.zipcode.atm;

import javafx.scene.layout.Border;
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
import javafx.scene.text.Text;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    Stage window;
    Scene scene1, scene2, scene3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        window = mainStage;
        Bank bank = new Bank();
        FlowPane flowpane = new FlowPane();
        CashMachine cashMachine = new CashMachine(bank);

        /*Scene1 * * * * * * * * LOGIN OR CREATE ACCOUNT PAGE * * * * * * * * * * * * * */
        /*Scene1 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        TextField textField = new TextField();
        FlowPane flowpane1 = new FlowPane();
        Button btnLogin = new Button("Login");
        TextArea areaInfo = new TextArea();
        btnLogin.setOnAction(a -> {
            window.setScene(scene3);
            int id = Integer.parseInt(textField.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnCreateAccount = new Button("Create Account");
        btnCreateAccount.setOnAction(e -> window.setScene(scene2));

        VBox vbox = new VBox(10);
        scene1 = new Scene (vbox, 600, 600);

        flowpane1.getChildren().add(btnLogin);
        flowpane1.getChildren().add(btnCreateAccount);

        vbox.getChildren().addAll(flowpane1, textField, areaInfo);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


        /*Scene2 * * * * * * * * CREATE ACCOUNT PAGE * * * * * * * * * * * * * * * * * * */
        /*Scene2 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        FlowPane flowPane2 = new FlowPane();
        Button btnCreate = new Button("Submit");
        Button btnSubmit = new Button("Go Back");
        btnSubmit.setOnAction(e -> window.setScene(scene1));

        Text newName = new Text();
        newName.setText("Please enter your name:");
        TextField nameField = new TextField();

        Text newEmail = new Text();
        newEmail.setText("Please enter your email:");
        TextField emailField = new TextField();

        Text newPin = new Text();
        newPin.setText("Please enter your desired pin:");
        TextField pinField = new TextField();


        VBox vbox2 = new VBox();
        scene2 = new Scene(vbox2, 600, 600);
        flowPane2.getChildren().add(btnCreate);
        flowPane2.getChildren().add(btnSubmit);
        vbox2.getChildren().addAll(newName, nameField, newEmail, emailField, newPin, pinField, flowPane2);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


        /*Scene3 * * * * * * * * * * MAIN PAGE AFTER LOGIN * * * * * * * * * * * * * * * */
        /*Scene3 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        FlowPane flowPane3 = new FlowPane();
        Text title = new Text();
        title.setText("This is where all of our main bank processes will go");

        Button btnSubmitCredentials = new Button("The");
        Button btnGoBack = new Button("testAgain");
        btnGoBack.setOnAction(e -> window.setScene(scene1));


        VBox vbox3 = new VBox();
        flowPane3.getChildren().add(btnSubmitCredentials);
        flowPane3.getChildren().add(btnGoBack);
        vbox3.getChildren().addAll(title, flowPane3);
        scene3 = new Scene(vbox3, 600, 600);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


        mainStage.setScene(scene1);
        mainStage.show();

    }

}
