package mypkg;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Hashtable <Integer,Book>ht=new Hashtable<>(); 
	private Connection cn;
	private PreparedStatement psq; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
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
		
		
		try
		{
			for(int l=101;l<=120;l++)
			{
				Book b=new Book();
			psq.setInt(1,l);
			ResultSet rs=psq.executeQuery();
			while(rs.next())
			{	
				b.bkid=l;
				b.title=rs.getString(2);
				b.author=rs.getString(3);
				b.subject=rs.getString(4);
				b.price=rs.getInt(5);
				b.dis=rs.getInt(6);
				b.qoh=rs.getInt(7);
				
				ht.put(l, b);
				
			}
		    }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
			
		
		String html="<html>"
				+ "<body bgcolor=#b5c0d0>"
				+ "<center><br>"
				+ "<table width=90% cellspacing=10px cellpadding=10px>";
		
		for(int i=101;i<=120;)
		{
			html=html+"<tr align=center>";
			for(int j=0;j<4;j++,i++)
			{
				html=html+"<td><a href='bookdet?bkid="+i+"'><img border=3px src="+i+".jpg></a>";
			}
			html=html+"</tr><tr align=center>";
			for(int j=i-4;j<i;j++)
			{		
			html=html+"<td>Rs."+ht.get(j).price+"</td>";
			}
			html=html+"</tr>";
		}
		html=html+"</table></center></body></html>";
		
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
