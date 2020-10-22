package co.empresa.prueba.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.prueba.dao.IngredienteDao;
import co.empresa.prueba.modelo.Ingrediente;

/**
 * Servlet implementation class IngredienteServlet
 */
@WebServlet("/")
public class IngredienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IngredienteDao ingredienteDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngredienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.ingredienteDao = new IngredienteDao(); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertarIngrediente(request, response);
			break;
		case "/delete":
			eliminarIngrediente(request, response);
			break;
		case "/edit":
			showEditIngrediente(request, response);
			break;
		case "/update":
			actualizarIngrediente(request, response);
			break;
			
		default:
			listIngredientes(request,response);
			break;
		}
		}catch(SQLException e) {
			throw new ServletException(e);
		} 
		
	}

	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException , IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("ingrediente.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertarIngrediente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException , IOException, SQLException {
		String nombre = request.getParameter("nombre");
		String cantidad = request.getParameter("cantidad");
		String precio = request.getParameter("precio");
		Ingrediente ingrediente = new Ingrediente(nombre, cantidad, precio);
		ingredienteDao.insert(ingrediente);
		response.sendRedirect("list");
	}
	
	private void showEditIngrediente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException , IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		Ingrediente ingredienteActual = ingredienteDao.select(id);
		request.setAttribute("ingrediente", ingredienteActual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ingrediente.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void actualizarIngrediente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException , IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String cantidad = request.getParameter("cantidad");
		String precio = request.getParameter("precio");
		Ingrediente ingrediente = new Ingrediente(id,nombre, cantidad, precio);
		ingredienteDao.update(ingrediente);
		response.sendRedirect("list");
		
	}
	
	private void eliminarIngrediente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException , IOException, SQLException{
		int id = Integer.parseInt(request.getParameter("id"));
		ingredienteDao.delete(id);
		response.sendRedirect("list");
		
	}
	
	private void listIngredientes(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException , IOException, SQLException{
		List <Ingrediente> listIngredientes = ingredienteDao.selectAll();
		request.setAttribute("listIngredientes", listIngredientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ingredientelist.jsp");
		dispatcher.forward(request, response);
	}

	
}
