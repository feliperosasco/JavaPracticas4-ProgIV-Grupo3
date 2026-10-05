package servlets;

import dao.SeguroDao;
import dao.TipoSeguroDao;
import entidades.Seguro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletAgregarSeguro")
public class ServletAgregarSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SeguroDao seguroDao = new SeguroDao();
	private TipoSeguroDao tipoDao = new TipoSeguroDao();
	
	private void cargarFormulario(HttpServletRequest request)
	{
		request.setAttribute("proximoId", seguroDao.proximoId());
		request.setAttribute("listaTipos", tipoDao.listarTipoSeguro());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cargarFormulario(request);
		request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mensaje = "";
		
		try
		{
			Seguro s = new Seguro();
			s.setDescripcion(request.getParameter("descripcion"));
			s.setIdTipo(Integer.parseInt(request.getParameter("idTipo")));
            s.setCostoContratacion(Double.parseDouble(request.getParameter("costoContratacion")));
            s.setCostoAsegurado(Double.parseDouble(request.getParameter("costoMaximo")));
            
            boolean ok = seguroDao.agregar(s);
            
            mensaje = ok ? "Seguro agregado correctamente." : "No se pudo agregar el seguro.";
            
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		request.setAttribute("mensaje", mensaje);
        cargarFormulario(request); 
        request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
	}

}
