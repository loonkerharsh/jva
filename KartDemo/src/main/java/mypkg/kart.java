package mypkg;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class kart
 */
public class kart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection cn;
	private PreparedStatement psq;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public kart() {
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
		String html;
		Cookie arr[]=request.getCookies();
		int sum=0;
		if(arr==null) {html="<html><body bgcolor=#F5E8DD><center><h1>your kart is empty</h1><br><br></center></body></html>";}
		
		else {	
		html="<html><body bgcolor=#F5E8DD><center><h1>Your Cart</h1><br>"
					+ "<form action=delete><table bgcolor=white width=50% border=1px><tr><th>bkid</th><th>title</th><th>quantity</th><th>total</th><th>delete</th></tr>";
		try
		{
			for(Cookie c:arr)
			{
				psq.setInt(1, Integer.parseInt(c.getName()));
				ResultSet rs=psq.executeQuery();
				
				while(rs.next())
				{
					int price=rs.getInt("price");
					int dis=rs.getInt("dis");
					int discnt=price-price*dis/100;
					sum=sum+Integer.parseInt(c.getValue())*discnt;
					html=html+"<tr>"
							+ " <td>"+rs.getInt(1)+"</td>"
							+ " <td>"+rs.getString(2)+"</td>"
							+ " <td>"+c.getValue()+"</td>"
							+ " <td>"+Integer.parseInt(c.getValue())*discnt+"</td>"
							+ "<td><a href=delete?del="+rs.getInt(1)+"><img height=55px width=55px src=trash.JPG></a></td>"
							+ " <td><input type=checkbox name=del value="+rs.getInt(1)+"></td>"
							+ "</tr>";
				}
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		html=html+"</table><br><input type=submit value=delete></form><h2>cart total : "+sum+"</h2><br><br>"
				+ "<a href=transferdetails?sum="+sum+"><img height=55px width=55px src=pay.png></a>"
				+ "</center></body></html>";}
		response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu
		doGet(request, response);
	}

}
