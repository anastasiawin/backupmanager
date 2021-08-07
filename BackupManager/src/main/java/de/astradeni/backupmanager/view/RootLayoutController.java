package de.astradeni.backupmanager.view;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.astradeni.backupmanager.Manager;
import de.astradeni.backupmanager.model.Fields;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class RootLayoutController {
	private static final Logger LOGGER = LogManager.getLogger(RootLayoutController.class);
	private Manager manager;
	private Fields field;
	
	public void setManager (Manager manager)
	{
		this.manager = manager;
	}
	
	
	
	@FXML
	private Button myButton;
	
	@FXML
	private Button fileButton;
	
	@FXML
	private TextArea myTextArea;
	
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
	
	@FXML
	private void getFiles() throws Exception {
		manager.showOverviewWindow();
	}
	
	@FXML
	private void getSettings() {
		manager.showSettingsWindow(field);
	}
	
	
	
	
}
