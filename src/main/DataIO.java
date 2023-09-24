package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataIO {
	public static String root = "";
	public static ArrayList<Book> loadFile(String fileName) {
		char[] s = {File.separatorChar};
		String sp = new String(s);
		
		File file = new File(root+ sp + fileName);
		FileReader fread = null;
		BufferedReader buffR = null;
		ArrayList<Book> list = new ArrayList<>();
		
		try{
			fread = new FileReader(file);
			buffR = new BufferedReader(fread);
			
			String line;
			while((line = buffR.readLine()) != null) {
				Book b = new Book(line);
				list.add(b);
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(buffR != null) {
					buffR.close();
				}
			}catch(Exception e) {
				
			}
		}
		return list;
	}
}
