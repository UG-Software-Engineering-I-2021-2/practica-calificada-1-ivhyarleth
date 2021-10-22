import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Main {
    public static void main(String[] args) throws ParseException {
        BioAlert bioAlertInstance = new BioAlert(new ArrayList<Autor>(), new ArrayList<Libro>(), new ArrayList<Lector>(), new ArrayList<Copia>());
        bioAlertInstance.startDataBase();
        bioAlertInstance.start();
    }
}