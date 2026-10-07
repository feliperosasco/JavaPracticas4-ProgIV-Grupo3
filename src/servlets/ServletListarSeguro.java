package servlets;

import dao.SeguroDao;
import dao.TipoSeguroDao;
import entidades.Seguro;
import entidades.TipoSeguro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletListarSeguro")
public class ServletListarSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final SeguroDao seguroDao = new SeguroDao();
	private final TipoSeguroDao tipoSeguroDao = new TipoSeguroDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		listar(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		listar(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idTipo = null;
		String idTipoSeleccionado = request.getParameter("idTipo");
		if (idTipoSeleccionado != null && !idTipoSeleccionado.trim().isEmpty()) {
			try {
				int id = Integer.parseInt(idTipoSeleccionado);
				if (id > 0) {
					idTipo = id;
				}
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El tipo de seguro seleccionado no es válido.");
				return;
			}
		}

		List<Seguro> seguros = seguroDao.listarSeguros(idTipo);
		List<TipoSeguro> tipos = tipoSeguroDao.listarTipoSeguro();
		request.setAttribute("listaS", seguros);
		request.setAttribute("listaT", tipos);
		request.setAttribute("idTipoSeleccionado", idTipo);
		request.getRequestDispatcher("ListarSeguro.jsp").forward(request, response);
	}
}
