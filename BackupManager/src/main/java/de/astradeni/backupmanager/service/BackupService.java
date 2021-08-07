package de.astradeni.backupmanager.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.astradeni.backupmanager.model.Fields;
import de.astradeni.backupmanager.view.SettingsController;

public class BackupService {
	
	private static final Logger LOGGER = LogManager.getLogger(BackupService.class);

	private File myFolder;
	

	
	private SettingsController sc = new SettingsController();
	
	public List<Path> calculate() throws Exception {
		List<Path> newList = new ArrayList<>();
		for (Fields fields : getArrayOfPaths()) {
		myFolder = new File(fields.path);

		File[] files = myFolder.listFiles();
		
				
		
		if (files != null) {
			List<File> list = Arrays.asList(files);
			
			newList.addAll(list.stream().map(file -> file.toPath()).filter(path -> {
				try {
					BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
					LocalDateTime borderDate = LocalDateTime.now().minusYears(1);
					LocalDateTime convertedFileTime = LocalDateTime.ofInstant(attributes.creationTime().toInstant(), ZoneId.systemDefault());
					return convertedFileTime.isBefore(borderDate);
				} catch (IOException exception) {
					LOGGER.error(
							"Exception handled when trying to get file " + "attributes: " + exception.getMessage());
					return false;
				}
			}).collect(Collectors.toList()));
	
			LOGGER.info("List of all recent files " + list.toString());
		}
		}
		
		return newList;
		

	}
	
	public List<Fields> getArrayOfPaths() throws IOException {
		
		List<Fields> pathList = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("pathtest.txt"))) {
			pathList.clear();
		    String line = br.readLine();

		    while (line != null) {
		    	
		    	Fields fields = new Fields(line);
		    	pathList.add(fields);
		    	line = br.readLine();  
		    	System.out.println(line);   
		    }
		
		return pathList;
		
//		
		
	}

	}

	public void appendToFile(String text) throws IOException {
		Files.write(Paths.get("pathtest.txt"), (text + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
	}
		
		
	
	
	public void deleteFromFile(int index) throws IOException {
		File inputFile = new File("pathtest.txt");
		List<Fields> fields = getArrayOfPaths();
		fields.remove(index);

	    FileWriter fileWriter = new FileWriter(inputFile);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    for (Fields field:fields) {
	    	printWriter.println(field.getPath());
	    }
	    printWriter.close();

		
//		text = null;
//		try {
//			Files.write(Paths.get("pathtest.txt"),(System.lineSeparator() +  text).getBytes(),StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

