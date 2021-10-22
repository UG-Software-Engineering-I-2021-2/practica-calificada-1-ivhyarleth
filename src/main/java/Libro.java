import java.util.ArrayList;

public class Libro {
    private Integer id;
    private String nombre;
    private Integer anho;
    private Autor autor;
    private ArrayList<Copia> copias;

    public ArrayList<Copia> getCopias() {
        return copias;
    }

    public void setCopias(ArrayList<Copia> copias) {
        this.copias = copias;
    }

    public Integer getId() {
        return id;
    }

    public Libro(Integer id, String nombre, Integer anho, Autor autor) {
        this.id = id;
        this.nombre = nombre;
        this.anho = anho;
        this.autor = autor;
        this.copias = new ArrayList<>();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
