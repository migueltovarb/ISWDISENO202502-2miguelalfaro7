import java.util.ArrayList;
import java.util.List;


class SalaEstudio {
    private int numeroSala;
    private int capacidadMaxima;
    private boolean disponible;

    public SalaEstudio(int numeroSala, int capacidadMaxima, boolean disponible) {
        this.numeroSala = numeroSala;
        this.capacidadMaxima = capacidadMaxima;
        this.disponible = disponible;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Sala " + numeroSala + " (Capacidad: " + capacidadMaxima + 
               ", Disponible: " + (disponible ? "Sí" : "No") + ")";
    }
}

class Estudiante {
    private String nombre;
    private String codigoInstitucional;
    private String programa;

    public Estudiante(String nombre, String codigoInstitucional, String programa) {
        this.nombre = nombre;
        this.codigoInstitucional = codigoInstitucional;
        this.programa = programa;
    }

    public String getCodigoInstitucional() {
        return codigoInstitucional;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigoInstitucional + " - " + programa + ")";
    }
}


class Reserva {
    private Estudiante estudiante;
    private SalaEstudio sala;
    private String fecha;
    private String hora;

    public Reserva(Estudiante estudiante, SalaEstudio sala, String fecha, String hora) {
        this.estudiante = estudiante;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public SalaEstudio getSala() {
        return sala;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "Reserva → " + estudiante + " | Sala " + sala.getNumeroSala() + 
               " | " + fecha + " " + hora;
    }
}


class SistemaReserva {
    private List<SalaEstudio> salas;
    private List<Estudiante> estudiantes;
    private List<Reserva> reservas;

    public SistemaReserva() {
        salas = new ArrayList<>();
        estudiantes = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public void registrarSala(SalaEstudio sala) {
        salas.add(sala);
        System.out.println(" Sala registrada: " + sala);
    }

    public void registrarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        System.out.println(" Estudiante registrado: " + estudiante);
    }

    public void mostrarSalasDisponibles() {
        System.out.println(" Salas disponibles:");
        for (SalaEstudio sala : salas) {
            if (sala.isDisponible()) {
                System.out.println(sala);
            }
        }
    }

    public boolean reservarSala(Estudiante estudiante, SalaEstudio sala, String fecha, String hora) {
        for (Reserva r : reservas) {
            if (r.getSala().getNumeroSala() == sala.getNumeroSala() &&
                r.getFecha().equals(fecha) &&
                r.getHora().equals(hora)) {
                System.out.println("⚠ Error: Sala ya reservada en esa fecha y hora.");
                return false;
            }

            if (r.getSala().getNumeroSala() == sala.getNumeroSala() &&
                r.getEstudiante().getCodigoInstitucional().equals(estudiante.getCodigoInstitucional()) &&
                r.getFecha().equals(fecha) &&
                r.getHora().equals(hora)) {
                System.out.println("⚠ Error: Ya existe una reserva idéntica.");
                return false;
            }
        }

        Reserva nueva = new Reserva(estudiante, sala, fecha, hora);
        reservas.add(nueva);
        sala.setDisponible(false); 
        System.out.println(" Reserva exitosa: " + nueva);
        return true;
    }

    public void consultarHistorial(String codigoEstudiante) {
        System.out.println(" Historial de reservas de " + codigoEstudiante + ":");
        for (Reserva r : reservas) {
            if (r.getEstudiante().getCodigoInstitucional().equals(codigoEstudiante)) {
                System.out.println(r);
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        SistemaReserva sistema = new SistemaReserva();

        SalaEstudio sala1 = new SalaEstudio(1, 6, true);
        SalaEstudio sala2 = new SalaEstudio(2, 10, true);
        sistema.registrarSala(sala1);
        sistema.registrarSala(sala2);

        Estudiante e1 = new Estudiante("Juan Pérez", "A001", "Ingeniería");
        Estudiante e2 = new Estudiante("Ana Gómez", "A002", "Derecho");
        sistema.registrarEstudiante(e1);
        sistema.registrarEstudiante(e2);

        sistema.mostrarSalasDisponibles();

        sistema.reservarSala(e1, sala1, "2025-10-12", "09:00");
        sistema.reservarSala(e2, sala1, "2025-10-12", "09:00"); 
        sistema.reservarSala(e2, sala2, "2025-10-12", "11:00");

        sistema.consultarHistorial("A001");
    }
}