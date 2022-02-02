package Biblioteca;

public class Llibre {

	@Override
	public String toString() {
		return "Llibre [id=" + id + ", titol=" + titol + ", autor=" + autor + ", any=" + any + ", editorial="
				+ editorial + ", pagines=" + pagines + "]";
	}

	private String id;
	private String titol;
	private String autor;
	private String any;
	private String editorial;
	private String pagines;
	
	public Llibre() {
		
	}

	public Llibre(String id, String titol, String autor, String any, String editorial, String pagines) {
		super();
		this.id = id;
		this.titol = titol;
		this.autor = autor;
		this.any = any;
		this.editorial = editorial;
		this.pagines = pagines;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAny() {
		return any;
	}

	public void setAny(String any) {
		this.any = any;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getPagines() {
		return pagines;
	}

	public void setPagines(String pagines) {
		this.pagines = pagines;
	}
	
}
