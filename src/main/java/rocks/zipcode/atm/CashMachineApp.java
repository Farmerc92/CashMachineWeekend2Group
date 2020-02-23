package rocks.zipcode.atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
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
    ComboBox comboBox;
    ObservableList<String> accountOptions = FXCollections.observableArrayList("Basic", "Premium");
    Integer globalId;

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
        FlowPane flowPane1 = new FlowPane();
        flowPane1.setHgap(10);
        Button btnLogin = new Button("Login");
        TextArea areaInfo = new TextArea();
        btnLogin.setOnAction(a -> {
            mainStage.setScene(scene3);
            int id = Integer.parseInt(textField.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnCreateBasicAccount = new Button("Create Account");
        btnCreateBasicAccount.setOnAction(e -> { window.setScene(scene2); });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> { cashMachine.exitProgram(); });

        btnCreateBasicAccount.setOnAction(e -> mainStage.setScene(scene2));

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 15, 10, 15));
        scene1 = new Scene (vbox, 600, 600);

        flowPane1.getChildren().add(btnLogin);
        flowPane1.getChildren().add(btnCreateBasicAccount);
        flowPane1.getChildren().add(btnExit);

        vbox.getChildren().addAll(
                flowPane1,
                textField,
                areaInfo);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


        /*Scene2 * * * * * * * * CREATE ACCOUNT PAGE * * * * * * * * * * */
        /*Scene2 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        Text selectAccountType = new Text();
        selectAccountType.setText("Please choose an account type using the dropdown menu.");
        final ComboBox comboBox = new ComboBox(accountOptions);
        FlowPane flowPane2 = new FlowPane();
        flowPane2.setHgap(10);
        Text basicWelcome = new Text();
        basicWelcome.setText("Welcome to your personalized Basic account creation.");

        Button btnCreate = new Button("Submit");
        btnCreate.setOnAction(e -> {mainStage.setScene(scene1);});
        Button btnSubmit = new Button("Go Back To Login");
        btnSubmit.setOnAction(e -> mainStage.setScene(scene1));

        Text newName = new Text();
        newName.setText("Please enter your name:");
        TextField nameField = new TextField();

        Text newEmail = new Text();
        newEmail.setText("Please enter your email:");
        TextField emailField = new TextField();

        Text newPin = new Text();
        newPin.setText("Please enter your desired pin:");
        TextField pinField = new TextField();

        Text newDepositAmount = new Text();
        newDepositAmount.setText("Amount To Deposit: ");
        TextField depositField = new TextField();


        VBox vbox2 = new VBox();
        scene2 = new Scene(vbox2, 600, 600);
        vbox2.setPadding(new Insets(10, 15, 10, 15));
        flowPane2.getChildren().add(btnCreate);
        flowPane2.getChildren().add(btnSubmit);
        flowPane2.setPadding(new Insets(10, 10, 10, 0));
        vbox2.getChildren().addAll(
                basicWelcome,
                selectAccountType,
                comboBox,
                newName,
                nameField,
                newEmail,
                emailField,
                newPin,
                pinField,
                newDepositAmount,
                depositField,
                flowPane2);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


        /*Scene3 * * * * * * * * * * MAIN PAGE AFTER LOGIN * * * * * * * * * * * * * * * */
        /*Scene3 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        FlowPane flowPane3 = new FlowPane();
        flowPane3.setHgap(10);
        Text title = new Text();
        title.setText("Welcome to your account!");
        TextArea accountInfoDisplay = new TextArea();

        TextField actionField = new TextField();
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnGoBack = new Button("Log Out");
        Button btnRefresh = new Button("Refresh");
        btnGoBack.setOnAction(e -> mainStage.setScene(scene1));
        btnRefresh.setOnAction(a -> {
            int id = Integer.parseInt(actionField.getText());
            cashMachine.login(id);

            accountInfoDisplay.setText(cashMachine.toString());});

        VBox vbox3 = new VBox();
        flowPane3.getChildren().add(btnDeposit);
        flowPane3.getChildren().add(btnWithdraw);
        flowPane3.getChildren().add(btnGoBack);
        flowPane3.setPadding(new Insets(10, 10, 10, 0));
        vbox3.getChildren().addAll(title, actionField, flowPane3, accountInfoDisplay);
        vbox3.setPadding(new Insets(10, 15, 10, 15));
        scene3 = new Scene(vbox3, 600, 600);
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


        mainStage.setScene(scene1);
        mainStage.show();

    }

}
