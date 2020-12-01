package db;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ConsultorioAdmin {

    static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int seleccion;
        String user = "";
        String password = "";
        BaseDatos persist = new BaseDatos("consultorio.db");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese su usuario y contraseña para iniciar");
            System.out.println("Usuario:");
            user = scanner.nextLine();
            System.out.println("Contraseña:");
            password = scanner.nextLine();
            List<Usuario> usuario = persist.getUsuarioByName(user, password);
            if (!usuario.isEmpty()) {
                while (true) {
                    System.out.println("(1) Dar de alta doctores.");
                    System.out.println("(2) Dar de alta pacientes.");
                    System.out.println("(3) Crear una cita con fecha y hora.");
                    System.out.println("(4) Ver doctores y sus pacientes correspondientes");
                    System.out.println("(5) Muestra de doctores");
                    System.out.println("(6) Muestra de horarios");
                    System.out.println("(7) Muestra de pacientes");
                    System.out.println("(8) Ver citas pendientes");
                    System.out.println("(9) Ver pacientes con sus doctores y citas correspondientes");
                    System.out.println("(0) Salir");
                    System.out.println("\nPor favor ingrese una opción: ");
                    // Fin de Menu
                    // Try Anidado
                    try {
                        // Asigna token Integer parseado
                        seleccion = scanner.nextInt();
                        switch (seleccion) {
                            case 0:
                                System.out.println("Saliendo..");
                                logger.info("Saliendo...");
                                return;
                            case 1:
                                Scanner Nombre = new Scanner(System.in);
                                Scanner ApellidoPat = new Scanner(System.in);
                                Scanner ApellidoMat = new Scanner(System.in);
                                Scanner Especialidad = new Scanner(System.in);
                                System.out.println("Ingresa nombre del doctor");
                                String Nom = Nombre.next();
                                System.out.println("Ingresa apellido paterno del doctor");
                                String ApellPat = ApellidoPat.next();
                                System.out.println("Ingresa apellido materno del doctor");
                                String ApellMat = ApellidoMat.next();
                                System.out.println("Agrega información de la especialidad del doctor");
                                String Esp = Especialidad.next();
                                persist.AgregarDoctor( Nom, ApellPat, ApellMat, Esp);
                                break;
                            case 2:
                                Scanner PNombre = new Scanner(System.in);
                                Scanner PApellidoPat = new Scanner(System.in);
                                Scanner PApellidoMat = new Scanner(System.in);
                                System.out.println("Ingresa el nombre paciente");
                                String PacNombre = PNombre.next();
                                System.out.println("Ingresa el apellido paterno del paciente");
                                String PacApellidoPat = PApellidoPat.next();
                                System.out.println("Ingresa el apellido materno paciente");
                                String PacApellidoMat = PApellidoMat.next();
                                persist.AgregarPaciente(PacNombre, PacApellidoPat, PacApellidoMat);
                                break;
                            case 3:
                                Scanner Doctor = new Scanner(System.in);
                                Scanner Paciente = new Scanner(System.in);
                                Scanner Horario = new Scanner(System.in);
                                persist.VerDoctor();
                                System.out.println("Ingresa ID del doctor");
                                Integer Doc = Doctor.nextInt();
                                persist.VerPacientes();
                                System.out.println("Ingresa ID del paciente");
                                Integer Pac = Paciente.nextInt();
                                persist.VerCitasPendientes();
                                System.out.println("Ingresa ID del horario de tu cita");
                                Integer Hor = Horario.nextInt();
                                persist.CrearCita(Doc, Pac, Hor);

                                break;
                            case 4:
                                System.out.println("Doctor");
                                persist.VerDoctor();
                                System.out.println("");
                                System.out.println("Pacientes");
                                persist.VerPacientes();
                                System.out.println("");
                                System.out.println("Doctor y paciente correspondiente con cita");
                                persist.VerDoctoryPacienteConCita();
                                break;
                            case 5:
                                persist.VerDoctor();
                                break;
                            case 6:
                                persist.VerHorariosParaCitas();
                                break;
                            case 7:
                                persist.VerPacientes();
                                break;
                            case 8:
                                persist.VerCitasPendientes();
                            case 9:
                                System.out.println("Pacientes");
                                persist.VerPacientes();
                                System.out.println("");
                                System.out.println("Doctores");
                                persist.VerDoctor();
                                System.out.println("");
                                System.out.println("Horarios");
                                persist.VerHorariosParaCitas();
                                System.out.println("");
                            default:
                                System.err.println("Opción inválida.");
                                logger.error("Opción inválida: {}", seleccion);
                                break;
                        }

                    } catch (Exception ex) {
                        logger.error("{}: {}", ex.getClass(), ex.getMessage());
                        System.err.format("Ocurrió un error. Para más información consulta el log de la aplicación.");
                        scanner.next();
                    }
                }
            } else {
                System.out.println("No tiene autorización");
            }
        } catch (Exception ex) {
            logger.error("{}: {}", ex.getClass(), ex.getMessage());
            System.err.format("Ocurrió un error. Para más información consulta el log de la aplicación.");
        } finally {
            persist.getConnection().close();
        }
    }
}
