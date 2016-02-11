/* 
 * Copyright (C) 2014 Vasilis Vryniotis <bbriniotis at datumbox.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package examples;

import classifiers.*;
import dataobjects.*;
import features.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vasilis Vryniotis <bbriniotis at datumbox.com>
 * @see <a href="http://blog.datumbox.com/developing-a-naive-bayes-text-classifier-in-java/">http://blog.datumbox.com/developing-a-naive-bayes-text-classifier-in-java/</a>
 */
public class NaiveBayesExample {

    /**
     * Reads the all lines from a file and places it a String array. In each 
     * record in the String array we store a training example text.
     * 
     * @param url
     * @return
     * @throws IOException 
     */
    public static String[] readLines(URL url) throws IOException {

        Reader fileReader = new InputStreamReader(url.openStream(), Charset.forName("UTF-8"));
        List<String> lines;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[lines.size()]);
    }
    
    /**
     * Main method
     * 
     * @param args the command line arguments
     * @return 
     * @throws java.io.IOException
     */
    public void run() throws IOException {
        //map of dataset files
        Map<String, URL> trainingFiles = new HashMap<>();
        trainingFiles.put("English", NaiveBayesExample.class.getResource("/datasets/training.language.en.txt"));
        trainingFiles.put("French", NaiveBayesExample.class.getResource("/datasets/training.language.fr.txt"));
        trainingFiles.put("German", NaiveBayesExample.class.getResource("/datasets/training.language.de.txt"));
        
        //loading examples in memory
        Map<String, String[]> trainingExamples = new HashMap<>();
        for(Map.Entry<String, URL> entry : trainingFiles.entrySet()) {
            trainingExamples.put(entry.getKey(), readLines(entry.getValue()));
        }
        
        //train classifier
        NaiveBayes nb = new NaiveBayes();
        nb.setChisquareCriticalValue(6.63); //0.01 pvalue
        nb.train(trainingExamples);
        
        //get trained classifier knowledgeBase
        NaiveBayesKnowledgeBase knowledgeBase = nb.getKnowledgeBase();
        
        nb = null;
        trainingExamples = null;
        
        
        //Use classifier
        nb = new NaiveBayes(knowledgeBase);
        String line;
        FileReader fr = new FileReader("E:\\test.txt");     
        BufferedReader br = new BufferedReader(fr);  
        while((line=br.readLine()) != null) {  
            //if(line.contains("Javascript"))
        	String example=line;
            String outputEn = nb.predict(line);
            if(outputEn!=null)
            {	//say that dates and others we are redacting using the format 00/00/0000 and thats y first occurence of data is not getting redacted or so
            	//repeat this for everything i mean one for greetings, one for client names etc etc... {
            	for(int i=0;i<5;i++) { //put i< arrayofgreetings.length
            	if(outputEn.contains("put the array of greetings [i]"));
            	outputEn=outputEn.replace("arrayofgreeting[i]", "xxxxxxxxx");
            	System.out.println(outputEn);
            	//No need of quotations if u give name[i] }
            }
            	for(int i=0;i<5;i++) { //put i< arrayofclient names.length
                	if(outputEn.contains("put the array of greetings [i]"));
                	outputEn=outputEn.replace("arrayofgreeting[i]", "xxxxxxxxx"); //No need of quotations if u give name[i] }
                	System.out.println(outputEn);}
            	for(int i=0;i<5;i++) { //put i< arrayofkeywords.length
                	if(outputEn.contains("put the array of greetings [i]"));
                	outputEn=outputEn.replace("arrayofgreeting[i]", "xxxxxxxxx"); //No need of quotations if u give name[i] }
                	System.out.println(outputEn);}
            }
            	
        }
        /*
        
*/
    }
    
}
