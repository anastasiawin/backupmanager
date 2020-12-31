package de.astradeni.backupmanager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import de.astradeni.backupmanager.service.BackupService;
import de.astradeni.backupmanager.view.OverviewController;
import de.astradeni.backupmanager.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Manager extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BackupManager");
        initRootLayout();
    }

    	public void initRootLayout() {
    		try {
    			 FXMLLoader loader = new FXMLLoader();
    			 loader.setLocation(getClass().getResource("/view/RootLayout.fxml" ));
    			 rootLayout = (BorderPane) loader.load();
    			 
    			 Scene scene = new Scene (rootLayout, 400, 400);
    			 primaryStage.setScene(scene);
    			 
    			 RootLayoutController controller = loader.getController();
    			 controller.setManager(this);
    			 primaryStage.show();
    		}
    		
    		catch (IOException e) {
    			System.out.println(e);
    		}
    		
    	}
    	
    	public void showOverviewWindow() {
    		try {
    			
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(Manager.class.getResource("/view/Overview.fxml"));
    			AnchorPane page = (AnchorPane) loader.load();
       			System.out.println(page);
    			Stage overviewStage = new Stage();
    			overviewStage.initOwner(primaryStage);
    			Scene scene = new Scene (page);
    			overviewStage.setScene(scene);
    			
    			OverviewController controller = loader.getController();
    			System.out.println(controller);
    			controller.setManager(this);
    			
    			BackupService service = new BackupService();
    			List<Path> fileList = service.calculate();
    			controller.showFields(fileList);
    			overviewStage.showAndWait();
    			
    		}
    		
    		catch (IOException e) {
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	
    public static void main(String[] args) {
        launch(args);
    }

}
