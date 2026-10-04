package entidades;

public class TipoSeguro {
	private int idTipo;
	private String descripcion;
	
	public TipoSeguro()
	{}
	
	public TipoSeguro(int idTipo, String descripcion)
	{
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoSeguro [idTipo=" + idTipo + ", descripcion=" + descripcion + "]";
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdTipo() {
		return idTipo;
	}
	
}
