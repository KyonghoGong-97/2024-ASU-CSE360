package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.TextArea;


public class DoctorView 
{
    static Label pageTitle = new Label("Doctor View Page");

    static Label patientNameLabel = new Label("Patient Name ");
    static ComboBox<String> patientNameComboBox = new ComboBox<>();

    static Label appointmentLabel = new Label("Appointment  ");
    static TextArea appointmentField = new TextArea();

    static Label healthHistoryLabel = new Label("Health History ");
    static TextArea healthHistoryField = new TextArea();

    static Label medicationsLabel = new Label("Medications");
    static TextArea medicationsField = new TextArea();

    static Label resultsLabel = new Label("Physical Results ");
    static TextArea resultsField = new TextArea();

    static Label drNoteLabel = new Label("Doctor Note");
    static TextArea drNoteField = new TextArea();

    static Label nurseNoteLabel = new Label("Nurse Note");
    static TextArea nurseNoteField = new TextArea();

    static Button backButton = new Button("Back");
    static Button editButton = new Button("Edit");
    static Button saveButton = new Button("Save");

    public static Parent goToDoctorView(Main app, String patientUsername) 
    {    
        Label drViewLabel = new Label("Doctor Page");
        drViewLabel.setStyle("-fx-font-size: 32px");
        drViewLabel.setPadding(new Insets(0, 10, 12, 45));

        loadPatientNames();
        String user = loadDoctorData(patientUsername);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(patientNameLabel, 0, 0);
        patientNameLabel.setPadding(new Insets(0, 0, 30, 0));
        gridPane.add(patientNameComboBox, 1, 0);
        patientNameComboBox.setPrefWidth(170);
        patientNameComboBox.setValue("Select");
        patientNameComboBox.setMinHeight(50);

        gridPane.add(appointmentLabel, 0, 1);
        appointmentLabel.setPadding(new Insets(0, 0, 30, 0));
        gridPane.add(appointmentField, 1, 1);
        appointmentField.setPrefWidth(170);
        appointmentField.setText("Example - 2020/02/02");
        appointmentField.setMinHeight(50);
        
        gridPane.add(healthHistoryLabel, 0, 2);
        healthHistoryLabel.setPadding(new Insets(0, 0, 30, 0));
        gridPane.add(healthHistoryField, 1, 2);
        healthHistoryField.setPrefWidth(170);
        healthHistoryField.setMinHeight(50);

        gridPane.add(medicationsLabel, 0, 3);
        medicationsLabel.setPadding(new Insets(0, 0, 30, 0));
        gridPane.add(medicationsField, 1, 3);
        medicationsField.setPrefWidth(170);
        medicationsField.setMinHeight(50);
      
        gridPane.add(resultsLabel, 2, 0);
        resultsLabel.setPadding(new Insets(0, 0, 30, 0));
        gridPane.add(resultsField, 3, 0);
        resultsField.setPrefWidth(170);
        resultsField.setMinHeight(50);
        
        gridPane.add(drNoteLabel, 2, 1);
        gridPane.add(drNoteField, 3, 1);
        drNoteLabel.setPadding(new Insets(0, 0, 30, 0));
        drNoteField.setPrefWidth(170);
        drNoteField.setMinHeight(50);
        
        gridPane.add(nurseNoteLabel, 2, 2);
        gridPane.add(nurseNoteField, 3, 2);
        nurseNoteLabel.setPadding(new Insets(0, 0, 30, 0));
        nurseNoteField.setPrefWidth(170);
        nurseNoteField.setMinHeight(50);

        HBox buttonBox = new HBox(30);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.setPadding(new Insets(-250, 17, 28, 10));
        buttonBox.getChildren().addAll(editButton, saveButton, backButton);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getChildren().addAll(drViewLabel, gridPane, buttonBox);

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
                        "-fx-font-style: italic; ");

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
                "-fx-font-style: italic; ");

        editButton.setOnAction(e -> 
        {
            setFieldsEditable(true);
            saveButton.setDisable(false);
        });

        saveButton.setOnAction(e -> 
        {
            saveDoctorData(user);
            setFieldsEditable(false);
            saveButton.setDisable(true);
        });

        backButton.setOnAction(e -> app.switchWelcomePage());

        patientNameComboBox.setOnAction(e -> {
            String selectedPatient = patientNameComboBox.getValue();
            if(selectedPatient != null && !selectedPatient.isEmpty()) {
                loadDoctorData(selectedPatient);
            }
        });

        return vbox;
    }

    private static void setFieldsEditable(boolean editable) 
    {
        appointmentField.setEditable(editable);
        healthHistoryField.setEditable(false);
        medicationsField.setEditable(false);
        resultsField.setEditable(editable);
        drNoteField.setEditable(editable);
        nurseNoteField.setEditable(editable);
    }

    private static void loadPatientNames() 
    {
        File folder = new File("./PatientInformation");
        File[] file_list = folder.listFiles();

        if(file_list != null) {
            for(File file : file_list) {
                if(file.isFile() && file.getName().endsWith(".txt")) {
                    String patient_user_name = file.getName().replace(".txt", "");
                    patientNameComboBox.getItems().add(patient_user_name);
                }
            }
        }
    }

    private static String loadDoctorData(String patientUsername) {
        File userFile = new File("./PatientInformation/" + patientUsername + ".txt");
        String name = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
			name = reader.readLine();
            reader.readLine();		// Skip password
            reader.readLine(); 		// Skip gender
            reader.readLine();	 	// Skip first name
            reader.readLine(); 		// Skip last name
            reader.readLine(); 		// Skip birthday
            reader.readLine(); 		// Skip phone number
            reader.readLine(); 		// Skip insurance
            reader.readLine(); 		// Skip pharmacy
            reader.readLine(); 		// Skip email
            appointmentField.setText(reader.readLine());
            healthHistoryField.setText(reader.readLine());
            medicationsField.setText(reader.readLine());
            resultsField.setText(reader.readLine());
            reader.readLine(); 		// Skip allergies
            reader.readLine(); 		// Skip precautions
            drNoteField.setText(reader.readLine());
            nurseNoteField.setText(reader.readLine());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return name;
    }

    private static void saveDoctorData(String patientUsername) 
    {
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
    	        writer.write((line4 == null ? "N/A" : line4) + "\n");
    	        writer.write((line5 == null ? "N/A" : line5) + "\n");
    	        writer.write((line6 == null ? "N/A" : line6) + "\n");
    	        writer.write((line7 == null ? "N/A" : line7) + "\n");
    	        writer.write((line8 == null ? "N/A" : line8) + "\n");
    	        writer.write((line9 == null ? "N/A" : line9) + "\n");
    	        writer.write((line10 == null ? "N/A" : line10) + "\n");
    	        
    	        
    	        String writing = appointmentField.getText();
    	        writer.write((writing == null ? "N/A" : writing) + "\n");
    	        
    	        writing = healthHistoryField.getText();
    	        writer.write((writing == null ? "N/A" : writing) + "\n");
    	        
    	        writing = medicationsField.getText();
    	        writer.write((writing == null ? "N/A" : writing) + "\n");
    	        
    	        writing = resultsField.getText();
    	        writer.write((writing == null ? "N/A" : writing) + "\n");

    	        writer.write((line15 == null ? "N/A" : line15) + "\n");
    	        writer.write((line16 == null ? "N/A" : line16) + "\n");
    	        
    	        writing = drNoteField.getText();
    	        writer.write((writing == null ? "N/A" : writing) + "\n");
    	        
    	        writing = nurseNoteField.getText();
    	        writer.write((writing == null ? "N/A" : writing));
    


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
