package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import dao.SeguroDao;
import dao.TipoSeguroDao;
import entidades.Seguro;
import entidades.TipoSeguro;

/**
 * Servlet implementation class ServletListarSeguro
 */
@WebServlet("/ServletListarSeguro")
public class ServletListarSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletListarSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnListarSeguros")!=null)
		{
			SeguroDao sdao = new SeguroDao();
			ArrayList<Seguro> lista= sdao.listarSeguros();
			
			TipoSeguroDao tdao = new TipoSeguroDao();
		    ArrayList<TipoSeguro> listaTipos = tdao.listarTipoSeguro();
			
			request.setAttribute("listaS",lista);
			request.setAttribute("listaT", listaTipos);
			
			RequestDispatcher rd = request.getRequestDispatcher("ListarSeguro.jsp");
			rd.forward(request, response);
		}
	}

}
