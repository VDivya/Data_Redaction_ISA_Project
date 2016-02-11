package isa;


import java.io.File;
import java.io.IOException;
import java.util.*;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class Storage {
	
	File inputWorkbook;
	Workbook w;
	private String inputFile;
    String[][] data = null;
    static int columnwidth=25;
   // String [][] datacopy=null;
    public void setInputFile(String inputFile) 
    {
        this.inputFile = inputFile;
       // File inputWorkbook = new File(inputFile);
        Workbook w;
    }
//Reading the file and storing
    public String[][] read() throws IOException  
    {
        inputWorkbook = new File(inputFile);
        //Workbook w;
       
        try 
        {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet


            Sheet sheet = w.getSheet(0);
            data = new String[sheet.getColumns()][sheet.getRows()];
                
            
            System.out.println("columns in excel"+sheet.getColumns()+"rows in excel"+sheet.getRows());
            
            // Loop over first 10 column and lines
       //     System.out.println(sheet.getColumns() +  " " +sheet.getRows());
            //rows in excel are columns in data
            //columns in excel are rows in data
            for (int j = 0; j <sheet.getColumns(); j++) 
            {
                for (int i = 0; i < sheet.getRows(); i++) 
                {
                    Cell cell = sheet.getCell(j, i);
                    data[j][i] = cell.getContents();
                  //  System.out.println(cell.getContents());
                }
            }
            System.out.println("rows in data stored:"+data.length);
                       

            
        /* for (int j = 0; j < sheet.getColumns(); j++) 
            {
        	  for (int i = 0; i <sheet.getRows(); i++) 
                {
                	//System.out.println("\n");
        		 System.out.format("%-" + columnwidth + "s", data[i][j]);
                 //   System.out.print(data[i][j]+" ");
                }
                System.out.println("\n");
            } */
         return data;
        } 
        
        catch (BiffException e) 
        {
            e.printStackTrace();
        }
    return null;
    }


	

    public static void main(String[] args) throws IOException 
    {
        Storage test = new Storage();
        test.setInputFile("E:/Book1.xls");
       String[][] data=new String[15][15];
       // String [][] datacopy//
      data= test.read();
      String p="1";
     data=test.processSSN(data);
      test.partialRedaction(data,p);
        //System.out.println(data);
    }
    //partial redaction of bank numbers of particular users with particular manager
    void partialRedaction(String[][] data2,String p0)throws IOException {
		// TODO Auto-generated method stub
		String [][] datacopy;
		List<String> bankno= new ArrayList<String>();
		List<String> test=new ArrayList<String>();
		//Iterator<String> bno = bankno.iterator();
		//String names=null;
		 try {
	        w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
           Sheet sheet = w.getSheet(0);
		 datacopy = data2;
		String userinput = p0;
		 int rows=sheet.getRows();
		 int columns=sheet.getColumns();
		// StringBuffer redactbno;
		//extract the required data to redact
		 for(int i=0;i<datacopy.length;i++) {
			 //String p="Bank Account";
			  for(int j=0;j<datacopy[i].length;j++) {
				 if(datacopy[i][0].contains("manager") && datacopy[i][j].contains(userinput)) {
					 //test.add(datacopy[i][j]);
					 bankno.add(datacopy[i-1][j]);}
					 
					 						 
					/* if(datacopy[i-1][0].contains("Bank Account"))
					 {
						 for(int k=j; k<datacopy[i].length;k++) {
						 bankno.add(datacopy[i][k]);
						 k++;
						 }
					 }*/
				 }
			 }
		 //redacting the proper extracted data
		 for(int p=0;p<bankno.size();p++){
			 String temp= bankno.get(p);
			 StringBuffer redactbno=new StringBuffer(temp);
			redactbno.replace(0, 7, "xxxxxxxx");
			//System.out.println(redactbno);
			test.add(redactbno.toString());

			 //String redactbno= temp.replace("(.*)-", "xxxxxxx");
			 //System.out.println(redactbno);
			 //test.add(temp);
		 }
		 
		 System.out.println(bankno+"jjj");
		 String[][] red=null;
		 
		 System.out.println(test+"hhh");
		 //print the table with new redacted data
		 for (int j = 0; j <columns; j++) 
	     {
			 System.out.format("%-" + columnwidth + "s", datacopy[j][0]);
			 for(int k=0,a=0;k<rows;k++,a++){
				 
			 if( datacopy[5][k].contentEquals("1")){
			//for(int i=j;i<j;i++) {
	         	//System.out.println("\n");
				// red[a][a]=datacopy[j][k];
				 if(j==4)
				 {
					StringBuilder s=new StringBuilder(datacopy[4][k]) ;
					for(int temp=0;temp<7;temp++)
					s.setCharAt(temp, 'x');
					datacopy[4][k]=s.toString();
				 }
				 
					// System.out.println("rdjghjh");
	 		//System.out.print(""+ datacopy[j][k]+" \t");
				 
	 		System.out.format("%-" + columnwidth + "s", datacopy[j][k]);
	          //   System.out.print(data[i][j]+" ");
				//}
				 //for(int p=0;p<test.size();p++){
				// red[4][k]=test.get(p);
					// }
		         //System.out.format("%-" + columnwidth + "s", red[j][k]);
			 }
	         
			 
	     }
			 //System.out.print(datacopy[j][0]);
			 System.out.println("\n"); 
	     }
		 /*
		 for (int j = 0; j < sheet.getColumns(); j++) 
         {
     	  for (int i = 0; i <sheet.getRows(); i++) 
             {
     		  if(datacopy[5][i].contentEquals("1"))
     		  {	//System.out.println("\n");
     		 System.out.print( datacopy[j][i]);
     		  }
     		  //   System.out.print(data[i][j]+" ");
             }
             System.out.println("\n");
         } */
		 
		/* for(int i=0;i<datacopy.length;i++) {
			 //String p="Bank Account";
			  for(int j=0;j<datacopy[i].length;j++) {
				 if(datacopy[i][0].contains("manager") && datacopy[i][j].contains(userinput)) {
					 System.out.format("%-" + columnwidth + "s", datacopy[j][i]);
				 }
			  }
		 }*/
				 
			 
		 }catch(BiffException e)
		 {
			 e.printStackTrace();
		 }
		
	}
	
	
	
	
	//full redaction of ssn column
	
	String[][] processSSN(String[][] data) throws IOException {
		// TODO Auto-generated method stub
		String [][] datacopy;
		List<String> ssn= new ArrayList<String>();
		//String names=null;
		 try {
	        w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet


            Sheet sheet = w.getSheet(0);
		 datacopy = data;
		// String[] oneDArray = new String[datacopy.length * datacopy.length];
		 //getting the data to redact
		  for(int i = 0; i < datacopy.length; i ++)
		  {
		  if(datacopy[i][0].contentEquals("SSN"))
		  {
			  for(int j=1;j<datacopy[i].length;j++){
			  ssn.add(datacopy[i][j]);
		  }
		  }
		  }
		  System.out.println(ssn);
		  //redaction of ssn list
		  //redaction of data
		  Collections.fill(ssn,"xxxxxxxxx");
	 System.out.println(ssn);
	 String[] temp=new String[ssn.size()];
	 temp=ssn.toArray(temp);
	 for(int i = 0; i < datacopy.length; i ++)
	  {
	  if(datacopy[i][0].contentEquals("SSN"))
	  {
		  for(int j=1;j<datacopy[i].length;j++){
			  for(int k=0;k<temp.length;k++)
		  datacopy[i][j]=temp[k];
	  }
	  }
	  }
	 //printing the table with redacted data
	 for (int j = 0; j < sheet.getColumns(); j++) 
     {
 	  for (int i = 0; i <sheet.getRows(); i++) 
         {
         	//System.out.println("\n");
 		 System.out.format("%-" + columnwidth + "s", datacopy[i][j]);
          //   System.out.print(data[i][j]+" ");
         }
         System.out.println("\n");
     } 
	
		return datacopy;  
		 //for()
		 //System.out.println(datacopy);
	}
		 catch(BiffException e){
			 e.printStackTrace();
			 }
		 catch(ArrayIndexOutOfBoundsException e){
	e.printStackTrace();	 }
		return null;
	}

}
