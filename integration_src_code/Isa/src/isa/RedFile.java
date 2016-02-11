package isa;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class RedFile {
	ArrayList<String> lines = new ArrayList<String>();     
    String line= null;   
    String[] keywordslaterredact={"Respectfully"," Yours truly","Infosec"," Technologies","MICROSOFT","Yours sincerely"," Sincerely yours"," Sincerely"," Best regards","Regards","Cordially","With many thanks","Warm wishes","Dear","Agreed and Accepted"};
	String[] keywordsbeforeredact={"/hr"};
	String dateregex="([0-9]{2})/([0-9]{2})/([0-9]{4})";
	String[] st={"Milano","MICROSOFT","Infosec Technologies","Bootch Thomas"};
    public void printThis(){     
        try{     
    FileReader fr = new FileReader("E:\\test.txt");     
    BufferedReader br = new BufferedReader(fr);     
    FileWriter fw = new FileWriter("E:\\test_out.txt");     
    BufferedWriter out = new BufferedWriter(fw);     
    while((line=br.readLine()) != null) {  
     //if(line.contains("Javascript"))
    	int temp;
    	StringBuffer l;
    	//String[] t=line.split("");
    	//for(int c=0;c<t.length;c++) {
    	//if(t[c].contains("/hr"))
		//{			line.replaceAll("t[c]", "xxxxxxxxxx");
			//lines.add(line+"\n");
			//out.write(line+"\n");
				//	}
      //line=line.replace("Javascript"," JAVA");
    	for(int i=0;i<st.length;i++) {
			if(line.contains(st[i])) {
				//int o=line.indexOf(keywordslaterredact[i]);
				//line=line.
				//temp=line.indexOf(st[i].length());
				 //l=new StringBuffer(line);
				line=line.replace(st[i], "xxxxxxxxxxxxxxx");}
				else if((line.contains("11/01/2014"))){
				line=line.replaceAll("[0-9]{2}/[0-9]{2}/[0-9]{4}", "xxxxxxxxxx");}
				else if(line.contains("/hr"))
					line=line.replaceAll("1.5", "xxxxxxx");
				//line=l.toString();
				lines.add("\n"+line+"\n");
			}
    	lines.add(line+"\n");     
        out.write("\n"+line+"\n");
		}
    	 
      
    out.flush();
    out.close();    
    
     br = new BufferedReader(new FileReader("E:\\test_out.txt"));
    //String line = null;
    while ((line = br.readLine()) != null) {
      System.out.println(line);
     // System.out.println();
    }
        }     
    catch(Exception e){}     
    }     


    public static void main(String [] args){     
            RedFile tr = new RedFile();     
            tr.printThis();     
        }     
}
