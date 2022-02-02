package APP;

public class Llibre {

	private int id;
	private String titol;
	private String autor;
	private String anyNaixement;
	private String anyPublicacio;
	private String editorial;
	private String pagines;
	
	public Llibre() {
		
	}

	public Llibre(String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String pagines) {
		this.titol = titol;
		this.autor = autor;
		this.anyNaixement = anyNaixement;
		this.anyPublicacio = anyPublicacio;
		this.editorial = editorial;
		this.pagines = pagines;
	}
	
	public String mostrarDetalles() {
		return " Autor: "+autor+" Any naixement: "+anyNaixement+" Any publicacio: "+anyPublicacio+" Editorial: "+editorial+" Pagines: "+pagines;
	}
	public String mostrarIdTitol() {
		return "ID: "+id+" Titol: "+titol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public String getAnyNaixement() {
		return anyNaixement;
	}

	public void setAnyNaixement(String anyNaixement) {
		this.anyNaixement = anyNaixement;
	}

	public String getAnyPublicacio() {
		return anyPublicacio;
	}

	public void setAnyPublicacio(String anyPublicacio) {
		this.anyPublicacio = anyPublicacio;
	}
	
}
