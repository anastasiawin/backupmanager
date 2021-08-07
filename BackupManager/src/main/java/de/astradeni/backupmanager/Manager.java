package de.astradeni.backupmanager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.astradeni.backupmanager.model.Fields;
import de.astradeni.backupmanager.service.BackupService;
import de.astradeni.backupmanager.view.OverviewController;
import de.astradeni.backupmanager.view.RootLayoutController;
import de.astradeni.backupmanager.view.SettingsController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Manager extends Application {
	
	private static final Logger LOGGER = LogManager.getLogger(Manager.class);
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<Fields> pathData = FXCollections.observableArrayList();
	
	public Manager() {

	}
	
	public ObservableList<Fields> getPathData(){
		return pathData;
	}

    @Override
    public void start(Stage primaryStage) throws Exception {
    	setPrimaryStage(primaryStage);
        
        initRootLayout();
    }

	public void initRootLayout() throws IOException {
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("/view/RootLayout.fxml" ));
		 rootLayout = (BorderPane) loader.load();
		 
		 Scene scene = new Scene (rootLayout, 400, 400);
		 primaryStage.setScene(scene);
		 primaryStage.setTitle("BackupManager");
		 RootLayoutController controller = loader.getController();
		 controller.setManager(this);
		 primaryStage.show();	
	}
	
	public void showOverviewWindow() throws Exception {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Manager.class.getResource("/view/Overview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			LOGGER.info(page);
   			//System.out.println(page);
			Stage overviewStage = new Stage();
			overviewStage.initOwner(primaryStage);
			Scene scene = new Scene (page);
			
			overviewStage.setScene(scene);
			overviewStage.setTitle("File List");
			OverviewController controller = loader.getController();
			LOGGER.info(controller);
			//System.out.println(controller);
			controller.setManager(this);
			
			BackupService service = new BackupService();
			List<Path> fileList = service.calculate();
			//List<Path> pathList = service.getArrayOfPaths();
			controller.showFields(fileList);
			overviewStage.showAndWait();
			
		}
		
		catch (IOException e) {
			LOGGER.error(e.getMessage());
			
		}
	}
	
	public void showSettingsWindow(Fields field) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Manager.class.getResource("/view/Settings.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			LOGGER.info(page);
   			//System.out.println(page);
			Stage settingsStage = new Stage();
			settingsStage.initOwner(primaryStage);
			Scene scene = new Scene (page);
			
			settingsStage.setScene(scene);
			settingsStage.setTitle("Settings");
			SettingsController controller = loader.getController();
			LOGGER.info(controller);
			//System.out.println(controller);
			controller.setManager(this);
			
			BackupService service = new BackupService();
			List<Fields> pathList = service.getArrayOfPaths();
			
			controller.showPaths(pathList);
			
			settingsStage.showAndWait();
			
		}
		
		catch (IOException e) {
			LOGGER.error(e.getMessage());
			
		}
	}
	
	public List<Fields>  handleSave(String path) throws IOException {
		BackupService backupService = new BackupService();
		backupService.appendToFile(path);
		List<Fields> pathList = null;
		try {
			pathList = backupService.getArrayOfPaths();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pathList;
	}
	
	public List<Fields> handleDelete(int path) throws IOException {
		BackupService backupService = new BackupService();
		backupService.deleteFromFile(path);
		List<Fields> pathList = null;
		try {
			pathList = backupService.getArrayOfPaths();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pathList;
		
	}
    	
    	
    public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public static void main(String[] args) {
        launch(args);
    }

}
