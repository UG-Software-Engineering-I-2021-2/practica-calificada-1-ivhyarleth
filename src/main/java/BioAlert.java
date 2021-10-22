import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class BioAlert {
    private ArrayList<Autor> autores;
    private ArrayList<Libro> libros;
    private ArrayList<Lector> lectores;
    private ArrayList<Copia> copias;

    public BioAlert(ArrayList<Autor> autores, ArrayList<Libro> libros, ArrayList<Lector> lectores, ArrayList<Copia> copias) {
        this.autores = autores;
        this.libros = libros;
        this.lectores = lectores;
        this.copias = copias;
    }

    public void startDataBase() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        autores.add(new Autor("Teofilo Chambilla", ft.parse("1980-10-11")));
        autores.add(new Autor("Andrew Summerville", ft.parse("1970-12-09")));
        libros.add(new Libro(0, "Ingenieria de Software I", 2010, autores.get(0)));
        libros.add(new Libro(1, "Ingenieria de Software II", 2011, autores.get(1)));
        libros.add(new Libro(2, "Base de Datos I", 2017, autores.get(0)));
        libros.add(new Libro(3, "Base de Datos II", 2021, autores.get(1)));
        copias.add(new Copia(0, libros.get(0), "biblioteca"));
        copias.add(new Copia(1, libros.get(0), "reparacion"));
        copias.add(new Copia(2, libros.get(1), "biblioteca"));
        copias.add(new Copia(3, libros.get(1), "biblioteca"));
        copias.add(new Copia(4, libros.get(1), "biblioteca"));
        copias.add(new Copia(5, libros.get(2), "biblioteca"));
        copias.add(new Copia(6, libros.get(2), "biblioteca"));
        copias.add(new Copia(7, libros.get(2), "biblioteca"));
        copias.add(new Copia(8, libros.get(3), "biblioteca"));
        libros.get(0).getCopias().add(copias.get(0));
        libros.get(0).getCopias().add(copias.get(1));
        libros.get(1).getCopias().add(copias.get(2));
        libros.get(1).getCopias().add(copias.get(3));
        libros.get(1).getCopias().add(copias.get(4));
        libros.get(2).getCopias().add(copias.get(5));
        libros.get(2).getCopias().add(copias.get(6));
        libros.get(2).getCopias().add(copias.get(7));
        libros.get(3).getCopias().add(copias.get(8));
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a Bioalert");
        int option = 0;
        boolean finish = false;
        while (!finish) {
            while (option == 0) {
                System.out.println("Indique, por favor, si ya se encuentra suscrito (1) o desea suscribirse (2): ");
                option = scanner.nextInt();
                if (option == 1) {
                    System.out.println("Indique, por favor, su numero de identificacion: ");
                    int id = scanner.nextInt();
                    if (existeLector(id)) {
                        pedirLibro(id);
                    } else {
                        System.out.println("No existe ese numero de identificacion, intente nuevamente.");
                    }
                } else if (option == 2) {
                    Integer id = crearUsuario();
                    pedirLibro(id);
                } else {
                    System.out.println("Opcion elegida incorrectamente, intente nuevamente por favor");
                    option = 0;
                }
            }
            System.out.println("Desea continuar? (1) Si (2) No");
            option = scanner.nextInt();
            if (option == 2) {
                finish = true;
            }
        }

    }

    public boolean existeLector(int id) {
        boolean encontrado = false;
        int i = 0;
        while (i < lectores.size() || !encontrado) {
            if (lectores.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public Integer crearUsuario(){
        int id = lectores.size();
        lectores.add(new Lector(id));
        return id;
    }

    public void pedirLibro(Integer id) {
        System.out.println("Indique, por favor, que libro desea adquirir: ");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();
        Lector lector = lectores.get(id);
        int copiaBuscada = buscarLibro(query);
        if (copiaBuscada != -1) {
            if (lector.getPrestamos().size() < 3) {
                if (!tieneMora(lector)){
                    System.out.println("El libro se le prestara por un maximo de 30 dias, sino incurrira en multa.");
                    Copia libroAPrestar = copias.get(copiaBuscada);
                    libroAPrestar.setEstado("prestado");
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, 30);
                    lector.getPrestamos().add(new Prestamo(lector.getPrestamos().size(), copiaBuscada, lector.getId(), new Date(), c.getTime()));
                } else {
                    System.out.println("Actualmente usted cuenta con un impedimento de pedir libros. Por favor, regule sus prestamos.");
                }
            } else {
                System.out.println("Ya cuenta con un maximo de 3 libros prestados, no puede pedir mas prestamos");
            }
        }
    }

    public boolean tieneMora(Lector lector) {
        boolean mora = false;
        int i = 0;
        while(i < lector.getPrestamos().size() || !mora) {
            if (lector.getPrestamos().get(i).getFechaDevolucion().before(new Date())) {
                mora = true;
            }
            i++;
        }
        return mora;
    }

    public int buscarLibro(String query) {
        int indexLibro = -1;
        int indexCopia = -1;
        int i = 0;
        while (i < libros.size() || indexLibro != -1){
            if (libros.get(i).getNombre().equals(query)){
                indexLibro = i;
            }
            i++;
        }
        ArrayList<Copia> copiasQuery = libros.get(indexLibro).getCopias();
        i = 0;
        while (i < copiasQuery.size() || indexCopia != -1){
            if (copiasQuery.get(i).getEstado().equals("biblioteca")) {
                indexCopia = copiasQuery.get(i).getId();
            }
            i++;
        }
        return indexCopia;
    }
}
