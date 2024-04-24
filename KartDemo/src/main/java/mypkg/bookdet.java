package mypkg;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Servlet implementation class bookdet
 */
public class bookdet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection cn;
	private PreparedStatement psq; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookdet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookcafe","root","1234");
			psq=cn.prepareStatement("select * from books where bkid=?");
		}
		catch(ClassNotFoundException e) {
			System.out.println("driver can't load");
		}
		catch(SQLException e) {
			System.out.println("Sql Alert");
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			psq.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String s=request.getParameter("bkid");
		String title=null,author=null,subject=null;
		int price=0,dis=0,qoh=0;
		
		try
		{
			psq.setInt(1,Integer.parseInt(s));
			ResultSet rs=psq.executeQuery();
			while(rs.next())
			{
				title=rs.getString(2);
				author=rs.getString(3);
				subject=rs.getString(4);
				price=rs.getInt(5);
				dis=rs.getInt(6);
				qoh=rs.getInt(7);	
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		int discnt=price-price*dis/100;
		String html="<html><body bgcolor=#ccd3ca>"+ "<br>&nbsp;<center><font size=7px>"+title
				+ "<br></font>  <p align=center><image src="+Integer.parseInt(s)+".jpg align=left></p>"
				+"<form action=cart>"
				+ " <table cellspacing=20px>"
				+ "  <tr align=center>"
				+ "   <td>author  </td>"
				+ "   <td>"+author+"</td>"
				+ "   </tr>"
				+ "   <tr align=center>"
				+ "   <td>subject </td>"
				+ "   <td>"+subject+"</td>"
				+ "  </tr>"
				+ "  <tr align=center>"
				+ "  <td>price  </td>"
				+ "  <td><del>"+price+"</del>&nbsp"+discnt+"</td></tr>"
				+ "  <tr align=center><td>select quantity</td><td>"
				+ "<select name=qoh>";
				for(int i=1;i<=qoh;i++)
				{
					html=html+"<option >"+i+"</option>";
				}
				html=html+ "</select>"
				+ "</td></tr>"
				+ " </table><input type=submit value=\"add to cart\">"
				+ "<input type=hidden name=bkid value="+Integer.parseInt(s)+"><form>"
				+"</b><br>"
				+ "</center></body></html>";
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
