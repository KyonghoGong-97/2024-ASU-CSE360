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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NurseView 
{

    static Label pageTitle = new Label("Nurse Page");
    
    static Label patientNameLabel = new Label("Patient Name ");
    static Label appointmentLabel = new Label("Appointment  ");
    static Label healthHistoryLabel = new Label("Health History ");
    static Label medicationsLabel = new Label("Medications");
    static Label allergiesLabel = new Label("Allergies");
    static Label precautionsLabel = new Label("Precautions");
    static Label drNoteLabel = new Label("Doctor Note");
    static Label nurseNoteLabel = new Label("Nurse Note");
    
    
    static Label patientToLoad = new Label("Patient to Load");
    static ComboBox<String> patientNameComboBox = new ComboBox<>();

    static TextField patientNameField = new TextField();
    static TextField appointmentField = new TextField();
    static TextField healthHistoryField = new TextField();
    static TextField medicationsField = new TextField();
    static TextField allergiesField = new TextField();
    static TextField precautionsField = new TextField();
    static TextField drNoteField = new TextField();
    static TextField nurseNoteField = new TextField();
    
    static Button selectButton = new Button("Select");
    static Button backButton = new Button("Back");
    static Button editButton = new Button("Edit");
    static Button saveButton = new Button("Save");
    
    static String selection; 
    
    public static Parent goToNurseView(Main app, String patientUsername) 
    {

        Label NurseViewLabel = new Label("Nurse Page");
        NurseViewLabel.setStyle("-fx-font-size: 20px");
        loadPatientNames();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        
        gridPane.add(patientNameComboBox, 1, 0);
        gridPane.add(patientNameLabel, 0, 1);
        gridPane.add(appointmentLabel, 0, 2);
        gridPane.add(healthHistoryLabel, 0, 3);
        gridPane.add(medicationsLabel, 0, 4);
        gridPane.add(allergiesLabel, 0, 5);
        gridPane.add(precautionsLabel, 0, 6);
        gridPane.add(drNoteLabel, 0, 7);
        gridPane.add(nurseNoteLabel, 0, 8);

        gridPane.add(patientToLoad, 0, 0);
        gridPane.add(patientNameField, 1, 1);
        gridPane.add(appointmentField, 1, 2);
        gridPane.add(healthHistoryField, 1, 3);
        gridPane.add(medicationsField, 1, 4);
        gridPane.add(allergiesField, 1, 5);
        gridPane.add(precautionsField, 1, 6);
        gridPane.add(drNoteField, 1, 7);
        gridPane.add(nurseNoteField, 1, 8);
    

        appointmentField.setPrefSize(200, 500);
        healthHistoryField.setPrefSize(200, 500);
        medicationsField.setPrefSize(200, 500);
        allergiesField.setPrefSize(200, 500);
        precautionsField.setPrefSize(200, 500);
        drNoteField.setPrefSize(200, 500);
        nurseNoteField.setPrefSize(200, 500);

        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().addAll(editButton, saveButton, backButton);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getChildren().addAll(NurseViewLabel, gridPane, hBox);

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
            setFieldsEditable(false);
            saveButton.setDisable(true);
            saveNurseData(patientNameComboBox.getValue());
        });
        
        backButton.setOnAction(e -> app.switchWelcomePage());
        
        patientNameComboBox.setOnAction(e -> {
            String selectedPatient = patientNameComboBox.getValue();
            if(selectedPatient != null && !selectedPatient.isEmpty()) {
                loadNurseData(selectedPatient);
            }
        });
        
        return vbox;
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

    private static void setFieldsEditable(boolean editable) 
    {
        patientNameField.setEditable(editable);
        appointmentField.setEditable(editable);
        healthHistoryField.setEditable(editable);
        medicationsField.setEditable(editable);
        allergiesField.setEditable(editable);
        precautionsField.setEditable(editable);
        drNoteField.setEditable(editable);
        nurseNoteField.setEditable(editable);

    }

    private static void loadNurseData(String patientUsername) 
	{
		File userFile = new File("./PatientInformation/" + patientUsername + ".txt");
		try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) 
		{
			patientUsername = reader.readLine();
			reader.readLine();
			reader.readLine();
			
			String firstName = reader.readLine();
			String lastName = reader.readLine();
			
			reader.readLine();
			reader.readLine();
			reader.readLine();
			reader.readLine();
			reader.readLine();
			
			String appointment = reader.readLine();
			String healthHistory = reader.readLine();
			String medications = reader.readLine();
			
			reader.readLine();
			
			String allergies = reader.readLine();
			String precautions = reader.readLine();
			String doctorNote = reader.readLine();
			String nurseNote = reader.readLine();
			
			patientNameField.setText(firstName + " " + lastName);
			appointmentField.setText(appointment);
			healthHistoryField.setText(healthHistory);
			medicationsField.setText(medications);
			allergiesField.setText(allergies);
			precautionsField.setText(precautions);
			drNoteField.setText(doctorNote);
			nurseNoteField.setText(nurseNote);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			makeAlertPopUp.warn("Warning", "Warning:", "Patient Does not Exist");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    private static void saveNurseData(String patientUsername) 
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

    	        String[] name = patientNameField.getText().split(" ");
    	        writer.write(name[0] + "\n");
    	        writer.write(name[1] + "\n");

    	        writer.write((line6 == null ? "N/A" : line6) + "\n");
    	        writer.write((line7 == null ? "N/A" : line7) + "\n");
    	        writer.write((line8 == null ? "N/A" : line8) + "\n");
    	        writer.write((line9 == null ? "N/A" : line9) + "\n");
    	        writer.write((line10 == null ? "N/A" : line10) + "\n");

    	        writer.write(appointmentField.getText() + "\n");
    	        writer.write(healthHistoryField.getText() + "\n");
    	        writer.write(medicationsField.getText() + "\n");

    	        writer.write((line14 == null ? "N/A" : line14) + "\n");

    	        writer.write(allergiesField.getText() + "\n");
    	        writer.write(precautionsField.getText() + "\n");
    	        writer.write(drNoteField.getText() + "\n");
    	        writer.write(nurseNoteField.getText());


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


