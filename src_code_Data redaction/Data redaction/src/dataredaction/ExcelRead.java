package dataredaction;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.nio.file.Files;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class ExcelRead {
	public static void main(String[] args) throws IOException {
		
			/*File file = new File("E:/test.txt");
			FileReader fileReader = new FileReader(file);
			*/
			File source = new File("E:/test.txt");
		    File dest = new File("E:/temp.txt");
		   // copyFileUsingJava7Files(source, dest);
		    
			//Files.copy(new File("E:/test.txt"), new File("E:/temp.txt"));
		    redacttext();
		
	}
	 private static void copyFileUsingJava7Files(File source, File dest)
	   
	            throws IOException {
	    
	        Files.copy(source.toPath(), dest.toPath());
	    
	    }
	private static void redacttext() throws IOException {
		// TODO Auto-generated method stub
		//StringBuffer temp=stringBuffer;
		String[] keywordslaterredact={"Respectfully"," Yours truly"," Sincerely yours"," Sincerely"," Best regards","Regards","Cordially","With many thanks","Warm wishes","Dear","Agreed and Accepted"};
		String[] keywordsbeforeredact={"/hr"};
		String dateregex="`	(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
		String line;
		BufferedReader reader = new BufferedReader(new FileReader("E:/temp.txt"));
		//String line = null;
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuffer str = new StringBuffer();
		//StringBuffer temp=new StringBuffer();
		//FileWriter f= new FileWriter(new File("temp.txt"));
		//String line;
		//System.out.println(f);
			
		while ((line = bufferedReader.readLine()) != null) {
			for(int i=0;i<keywordslaterredact.length;i++) {
				if(line.contains(keywordslaterredact[i])) {
					line.replace(keywordslaterredact[i]+1, "xxxxxxxx");
					str.append(line);
				}
			}
			str.append(line);
			//System.out.println(str);
		}
		while((line=bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		
		
		
	}

	
}
