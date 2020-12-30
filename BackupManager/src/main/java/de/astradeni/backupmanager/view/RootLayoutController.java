package de.astradeni.backupmanager.view;

import de.astradeni.backupmanager.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;

public class RootLayoutController {
	
	private Manager manager;
	
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
	private void getFiles() {
		manager.showOverviewWindow();
	}
	
	
	
	
}
