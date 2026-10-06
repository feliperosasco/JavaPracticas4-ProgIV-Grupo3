package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.TipoSeguro;

public class TipoSeguroDao {
	
	
	
	public TipoSeguroDao() {
		
	}
	
	public ArrayList<TipoSeguro>listarTipoSeguro(){
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			ArrayList<TipoSeguro> lista = new ArrayList<TipoSeguro>();
			Connection cn = null;
			try
			{
				cn = Conexion.getConexion(); // crea la base de datos a la cual vincular
				Statement st = cn.createStatement();
				
				ResultSet rs = st.executeQuery("SELECT idTipo, descripcion FROM tipoSeguros");
				
				while(rs.next()) {
					
					TipoSeguro tipo = new TipoSeguro(
		                    rs.getInt("idTipo"),
		                    rs.getString("descripcion")
		                    );
					
					lista.add(tipo);
				}
			}
			catch(Exception e) 
			{
				e.printStackTrace(); // muestra el error por consola
			}finally {
				
			}
			
			return lista;
		}
	
	//prueba codigo
	//public static void main(String[] args) {

       // TipoSeguroDao dao = new TipoSeguroDao();

        //ArrayList<TipoSeguro> lista = dao.listarTipoSeguro();

        //for (TipoSeguro tipo : lista) {
        //    System.out.println(
        //       tipo.getIdTipo() + " - " + tipo.getDescripcion()
        //    );
        //}
   // }

}
