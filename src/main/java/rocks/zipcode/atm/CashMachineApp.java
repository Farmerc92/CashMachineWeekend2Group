package rocks.zipcode.atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import rocks.zipcode.atm.bank.AccountData;
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

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    ComboBox comboBox;
    Bank bank = new Bank();
    Integer globalId;
    Integer globalPin;
    Stage mainStage;
    CashMachine cashMachine = new CashMachine(bank);
    ObservableList<String> accountOptions = FXCollections.observableArrayList("Basic", "Premium");
    private static final Logger LOGGER = Logger.getLogger(CashMachineApp.class.getName());

    public Parent defaultPage() {
        Text inputIdNumber = new Text();
        inputIdNumber.setText("Please enter your Id number below.");

        TextField textField = new TextField();
        TextField pinField = new TextField();
        FlowPane flowPane1 = new FlowPane();
        flowPane1.setHgap(10);
        Button btnLogin = new Button("Login");
        TextArea areaInfo = new TextArea();
        btnLogin.setOnAction(e -> {
            int id = Integer.parseInt(textField.getText());
            //int pin = Integer.parseInt(pinField.getText());
            cashMachine.login(id);
            if (!Bank.getAccounts().containsKey(id)) {
                areaInfo.setText("No account with those credentials.\n" +
                        "Please try again or create a new account.");
            } else {
                mainStage.setScene(new Scene(LoggedInPage()));
                areaInfo.clear();
            }
        });

        Button btnCreateBasicAccount = new Button("Create Account");
        btnCreateBasicAccount.setOnAction(e -> {
            mainStage.setScene(new Scene(createAccountPage()));
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exitProgram();
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 15, 10, 15));

        btnCreateBasicAccount.setStyle("-fx-background-color: #12FFA5");
        btnExit.setStyle("-fx-background-color: #12FFA5");
        btnLogin.setStyle("-fx-background-color: #12FFA5");

        flowPane1.getChildren().add(btnLogin);
        flowPane1.getChildren().add(btnCreateBasicAccount);
        flowPane1.getChildren().add(btnExit);

        vbox.getChildren().addAll(
                flowPane1,
                inputIdNumber,
                textField,
                pinField,
                areaInfo);
        return vbox;
    }

    public Parent createAccountPage() {
        Text selectAccountType = new Text();
        selectAccountType.setText("Please choose an account type using the dropdown menu.");
        final ComboBox comboBox = new ComboBox(accountOptions);
        FlowPane flowPane2 = new FlowPane();
        flowPane2.setHgap(10);
        Text basicWelcome = new Text();
        TextArea areaField1 = new TextArea();
        basicWelcome.setText("Welcome to your personalized Basic account creation.");


        Button btnSubmit = new Button("Go Back To Login");
        btnSubmit.setOnAction(e -> {mainStage.setScene(new Scene(defaultPage()));});

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

        Button btnCreate = new Button("Submit");
        btnCreate.setOnAction(e -> {
            String newNameString = nameField.getText();
            String newEmailString = emailField.getText();
            Integer newPinInt = Integer.parseInt(pinField.getText());
            Integer newDepositAmountInt = Integer.parseInt(depositField.getText());
            globalId = bank.createAccount(newNameString, newEmailString, newPinInt, newDepositAmountInt);
            areaField1.setText("Your new ID is : " + globalId);
            btnCreate.setDisable(true);
        });

        VBox vbox2 = new VBox(10);
        vbox2.setPadding(new Insets(10, 15, 10, 15));

        btnCreate.setStyle("-fx-background-color: #12FFA5");
        btnSubmit.setStyle("-fx-background-color: #12FFA5");

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
                flowPane2,
                areaField1);
        return vbox2;
    }


    public Parent LoggedInPage() {

        FlowPane flowPane3 = new FlowPane();
        flowPane3.setHgap(10);
        Text title = new Text();
        title.setText("Welcome to your account!");
        TextArea accountInfoDisplay2 = new TextArea();
        accountInfoDisplay2.setText(cashMachine.toString());
        TextField actionField = new TextField();

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(actionField.getText());
            cashMachine.deposit(amount);
            accountInfoDisplay2.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(actionField.getText());
            cashMachine.withdraw(amount);
            accountInfoDisplay2.setText(cashMachine.toString());
        });

        Button btnLogOut = new Button("Log Out");
        btnLogOut.setOnAction(e -> mainStage.setScene(new Scene(defaultPage())));

        VBox vbox3 = new VBox();

        btnDeposit.setStyle("-fx-background-color: #12FFA5");
        btnLogOut.setStyle("-fx-background-color: #12FFA5");
        btnWithdraw.setStyle("-fx-background-color: #12FFA5");

        flowPane3.getChildren().add(btnDeposit);
        flowPane3.getChildren().add(btnWithdraw);
        flowPane3.getChildren().add(btnLogOut);
        flowPane3.setPadding(new Insets(10, 10, 10, 0));
        vbox3.getChildren().addAll(title, actionField, flowPane3, accountInfoDisplay2);
        vbox3.setPadding(new Insets(10, 15, 10, 15));
        return vbox3;
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        try {
            bank.loadBankAccounts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mainStage = mainStage;
        mainStage.setScene(new Scene(defaultPage()));
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
