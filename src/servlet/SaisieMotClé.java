package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaisieMotCl�
 */
@WebServlet("/SaisieMotCl�")
public class SaisieMotCl� extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SaisieMotCl�() {
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html");
    	/*PrintWriter out = response.getWriter();
    	String mot = request.getParameter("keyword");
    	out.println("vous avez cherch� <b>"+mot+ "</b>");*/
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("AffichageMotCl�.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
