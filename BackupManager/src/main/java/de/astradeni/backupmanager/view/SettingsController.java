package de.astradeni.backupmanager.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.astradeni.backupmanager.Manager;
import de.astradeni.backupmanager.model.Fields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SettingsController {
	
	private Manager manager;
	ObservableList<Fields> fields = FXCollections.observableArrayList();
	
	@FXML
	private Button settingsButton;
	
	@FXML
	private Button selectButton;
	
	@FXML
	private Button addButton;
	
//	@FXML
//	private TextArea field;

	@FXML
	private TableColumn <Fields, String> pathsColumn;
	
	@FXML
	private TableView <Fields> tableView;
	
	@FXML
	private TableColumn <Fields, Void> colBtn;
	
	
	@FXML
	private Stage settingsStage;
	
	@FXML
	private void initialize() {
		pathsColumn.setCellValueFactory(new PropertyValueFactory<>("path"));
		//tableView.getColumns().add(pathsColumn);
		addButtonToTable();
		 
		//
	}
	public void setManager(Manager manager) {
		this.manager = manager;
		tableView.setItems(manager.getPathData());
	}
	
	public void setStage(Stage settingsStage) {
		this.settingsStage = settingsStage;
	}

	
	@FXML
	public void handleSave() {
		
			DirectoryChooser directoryChooser = new DirectoryChooser();
	        directoryChooser.setInitialDirectory(new File("src"));
	        selectButton.setOnAction(e -> {
		        File selectedDirectory = directoryChooser.showDialog(settingsStage);
		        System.out.println(tableView.getItems());
	
		        boolean isFound = tableView.getItems().stream().anyMatch(field->field.getPath().equals(selectedDirectory.toString()));
		        if(!isFound) {
		        	try {
						showPaths(manager.handleSave(selectedDirectory.getAbsolutePath()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
			
	        });
		
		
	}
	
	public void showPaths(List<Fields> pathList) throws IOException {
		
		tableView.getItems().clear();
		for (Fields field: pathList) {
			tableView.getItems().add(field);
		}
		//pathsColumn.setText(result);	
	}
	
	public void addButtonToTable() {
		Callback<TableColumn<Fields, Void>, TableCell<Fields, Void>> cellFactory = new Callback<TableColumn<Fields, Void>, TableCell<Fields, Void>>() {
            public TableCell<Fields, Void> call(final TableColumn<Fields, Void> param) {
                final TableCell<Fields, Void> cell = new TableCell<Fields, Void>() {
                	private final Button btn = new Button("Delete");
                    
                    {
                    
                    	btn.setOnAction((ActionEvent event) ->{
                    		int toDeleteIndex = getTableRow().indexProperty().intValue();
	                    	try {
								List<Fields> fields = manager.handleDelete(toDeleteIndex);
								showPaths(fields);
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    		
//                    		 
//                    		//Fields field = tableView.getSelectionModel().getSelectedItem();
//                    		Fields field = getTableRow().getItem();
//                    		 tableView.getItems().remove(field);
//                    		// fields.remove(field);
//                    		
                    	});
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                        	setText(null);
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        //tableView.getColumns().add(colBtn);

    }
	
	@FXML
	public void okHandle() {
		System.exit(0);
	}
		
	
}


