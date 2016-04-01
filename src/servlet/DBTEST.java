package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import getdata.DataResolver;
import utils.MyConstants;

/**
 * Servlet implementation class DBTEST
 */
@WebServlet("/DBTEST")
public class DBTEST extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBTEST() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		DataResolver.dataResolve(MyConstants.RSSURLZGHKWLSJDHZ,"中国航空新闻网","/home/wzq/xmldoc/",MyConstants.CATEGORYZGHKWLSJDHZ);
//		DataResolver.dataResolve(MyConstants.RSSURLZGHKWNEWSCENTER,"中国航空新闻网","/home/wzq/xmldoc2/",MyConstants.CATEGORYZGHKWNEWSCENTER);
		
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
