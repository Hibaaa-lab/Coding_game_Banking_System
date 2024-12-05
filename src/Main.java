import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private SavingsAccount savingsAccount;
    private CheckingAccount checkingAccount;
    private BusinessAccount businessAccount;
    private BankingOperations currentAccount; // Keeps track of the currently selected account

    @Override
    public void start(Stage primaryStage) {
        // Initialize accounts
        initializeAccounts();

        // Welcome Label
        Label welcomeLabel = new Label("Welcome to Our Banking Platform!");
        welcomeLabel.getStyleClass().add("label");

        // Current Account Info
        Label accountInfoLabel = new Label("Current Account: Savings");
        accountInfoLabel.getStyleClass().add("label");

        // Current Balance Display
        Label balanceLabel = new Label("Current Balance: $" + currentAccount.getBalance());
        balanceLabel.getStyleClass().add("label");

        // Input Field for Amount
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
        amountField.getStyleClass().add("text-field");

        // Buttons for different actions
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button checkBalanceButton = new Button("Check Balance");
        Button switchAccountButton = new Button("Switch Account");
        Button exitButton = new Button("Exit");

        // Style the buttons
        depositButton.getStyleClass().add("button");
        withdrawButton.getStyleClass().add("button");
        checkBalanceButton.getStyleClass().add("button");
        switchAccountButton.getStyleClass().add("button");
        exitButton.getStyleClass().add("button");

        // Deposit Action
        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    showError("Please enter a positive amount.");
                } else {
                    currentAccount.deposit(amount);
                    balanceLabel.setText("Current Balance: $" + String.format("%.2f", currentAccount.getBalance()));
                    amountField.clear();
                }
            } catch (NumberFormatException ex) {
                showError("Invalid input. Please enter a numeric value.");
            }
        });

        // Withdraw Action
        withdrawButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    showError("Please enter a positive amount.");
                } else {
                    currentAccount.withdraw(amount);
                    balanceLabel.setText("Current Balance: $" + String.format("%.2f", currentAccount.getBalance()));
                    amountField.clear();
                }
            } catch (InsufficientFundsException ex) {
                showError(ex.getMessage());
            } catch (NumberFormatException ex) {
                showError("Invalid input. Please enter a numeric value.");
            }
        });

        // Check Balance Action
        checkBalanceButton.setOnAction(e -> {
            balanceLabel.setText("Current Balance: $" + String.format("%.2f", currentAccount.getBalance()));
        });

        // Switch Account Action
        switchAccountButton.setOnAction(e -> {
            if (currentAccount == savingsAccount) {
                currentAccount = checkingAccount;
                accountInfoLabel.setText("Current Account: Checking");
            } else if (currentAccount == checkingAccount) {
                currentAccount = businessAccount;
                accountInfoLabel.setText("Current Account: Business");
            } else {
                currentAccount = savingsAccount;
                accountInfoLabel.setText("Current Account: Savings");
            }
            balanceLabel.setText("Current Balance: $" + String.format("%.2f", currentAccount.getBalance()));
        });

        // Exit Action
        exitButton.setOnAction(e -> primaryStage.close());

        // Layout
        VBox vbox = new VBox(10, welcomeLabel, accountInfoLabel, balanceLabel, amountField, depositButton, withdrawButton, checkBalanceButton, switchAccountButton, exitButton);
        vbox.getStyleClass().add("vbox");

        // Scene Setup
        Scene scene = new Scene(vbox, 300, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("JavaFX Banking Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Initialize Accounts
    private void initializeAccounts() {
        savingsAccount = new SavingsAccount("S100", 1500.0);
        checkingAccount = new CheckingAccount("C100", 1000.0);
        businessAccount = new BusinessAccount("B100", 5000.0);
        currentAccount = savingsAccount; // Default account
    }

    // Utility method to show error messages
    private void showError(String message) {
        Stage errorStage = new Stage();
        Label errorMessage = new Label(message);
        errorMessage.getStyleClass().add("label");
        Button closeButton = new Button("Close");
        closeButton.getStyleClass().add("button");
        closeButton.setOnAction(e -> errorStage.close());
        VBox errorBox = new VBox(10, errorMessage, closeButton);
        errorBox.getStyleClass().add("vbox");
        Scene errorScene = new Scene(errorBox, 250, 100);
        errorScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        errorStage.setTitle("Error");
        errorStage.setScene(errorScene);
        errorStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Start JavaFX application
    }
}
