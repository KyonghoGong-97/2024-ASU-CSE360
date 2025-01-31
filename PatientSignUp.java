package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.FileNotFoundException;

public class PatientSignUp {
	static Label pageTitle = new Label("Patient Sign Up");
	
	static Label usernameLabel = new Label("Username ");
	
	static TextField usernameField = new TextField();
	static Button checkButton = new Button("Check");
	
	static Label passwordLabel = new Label("Password  ");
	static TextField passwordField = new TextField();
	
	static Label genderLabel = new Label("Gender            ");
	
	static Label firstNameLabel = new Label("First Name");
	static TextField firstNameField = new TextField();
	
	static Label lastNameLabel = new Label("Last Name       ");
	static TextField lastNameField = new TextField();
	
	static Label birthdayLabel = new Label("Birthday    ");
	static TextField birthdayField = new TextField();
	
	static Label phoneNumberLabel = new Label("Phone Number");
	static TextField phoneNumberField = new TextField();
	
	static Label insuranceLabel = new Label("Insurance  ");
	static TextField insuranceField = new TextField();
	
	static Label pharmacyLabel = new Label("Pharmacy        ");
	static TextField pharmacyField = new TextField();
	
	static Label emailLabel = new Label("Email         ");
	static TextField emailField = new TextField();
	
	static Button signUpButton = new Button("Sign up");
	static Button backButton = new Button("Back");
	
	static boolean usernameChecked = false;
	
	static String selection; 

