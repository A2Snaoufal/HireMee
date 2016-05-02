package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaisieMotClé
 */
@WebServlet("/SaisieMotCle")
public class SaisieMotCle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/webContent/AffichageMotCle.jsp";

    /**
     * Default constructor. 
     */
    public SaisieMotCle() {
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	String mot = request.getParameter("keyword");
    	out.println("vous avez cherché <b>"+mot+ "</b>");
    	request.setAttribute("mot", mot);
        request.getRequestDispatcher("AffichageMotCle.jsp").forward(request, response);
    

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
