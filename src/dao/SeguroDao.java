package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entidades.Seguro;
public class SeguroDao {

	public List<Seguro> listarSeguros() {
		return listarSeguros(null);
	}

	public List<Seguro> listarSeguros(Integer idTipo) {
		List<Seguro> seguros = new ArrayList<Seguro>();
		String sql = "SELECT idSeguro, descripcion, idTipo, costoContratacion, costoAsegurado FROM seguros";
		if (idTipo != null && idTipo > 0) {
			sql += " WHERE idTipo = ?";
		}
		sql += " ORDER BY idSeguro";

		try (Connection cn = Conexion.getConexion();
		     PreparedStatement ps = cn.prepareStatement(sql)) {
			if (idTipo != null && idTipo > 0) {
				ps.setInt(1, idTipo);
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Seguro seguro = new Seguro(
						rs.getInt("idSeguro"),
						rs.getString("descripcion"),
						rs.getInt("idTipo"),
						rs.getDouble("costoContratacion"),
						rs.getDouble("costoAsegurado")
					);
					seguros.add(seguro);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seguros;
	}

	public int proximoId() {
		int id = 1;
		String sql = "SELECT COALESCE(MAX(idSeguro), 0) + 1 FROM seguros";
		try (Connection cn = Conexion.getConexion();
		     PreparedStatement ps = cn.prepareStatement(sql);
		     ResultSet rs = ps.executeQuery()) {
			if (rs.next()) id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean agregar(Seguro s) {
		String sql = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?,?,?,?)";
		try (Connection cn = Conexion.getConexion();
		     PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, s.getDescripcion());
			ps.setInt(2, s.getIdTipo());
			ps.setDouble(3, s.getCostoContratacion());
			ps.setDouble(4, s.getCostoAsegurado());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}


