package de.astradeni.backupmanager.view;

import java.io.File;
import java.util.List;

import de.astradeni.backupmanager.Manager;
import de.astradeni.model.Fields;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class OverviewController {
	
	private Manager manager;
	
	@FXML
	private Button myButton;
	
	@FXML
	private TextArea field;
	
	@FXML
	private Stage overviewStage;
	
	
	
	
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public void setStage(Stage overviewStage) {
		this.overviewStage = overviewStage;
	}
	
	public void showFields(List<File> fieldList) {
		String result = "";
		for (File f : fieldList) {
			result = (result + f.getName() + "\n").replace("[", "").replace("]", "").replace(",", "");
			field.setText(result);
		}
	}
	
	
	@FXML
	private void okClicked() {
		
	
	}
			

}
