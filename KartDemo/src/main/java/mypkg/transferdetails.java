package mypkg;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Date;

/**
 * Servlet implementation class transferdetails
 */
public class transferdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transferdetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String sum=request.getParameter("sum");
		
		String html="<html>"
				   +" <form action=Home>"
				   +"  <body bgcolor=#EABE6C>"
				   +"   <center>"
				   +"    <h1>Payment Window</h1>"
				   +"    <br><br><br><br>"
				   +"    <table>"
				   +"     <tr>"
				   +"      <td><font face=arial color=white size=5>Your Account No. </font></td>"
				   +"      <td><input type=text name=acc></font></td>"
				   +"     </tr>"
				   +"     <tr>"
				   +"      <td><font face=arial color=white size=5>Transfering Amount </font></td>"
				   +"      <td align=center><font face=arial color=red size=5>"+sum+"</font></td>"
				   +"     </tr>"
				   +"    </table>"
				   +"    <br><br><br>"
				   +"    <input type=submit value=Confirm>"
				   +"   </center>"
				   +"  </body>"
				   +" </form>"
				   +"</html>";
		
		response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
