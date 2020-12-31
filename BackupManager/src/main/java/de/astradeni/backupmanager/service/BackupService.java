package de.astradeni.backupmanager.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BackupService {

	public List<Path> calculate() {

		File myFolder = new File("D:\\Downloads");
		File[] files = myFolder.listFiles();
		List<File> list = Arrays.asList(files);
		List<Path> newList = new ArrayList<Path>();
		newList = list.stream().map(file -> file.toPath()).filter(path -> {
			BasicFileAttributes attributes;
			try {
				attributes = Files.readAttributes(path, BasicFileAttributes.class);
				long milliseconds = attributes.creationTime().to(TimeUnit.MILLISECONDS);
				Date creationDate = new Date(attributes.creationTime().to(TimeUnit.MILLISECONDS));
				return (creationDate.getYear() + 1900) >= 2020;
			} catch (IOException exception) {
				System.out.println(
						"Exception handled when trying to get file " + "attributes: " + exception.getMessage());
				return false;
			}
		}).collect(Collectors.toList());
		/*
		 * for (int i = 0; i < list.size(); i++) { Path filePath = files[i].toPath();
		 * BasicFileAttributes attributes = null; try { attributes =
		 * Files.readAttributes(filePath, BasicFileAttributes.class); } catch
		 * (IOException exception) {
		 * System.out.println("Exception handled when trying to get file " +
		 * "attributes: " + exception.getMessage()); }
		 * 
		 * long milliseconds = attributes.creationTime().to(TimeUnit.MILLISECONDS);
		 * 
		 * String helpString = files[i].toString(); List<String> stringList = new
		 * ArrayList(); stringList.add(helpString);
		 * 
		 * if((milliseconds > Long.MIN_VALUE) && (milliseconds < Long.MAX_VALUE)) { Date
		 * creationDate = new Date(attributes.creationTime().to(TimeUnit.MILLISECONDS));
		 * if ((creationDate.getYear() + 1900) >= 2020 ) { newList.add(files[i]);
		 * stringList.stream().filter(creationDate)
		 * 
		 * }
		 * 
		 * } }
		 */

		System.out.println(list.toString());
		return newList;

	}

}
