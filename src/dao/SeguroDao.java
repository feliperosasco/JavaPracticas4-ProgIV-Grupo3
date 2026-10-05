package dao;
import java.sql.*;

import entidades.Seguro;
public class SeguroDao {

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


