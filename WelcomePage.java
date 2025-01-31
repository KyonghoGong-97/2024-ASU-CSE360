package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WelcomePage {
	
	static Button PatientButton = new Button("Patient");
	static Button NurseButton = new Button("Nurse");
	static Button DoctorButton = new Button("Doctor");
	
	static Image patientImage = new Image("patient_image.png");
	static Image nurseImage = new Image("nurse_image.png");
	static Image stethoscope_image = new Image("stethoscope_image.png");
	
	public static Parent goToWelcomePage(Main app) {
		
        Label Select_one = new Label("SELECT ONE");
        Select_one.setStyle(
				    "-fx-text-fill: green; " +
				    "-fx-border-width: 1px; " + 
				    "-fx-font-size: 32px;" +
				    "-fx-font-weight: bold; " +
				    "-fx-font-style: italic; "
				);
        Select_one.setPadding(new Insets(10, 10, 0, 10));
        		
		VBox patientOption = new VBox();
		
		// khg - fixed this part all below.
		patientOption.setSpacing(20);
		patientOption.setPadding(new Insets(10,10,-6,0));
		patientOption.setAlignment(Pos.CENTER);
		
		 ImageView patientImageView = new ImageView(patientImage);
		 patientImageView.setFitWidth(90);           //set width
		 patientImageView.setFitHeight(70);          //set height
		 patientImageView.setPreserveRatio(true);     //preserve the aspect ratio
		 
		 PatientButton.setStyle(
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
		    
		
		patientOption.getChildren().addAll(patientImageView, PatientButton);
		
		
		
		
		// khg - fixed this part all below.
		VBox nurseOption = new VBox();
		
		nurseOption.setSpacing(20);
		nurseOption.setPadding(new Insets(10,10,10,0));
		nurseOption.setAlignment(Pos.CENTER);
		
		 ImageView nurseImageView = new ImageView(nurseImage);
		 nurseImageView.setFitWidth(70);           //set width
		 nurseImageView.setFitHeight(70);          //set height
		 nurseImageView.setPreserveRatio(true);     //preserve the aspect ratio
		 
		 NurseButton.setStyle(
				    "-fx-background-color: white; " +
				    "-fx-text-fill: purple; " +
				    "-fx-border-color: black; " +
				    "-fx-border-width: 1px; " + 
				    "-fx-font-family: 'Arial'; " +
				    "-fx-font-size: 14px;" +
				    "-fx-background-radius: 5; " + 
				    "-fx-border-radius: 5;" +
				    "-fx-font-weight: bold; " +
				    "-fx-font-style: italic; "
				);
		    
		
		nurseOption.getChildren().addAll(nurseImageView, NurseButton);
		
		
		
		
		
		// khg - fixed this part all below.
		VBox doctorOption = new VBox();
		
		doctorOption.setSpacing(20);
		doctorOption.setPadding(new Insets(10,10,10,10));
		doctorOption.setAlignment(Pos.CENTER);
		
		 ImageView doctorImageView = new ImageView(stethoscope_image);
		 doctorImageView.setFitWidth(70);           //set width
		 doctorImageView.setFitHeight(70);          //set height
		 doctorImageView.setPreserveRatio(true);     //preserve the aspect ratio
		 
		 DoctorButton.setStyle(
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
		    
		
		doctorOption.getChildren().addAll(doctorImageView, DoctorButton);
		
		
		HBox ButtonUI = new HBox();
		
		ButtonUI.setSpacing(10);
		ButtonUI.setPadding(new Insets(5));
		ButtonUI.setAlignment(Pos.CENTER);
		

		ButtonUI.getChildren().addAll(patientOption, nurseOption, doctorOption);
		
		
		PatientButton.setOnAction(e -> app.switchLoginPortal());
		NurseButton.setOnAction(e -> app.switchNurseView(""));
		DoctorButton.setOnAction(e -> app.switchDoctorView(""));

        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().addAll(Select_one, ButtonUI);

        return root;
		
	}
}
