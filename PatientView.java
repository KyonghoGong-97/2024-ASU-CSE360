package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PatientView {
    static TextField patientNameField = new TextField();
    static TextField emailField = new TextField();
    static TextField reservationField = new TextField();
    static TextField appointmentField = new TextField();
    static TextArea appointmentSummaryArea = new TextArea();
    static TextField contactDoctorField = new TextField();
    static TextField insuranceInfoField = new TextField();
    static TextArea messageArea = new TextArea();
    static Button editButton = new Button("Edit");
    static Button backButton = new Button("Back");
    static Button saveButton = new Button("Save");

    static String saveDirectory = "./PatientInformation";

    public static Parent goToPatientView(Main app, String patientUsername) {
        Label patient_title = new Label("Patient Page");
        patient_title.setStyle("-fx-font-size: 36px");
        patient_title.setPadding(new Insets(17, 10, 7, 45));
    	
        loadPatientData(patientUsername);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 10, 10, 15));
        gridPane.setVgap(12);
        gridPane.setHgap(12);

        Label patientNameLabel = new Label("Patient Name");
        Label emailLabel = new Label("Email");
        Label reservationLabel = new Label("Reservation");
        Label appointmentLabel = new Label("Appointment");
        Label appointmentSummaryLabel = new Label("Appointment Summary");
        Label contactDoctorLabel = new Label("Contact Doctor");
        Label insuranceInfoLabel = new Label("Insurance Info");
        Label messageLabel = new Label("Message");

        patientNameField.setPrefWidth(150);
        emailField.setPrefWidth(150);
        reservationField.setPrefWidth(150);
        reservationField.setPrefHeight(20);
        appointmentField.setPrefWidth(150);
        appointmentSummaryArea.setPrefSize(150, 100);
        appointmentSummaryArea.setMinHeight(100);
        contactDoctorField.setPrefWidth(150);
        insuranceInfoField.setPrefWidth(150);
        messageArea.setPrefSize(150, 60);

        gridPane.add(patientNameLabel, 0, 0);
        gridPane.add(patientNameField, 1, 0);
        gridPane.add(contactDoctorLabel, 2, 0);
        gridPane.add(contactDoctorField, 3, 0);

        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(insuranceInfoLabel, 2, 1);
        gridPane.add(insuranceInfoField, 3, 1);
       
        gridPane.add(appointmentLabel, 0, 2);
        gridPane.add(appointmentField, 1, 2);

        gridPane.add(messageLabel, 2, 3);
        gridPane.add(messageArea, 3, 3);
        messageLabel.setPadding(new Insets(-79, 0, 0, 0));
        messageArea.setMinHeight(60);
        
        gridPane.add(appointmentSummaryLabel, 0, 3);
        appointmentSummaryLabel.setPadding(new Insets(-79, 0, 0, 0));
        gridPane.add(appointmentSummaryArea, 1, 3);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(-60, 0, 0, 366));
        buttonBox.getChildren().addAll(editButton, saveButton, backButton);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(0, 10, 10, 10));
        vbox.getChildren().addAll(patient_title, buttonBox, gridPane);

        saveButton.setStyle(
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
        
        saveButton.setDisable(true);
        setFieldsEditable(false);

        editButton.setStyle(
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

        editButton.setOnAction(e -> {
            setFieldsEditable(true);
            saveButton.setDisable(false);
        });

        saveButton.setOnAction(e -> {
            savePatientData(patientUsername);
            setFieldsEditable(false);
            saveButton.setDisable(true);
        });

        backButton.setOnAction(e -> app.switchLoginPortal());

        return vbox;
    }

    private static void setFieldsEditable(boolean editable) {
        patientNameField.setEditable(editable);
        emailField.setEditable(editable);
        reservationField.setEditable(false);
        appointmentField.setEditable(false);
        appointmentSummaryArea.setEditable(false);
        contactDoctorField.setEditable(false);
        insuranceInfoField.setEditable(editable);
        messageArea.setEditable(editable);
    }

    private static void loadPatientData(String patientUsername) {
        File userFile = new File(saveDirectory + "/" + patientUsername + ".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String username = reader.readLine();
            reader.readLine();
            String gender = reader.readLine();
            String firstName = reader.readLine();
            String lastName = reader.readLine();
            String birthday = reader.readLine();
            String phoneNumber = reader.readLine();
            String insurance = reader.readLine();
            String pharmacy = reader.readLine();
            String email = reader.readLine();
            String appt = reader.readLine();

            patientNameField.setText(firstName + " " + lastName);
            emailField.setText(email);
            insuranceInfoField.setText(insurance);
            appointmentField.setText(appt); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void savePatientData(String patientUsername) {
    	File userFile = new File("./PatientInformation/" + patientUsername + ".txt");

    	try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
    	    String line1 = reader.readLine(); // Assuming patientUsername is already written
    	    String line2 = reader.readLine();
    	    String line3 = reader.readLine();
    	    String line4 = reader.readLine();
    	    String line5 = reader.readLine();
    	    String line6 = reader.readLine();
    	    String line7 = reader.readLine();
    	    String line8 = reader.readLine();
    	    String line9 = reader.readLine();
    	    String line10 = reader.readLine();
    	    String line11 = reader.readLine();
    	    String line12 = reader.readLine();
    	    String line13 = reader.readLine();
    	    String line14 = reader.readLine();
    	    String line15 = reader.readLine();
    	    String line16 = reader.readLine();
    	    String line17 = reader.readLine();
    	    String line18 = reader.readLine();

    	    // Close the reader after reading all lines
    	    reader.close();

    	    // Write updated data back to the file
    	    try (FileWriter writer = new FileWriter(userFile)) {
    	        writer.write((line1 == null ? "N/A" : line1) + "\n");
    	        writer.write((line2 == null ? "N/A" : line2) + "\n");
    	        writer.write((line3 == null ? "N/A" : line3) + "\n");

    	        String[] name = patientNameField.getText().split(" ");
    	        writer.write(name[0] + "\n");
    	        writer.write(name[1] + "\n");

    	        writer.write((line6 == null ? "N/A" : line6) + "\n");
    	        writer.write((line7 == null ? "N/A" : line7) + "\n");
    	        
    	        String extractedInsurance = insuranceInfoField.getText();
    	        writer.write((extractedInsurance == null? "N/A" : extractedInsurance) + "\n");
    	        writer.write((line9 == null ? "N/A" : line9) + "\n");
    	        
    	        String extractedEmail = emailField.getText();
    	        writer.write((extractedEmail == null ? "N/A" : extractedEmail) + "\n");
    	        
    	        writer.write((line11 == null ? "N/A" : line11) + "\n");
    	        writer.write((line12 == null ? "N/A" : line12) + "\n");
    	        writer.write((line13 == null ? "N/A" : line13) + "\n");
    	        writer.write((line14 == null ? "N/A" : line14) + "\n");
    	        writer.write((line15 == null ? "N/A" : line15) + "\n");
    	        writer.write((line16 == null ? "N/A" : line16) + "\n");
    	        writer.write((line17 == null ? "N/A" : line17) + "\n");
    	        writer.write((line18 == null ? "N/A" : line18) + "\n");



    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	} catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
	}
}
