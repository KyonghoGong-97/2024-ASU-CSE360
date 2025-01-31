package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	Stage stage;
	
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			stage = primaryStage;
			switchWelcomePage();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// finish
	public void switchWelcomePage() {
		stage.setTitle("Welcome Page");
		stage.setScene(new Scene(WelcomePage.goToWelcomePage(this), 500, 300));
		stage.show();
	}
	
	// finish
	public void switchLoginPortal() {
		stage.setTitle("Sign In Page");
		stage.setScene(new Scene(LoginPortal.goToLoginPortal(this), 400, 255));
		stage.show();
	}
	
	// finish
	public void switchPatientSignUp(){
		stage.setTitle("Sign Up Page");
		stage.setScene(new Scene(PatientSignUp.goToPatientSignUp(this), 550, 323));
		stage.show();
	}
	
	public void switchPatientView(String patientUsername) {
		stage.setTitle("Patient View Page");
		stage.setScene(new Scene(PatientView.goToPatientView(this, patientUsername), 590, 314));
		stage.show();
	}
	
	public void switchDoctorView(String patientUsername) {
		stage.setTitle("Doctor View Page");
		stage.setScene(new Scene(DoctorView.goToDoctorView(this, patientUsername), 600, 350));
		stage.show();
	}
	
	public void switchNurseView(String patientUsername) {
		stage.setTitle("Nurse View Page");
		stage.setScene(new Scene(NurseView.goToNurseView(this, patientUsername), 400, 500));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
