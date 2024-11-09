package model;

public class Tipo {

	private int idtipo;
	private String name;

	public Tipo() {
		
	}

	public Tipo(int idtipo, String name) {
		this.idtipo = idtipo;
		this.name = name;
	}

	public int getIdtipo() {
		return idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tipo [idtipo=" + idtipo + ", name=" + name + "]";
	}

}
