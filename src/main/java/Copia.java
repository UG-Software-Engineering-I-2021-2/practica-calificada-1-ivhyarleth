public class Copia {
    private Integer id;
    private Libro referenciaLibro;
    private String estado;

    public Copia(Integer id, Libro referenciaLibro, String estado) {
        this.id = id;
        this.referenciaLibro = referenciaLibro;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Libro getReferenciaLibro() {
        return referenciaLibro;
    }

    public void setReferenciaLibro(Libro referenciaLibro) {
        this.referenciaLibro = referenciaLibro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
