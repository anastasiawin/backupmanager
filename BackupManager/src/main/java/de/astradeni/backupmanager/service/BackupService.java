package de.astradeni.backupmanager.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import de.astradeni.model.Fields;

public class BackupService {
	

	   
	   public List<File> calculate(){
		   try {
			File myFolder = new File("D:\\Downloads");
			   File[] files = myFolder.listFiles();
		   List <File> list = Arrays.asList(files);
		   System.out.println(list.toString());
		   return list;
		   } catch(Exception e) {
			   System.out.println(e.getMessage());
			   return null;
		   }
		   
	   }

}
