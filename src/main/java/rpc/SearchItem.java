package rpc;

import java.util.Set;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import database.DBConnection;
import database.DBConnectionFactory;
import entity.Item;
import external.TicketMasterAPI;

/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/search")
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub
	 * response.getWriter().append("Served at: ").append(request.getContextPath());
	 * }
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * response.setContentType("text/html"); PrintWriter writer =
	 * response.getWriter();
	 * 
	 * writer.println("<html><body>"); writer.println("<h1>Hello World</h1>");
	 * writer.println("</body></html>");
	 * 
	 * writer.close(); }
	 */

	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * response.setContentType("text/html"); PrintWriter writer =
	 * response.getWriter();
	 * 
	 * if (request.getParameter("username") != null) { String username =
	 * request.getParameter("username");
	 * 
	 * writer.println("<html><body>"); writer.println("<h1>Hello " + username +
	 * "</h1>"); writer.println("</body></html>"); }
	 * 
	 * writer.close();
	 * 
	 * 
	 * }
	 */

	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * response.setContentType("application/json"); PrintWriter writer =
	 * response.getWriter();
	 * 
	 * if (request.getParameter("username") != null) { String username =
	 * request.getParameter("username");
	 * 
	 * JSONObject obj = new JSONObject(); try { obj.put("username", username); }
	 * catch (JSONException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } writer.print(obj); }
	 * 
	 * writer.close(); }
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//  // allow access only if session exists
	  HttpSession session = request.getSession(false);
	  if (session == null) {
	   response.setStatus(403);
	   return;
	  }
	  String userId = session.getAttribute("user_id").toString();

	  double lat = Double.parseDouble(request.getParameter("lat"));
	  double lon = Double.parseDouble(request.getParameter("lon"));
	  String term = request.getParameter("term");
	        DBConnection connection = DBConnectionFactory.getConnection();
	   try {
	   List<Item> items = connection.searchItems(lat, lon, term);
	   Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);
	   
	   JSONArray array = new JSONArray();
	   for (Item item : items) {
	    JSONObject obj = item.toJSONObject();
	    obj.put("favorite", favoritedItemIds.contains(item.getItemId()));
	    array.put(obj);
	   }
	   RpcHelper.writeJsonArray(response, array);
	  
	  } catch (Exception e) {
	   e.printStackTrace();
	  } finally {
	   connection.close();
	  }
	 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