	public static Parent goToPatientSignUp(Main app) {
		
		usernameField.clear();
		passwordField.clear();
		firstNameField.clear();
		lastNameField.clear();
		birthdayField.clear();
		phoneNumberField.clear();
		insuranceField.clear();
		pharmacyField.clear();
		emailField.clear();
		
		
		 checkButton.setStyle(
				    "-fx-background-color: white; " +
				    "-fx-text-fill: red; " +
				    "-fx-border-color: black; " +
				    "-fx-border-width: 1px; " + 
				    "-fx-font-family: 'Arial'; " +
				    "-fx-font-size: 14px;" +
				    "-fx-background-radius: 5; " + 
				    "-fx-border-radius: 5;" +
				    "-fx-font-weight: bold; " +
				    "-fx-font-style: italic; "
				);
		   
		 signUpButton.setStyle(
				    "-fx-background-color: white; " +
				    "-fx-text-fill: pink; " +
				    "-fx-border-color: black; " +
				    "-fx-border-width: 1px; " + 
				    "-fx-font-family: 'Arial'; " +
				    "-fx-font-size: 14px;" +
				    "-fx-background-radius: 5; " + 
				    "-fx-border-radius: 5;" +
				    "-fx-font-weight: bold; " +
				    "-fx-font-style: italic; "
				);
		 
		 backButton.setStyle(
				    "-fx-background-color: white; " +
				    "-fx-text-fill: black; " +
				    "-fx-border-color: black; " +
				    "-fx-border-width: 1px; " + 
				    "-fx-font-family: 'Arial'; " +
				    "-fx-font-size: 14px;" +
				    "-fx-background-radius: 5; " + 
				    "-fx-border-radius: 5;" +
				    "-fx-font-weight: bold; " +
				    "-fx-font-style: italic; "
				);

		VBox UI = new VBox();
		
		ComboBox<String> genderSelect = new ComboBox<>();
		pageTitle.setStyle("-fx-font-size: 36px;");
		pageTitle.setPadding(new Insets(10, 10, 18, 35));
		genderSelect.getItems().addAll("Male", "Female");
		genderSelect.setPromptText("Select");
		
		HBox firstLine = new HBox();
		firstLine.setSpacing(10);
	    firstLine.setPadding(new Insets(0, 0, 0, 15));
		firstLine.getChildren().addAll(usernameLabel, usernameField);
		firstLine.getChildren().add(2, checkButton);
		usernameLabel.setPadding(new Insets(3, 2, 0, 2));
		usernameField.setPrefWidth(165);
		
		HBox secondLine = new HBox();
		secondLine.setSpacing(10);
		secondLine.setPadding(new Insets(-4, 0, 0, 15));
		secondLine.getChildren().addAll(passwordLabel, passwordField, genderLabel, genderSelect);
		passwordLabel.setPadding(new Insets(3, 2, 0, 2));
		passwordField.setPrefWidth(165);
		genderLabel.setPadding(new Insets(3, 0, 0, 7));
		
		HBox thirdLine = new HBox();
		thirdLine.setSpacing(10);
		thirdLine.setPadding(new Insets(1, 0, 0, 15));
		thirdLine.getChildren().addAll(firstNameLabel, firstNameField, lastNameLabel, lastNameField);
		firstNameLabel.setPadding(new Insets(3, 2, 0, 2));
		firstNameField.setPrefWidth(165);
		lastNameLabel.setPadding(new Insets(3, 0, 0, 5));
		lastNameField.setPrefWidth(160);	
		
		HBox fourthLine = new HBox();
		fourthLine.setSpacing(10);
		fourthLine.setPadding(new Insets(1, 0, 0, 15));
		fourthLine.getChildren().addAll(birthdayLabel, birthdayField, phoneNumberLabel, phoneNumberField);
		birthdayLabel.setPadding(new Insets(3, 2, 0, 2));
		birthdayField.setPrefWidth(165);
		phoneNumberLabel.setPadding(new Insets(3, 0, 0, 5));
		phoneNumberField.setPrefWidth(158);
		
		HBox fifthLine = new HBox();
		fifthLine.setSpacing(10);
		fifthLine.setPadding(new Insets(1, 0, 0, 15));
		fifthLine.getChildren().addAll(insuranceLabel, insuranceField, pharmacyLabel, pharmacyField);
		insuranceLabel.setPadding(new Insets(3, 2, 0, 2));
		insuranceField.setPrefWidth(165);
		pharmacyLabel.setPadding(new Insets(3, 0, 0, 5));
		pharmacyField.setPrefWidth(160);
		
		HBox sixthLine = new HBox();
		sixthLine.setSpacing(10);
		sixthLine.setPadding(new Insets(1, 0, 0, 15));
		sixthLine.getChildren().addAll(emailLabel, emailField, signUpButton, backButton);
		emailLabel.setPadding(new Insets(3, 2, 0, 2));
		emailField.setPrefWidth(165);
		
		UI.setPadding(new Insets(10,10,10,10));
		UI.setSpacing(10);
		UI.getChildren().addAll(pageTitle, firstLine, secondLine, thirdLine, fourthLine, fifthLine, sixthLine);
		
		backButton.setOnAction(e -> app.switchLoginPortal());
		
		genderSelect.setOnAction(e ->  {
			selection = genderSelect.getValue();
		});
		
		checkButton.setOnAction(e -> {
			
			String usernameInput = usernameField.getText();
			
			if(usernameInput.isEmpty())
				makeAlertPopUp.warn("Empty Username Input", "Warning", "Must enter a username");
			
			else {
				
			try (FileReader reader = new FileReader("./PatientInformation/" + usernameInput + ".txt")) { 
				makeAlertPopUp.warn("Username already exists", "Warning:", "Username already exists");
				usernameChecked = false;
				
			} catch(FileNotFoundException bruh) {
				makeAlertPopUp.warn("Username Available", "Notice:", "Username available");
				usernameChecked = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			}
			
		});
		
		signUpButton.setOnAction(e -> {
			
			String usernameInput = usernameField.getText();
			String passwordInput = passwordField.getText();
			String firstNameInput = firstNameField.getText();
			String lastNameInput = lastNameField.getText();
			String birthdayInput = birthdayField.getText();
			String phoneNumberInput = phoneNumberField.getText();
			String insuranceInput = insuranceField.getText();
			String pharmacyInput = pharmacyField.getText();
			String emailInput = emailField.getText();
			
			if(!usernameInput.isEmpty() && !passwordInput.isEmpty() && !firstNameInput.isEmpty() && !lastNameInput.isEmpty() && !birthdayInput.isEmpty() && genderSelect.getValue() != null && !phoneNumberInput.isEmpty() && !insuranceInput.isEmpty() && !pharmacyInput.isEmpty() && !emailInput.isEmpty()) {
				try (FileWriter writer = new FileWriter("./PatientInformation/" + usernameInput + ".txt")) {   //create new file for writing
					writer.write(usernameInput + "\n");   //write all patient info
					writer.write(passwordInput + "\n");
					writer.write(selection + "\n");
					writer.write(firstNameInput + "\n");
					writer.write(lastNameInput  + "\n");
					writer.write(birthdayInput + "\n");
					writer.write(phoneNumberInput + "\n");
					writer.write(insuranceInput + "\n");
					writer.write(pharmacyInput + "\n");
					writer.write(emailInput + "\n");
					
					makeAlertPopUp.warn("Notice", "Notice", "Patient Made");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				app.switchLoginPortal();   //once finished, go back to the main UI
			}
			else if(!usernameChecked) 
				makeAlertPopUp.warn("Username check", "Warning", "Username must be checked for availability");
			else {
				makeAlertPopUp.warn("Not all field filled", "Warning:", "Must fill/select all fields");
			}
			
		});
		
		return UI;  //should return the UI setup
		
	}
}
