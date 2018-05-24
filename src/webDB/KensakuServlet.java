package webDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class IndexStartServlet
 */
@WebServlet("/KensakuServlet")
public class KensakuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KensakuServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("KensakuServletが実行されました。");

		String id = request.getParameter("id");

		String user = "train2018";
		String pass = "train2018";
		String servername = "localhost:3306";
		String dbname = "new_schema";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		try
		(
			Connection con = DriverManager.getConnection(
						"jdbc:mysql://"
						+ servername
						+ "/"
						+ dbname,
						user,
						pass
						);
		)
		{

			PreparedStatement stmt = con.prepareStatement(
					  "select "
					+ "   KAIINID "
					+ "  ,NAME "
					+ "  ,REGISTDATE "
					+ "from "
					+ "   KAIIN "
					+ "where "
					+ "	  KAIINID =? ");

			stmt.setInt(1, Integer.parseInt(id));

			ResultSet rset = stmt.executeQuery();

			System.out.println("AAAAAAAAAAAAAA");


			while (rset.next())
			{
				request.setAttribute("id", rset.getInt(1));
				request.setAttribute("name", rset.getString(2));
				request.setAttribute("date", rset.getDate(3));
			}

			RequestDispatcher disp = request.getRequestDispatcher("/next.jsp");
			disp.forward(request, response);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
