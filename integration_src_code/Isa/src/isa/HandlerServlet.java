package isa;

//import Storage;
//import filesredaction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.NaiveBayesExample;

/**
 * Servlet implementation class HandlerServlet
 */
@WebServlet("/HandlerServlet")
public class HandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 response.setContentType("text/html");
	        // read form fields
	        String InputNumber = request.getParameter("Input");
	        String jobrole = request.getParameter("Type");
	        String[][] data=new String[15][15];
	        Storage test=new Storage();
    	 test.setInputFile("E:/Book1.xls");
    	 RedFile tr = new RedFile();   
    	 NaiveBayesExample p=new NaiveBayesExample();
      
    	 data= test.read();
	         if(jobrole.contentEquals("User"))
	         {        	
	        	
	            // data=test.processSSN(data);
	             tr.printThis();
	             p.run();
	         }
	         else if(jobrole.contentEquals("Manager")) {
	        	        //String[][] data=new String[15][15];
	            // String [][] datacopy//
	           //data= test.read();
	        	 
	          data=test.processSSN(data);
	           test.partialRedaction(data,InputNumber);
	         }
	         else if(jobrole.contentEquals("Administrator")) {
	        	 data= test.read();
	        	 data=test.processSSN(data);
	         }
	        //System.out.println("username: " + username);
	        //System.out.println("password: " + password);
	 
	        // do some processing here...
	         
	        // get response writer
	        PrintWriter writer = response.getWriter();
	         
	        // build HTML code
	         
	        // return response
	        //writer.println(htmlRespone);
	}

}
