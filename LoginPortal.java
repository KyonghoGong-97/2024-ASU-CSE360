package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginPortal {
	
	static Button backButton = new Button("Back");
	static Button SignInButton = new Button("Sign In");
	static Button SignUpButton = new Button("Sign Up");
	
	static Label title = new Label("Login Portal");
	static Label usernameLabel = new Label ("Username");
	static Label passwordLabel = new Label("Password");
	
	static TextField usernameField = new TextField();
	static TextField passwordField = new TextField();
	
	
	public static Parent goToLoginPortal(Main app) {
		
		String[] info = new String[2];
		
		usernameField.clear();
		passwordField.clear();
		
		VBox SignInUI = new VBox();
		SignInUI.setPadding(new Insets(10,10,10,10));
		SignInUI.setSpacing(10);
		
		HBox backButtonLayout = new HBox();
		backButtonLayout.setSpacing(10);
		backButtonLayout.setAlignment(Pos.CENTER_RIGHT);
		
		// khg - fixed this part all below.
		VBox centerTitle = new VBox();
		title.setStyle("-fx-font-size: 36px;");
		centerTitle.setAlignment(Pos.CENTER);
		centerTitle.getChildren().addAll(title);
		title.setPadding(new Insets(-20, 0, 15, 0));
		
		
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
		
		backButtonLayout.getChildren().addAll(backButton);
		backButtonLayout.setSpacing(10);
		backButtonLayout.setAlignment(Pos.CENTER_RIGHT);
		
		
		// khg - fixed all paddings, etc.
		HBox usernameLayout = new HBox();
		usernameLayout.setSpacing(10);
		usernameLayout.setAlignment(Pos.CENTER);
		usernameLayout.getChildren().addAll(usernameLabel, usernameField);
		usernameLabel.setPadding(new Insets(7, 0, 8, -22));
		usernameLabel.setPrefWidth(33);
		usernameField.setPrefWidth(175);
		
		
		HBox passwordLayout = new HBox();
		passwordLayout.setSpacing(10);
		passwordLayout.setAlignment(Pos.CENTER);
		passwordLayout.getChildren().addAll(passwordLabel, passwordField);
		passwordLabel.setPadding(new Insets(7, 0, 8, -21.5));
		passwordLabel.setPrefWidth(34);
		passwordField.setPrefWidth(175);
		
		
		SignInButton.setStyle(
			    "-fx-background-color: white; " +
			    "-fx-text-fill: blue; " +
			    "-fx-border-color: black; " +
			    "-fx-border-width: 1px; " + 
			    "-fx-font-family: 'Arial'; " +
			    "-fx-font-size: 14px;" +
			    "-fx-background-radius: 5; " + 
			    "-fx-border-radius: 5;" +
			    "-fx-font-weight: bold; " +
			    "-fx-font-style: italic; "
				);
		
		
		
		SignUpButton.setStyle(
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
		
		HBox buttonLayout = new HBox();
		buttonLayout.setSpacing(10);
		buttonLayout.setAlignment(Pos.CENTER);
		buttonLayout.getChildren().addAll(SignInButton, SignUpButton);
        HBox.setMargin(SignInButton, new Insets(10, 0, 10, 33));
        HBox.setMargin(SignUpButton, new Insets(5, 0, 5, 15));
		
		
		
		SignInUI.getChildren().addAll(backButtonLayout, centerTitle, usernameLayout, passwordLayout, buttonLayout);
		
		backButton.setOnAction(e -> app.switchWelcomePage());
		SignUpButton.setOnAction(e -> app.switchPatientSignUp());
		
		SignInButton.setOnAction(e->{
					
				String usernameInput = usernameField.getText();
				String passwordInput = passwordField.getText();
				
				if(usernameInput.isEmpty()) 
					makeAlertPopUp.warn("Empty Username Input", "Warning", "Must enter a username");
				
				else if(passwordInput.isEmpty())	
					makeAlertPopUp.warn("Empty Password Input" ,"Warning", "Must enter password");
				
				else {
					
					//implement username finding algorithm using the title of the patient text files as the username
					
					//using example input
					try (FileReader reader = new FileReader("./PatientInformation/" + usernameInput + ".txt")) { 
						 BufferedReader bufferedReader = new BufferedReader(reader);
				         String line; 
				         
				         int index = 0;
				         
				         while((line = bufferedReader.readLine()) != null && index < 2) { 
				             info[index] = line;   //get the patient first and last name
				                index++;
				           }
				          
				         //password verification functionality demo test
				         if( !passwordInput.equals(info[1]) ) {
				        	makeAlertPopUp.warn("Invalid Password", "Warning:", "Incorrect Password");
				        	app.switchLoginPortal();
				         }
				         else {
				        	 makeAlertPopUp.warn("Login", "Sucess:", "Logging in..");
				        	 app.switchPatientView(usernameInput);
				         }
				         
				         
				         
					} catch(FileNotFoundException bruh) {
						makeAlertPopUp.warn("Invalid Usernane" ,"Warning:", "Invalid Username"); //if no associated CTResults with given patient ID, return this warning
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		
		
		return SignInUI;
		
	}
}
