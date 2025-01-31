package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class makeAlertPopUp {
	public static void warn(String title, String subtitle, String message) {   //two arguments: the title of the window, the message to be displayed inside the scene
		Stage warning = new Stage();
		
		warning.initModality(Modality.APPLICATION_MODAL); 
		warning.setTitle(title);   //set the title of the window to the given title
		warning.setMinWidth(300);
		warning.setMinHeight(200);
		
		Label label = new Label(message);                //print the message on the next line after after "Warning:"
		Label warningHeading = new Label(subtitle);   
		Button close = new Button("Ok");
		close.setOnAction(e-> warning.close());   //when the OK button is pressed, close the warning window
		
		VBox warningLayout = new VBox(10);
		
		warningLayout.getChildren().addAll(warningHeading,label,close);   //stack all UI 
		warningLayout.setPadding(new Insets(10,10,10,10));
		warningLayout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(warningLayout);
		warning.setScene(scene);
		warning.showAndWait();    //don't allow the user to do anything unless the OK button has been pressed
	}
}
