package rocks.zipcode.atm;

import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Window;
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

    private TextField entry = new TextField();
    private TextField num = new TextField();
    private TextField idInfo = new TextField();
    private TextField name = new TextField();
    private TextField email = new TextField();
//    private TextField balance = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    private Label instructions = new Label("Enter Dollar Amount:");
    private Label accountData = new Label("Account Information");

    public static void display(){
        Stage password = new Stage();
        password.initModality(Modality.APPLICATION_MODAL);
        password.setTitle("Password");
        password.setWidth(250);
        password.setHeight(100);
        VBox layoutPass = new VBox(10);
        TextField enterPass = new TextField();
        layoutPass.getChildren().add(enterPass);
        Scene pass = new Scene(layoutPass);
        password.setScene(pass);
        password.showAndWait();}


    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();
        TextArea accountInfo = new TextArea();
        TextArea warnings = new TextArea();
        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setDisable(true);
        Button btnDeposit = new Button("Deposit");
        btnDeposit.setDisable(true);
        Button btnGetBalance = new Button("Get Balance");
        btnGetBalance.setDisable(true);
        Button btnSubmit = new Button("Log In");
        Button btnExit = new Button("Log out");
        btnExit.setDisable(true);

        //Login
        btnSubmit.setOnAction(e ->
        {
            int id = Integer.parseInt(entry.getText());
            cashMachine.login(id);
            idInfo.setText(cashMachine.toString());
            name.setText(cashMachine.nameInfo());
            email.setText(cashMachine.emailInfo());
//            balance.setText(cashMachine.balanceInfo());
            btnDeposit.setDisable(false);
            btnGetBalance.setDisable(false);
            btnWithdraw.setDisable(false);
            btnExit.setDisable(false);


        });


        //Deposit
            btnDeposit.setOnAction(e -> {
            Double amount = Double.parseDouble(num.getText());
            cashMachine.deposit(amount);
            areaInfo.setText("You deposited $"+num.getText());
            num.clear();
        });


        //Withdraw
        btnWithdraw.setOnAction(e -> {
            Double amount = Double.parseDouble(num.getText());
            cashMachine.withdraw(amount);
            areaInfo.setText("You withdrew $"+num.getText());
            warnings.setText(cashMachine.toString());

            accountInfo.setText(cashMachine.balanceInfo());
            num.clear();

        });


        //Logout
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            accountInfo.clear();
            idInfo.clear();
            name.clear();
            email.clear();
            areaInfo.setText("Thank you for using our bank");
        });

        //balance
        btnGetBalance.setOnAction(e -> {
            int id = Integer.parseInt(entry.getText());
            cashMachine.getBalance(id);

            accountInfo.setText(cashMachine.balanceInfo());
        });

        FlowPane flowpane = new FlowPane();
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnGetBalance);

        flowpane.getChildren().add(btnExit);

        vbox.getChildren().addAll(entry,flowpane,instructions,num,accountData,idInfo,name,email, areaInfo,warnings,accountInfo);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
