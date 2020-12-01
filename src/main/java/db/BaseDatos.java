package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class
BaseDatos {

    private String database;
    private Connection connection;
    private Statement statement;

    public BaseDatos(String db) throws ClassNotFoundException, SQLException {
        this.database = db;
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + database);
        this.statement = connection.createStatement();
    }

    public Connection getConnection() {
        return connection;
    }

    public List<Usuario> getUsuarioByName(String nombre, String password) throws SQLException {
        ResultSet rs = this.statement.executeQuery("select * from usuario where upper(nombre)='" + nombre.toUpperCase() + "' and password='" + password.toUpperCase() + "'");
        List<Usuario> usuario = new ArrayList();
        while (rs.next()) {
            Usuario temp = new Usuario();
            temp.setIdUsuario(rs.getInt("id_usuario"));
            temp.setIdUsuario(rs.getInt("nombre"));
            temp.setIdUsuario(rs.getInt("password"));
            temp.setIdUsuario(rs.getInt("rol"));
            usuario.add(temp);
        }
        return usuario;

    }

    public void VerDoctor() throws SQLException {
        String sql = "SELECT Id_Doctor, Nombre, ApellidoPat, ApellidoMat, Especialidad FROM Doctores";
        try (Statement stmt = this.connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("Id_Doctor") +  "\t" +
                        rs.getString("Nombre") + "\t" +
                        rs.getString("ApellidoPat") + "\t" +
                        rs.getString("ApellidoMat") + "\t" +
                        rs.getString("Especialidad"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void VerPacientes() throws SQLException {
        String sql = "SELECT Id_Paciente, PNombre, PApellidoPat, PApellidoMat, FROM Paciente";
        try (Statement stmt = this.connection.createStatement();
             ResultSet sr    = stmt.executeQuery(sql)){

            while (sr.next()) {
                System.out.println(sr.getInt("Id_Paciente") +  "\t" +
                        sr.getString("PNombre") + "\t" +
                        sr.getString("PApellidoPat") + "\t" +
                        sr.getString("PApellidoMat"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void VerHorariosParaCitas() throws SQLException {
        String sql = "SELECT Id_Horario, Fecha, Hora, FROM Horarios";
        try (Statement stmt = this.connection.createStatement();
             ResultSet ab    = stmt.executeQuery(sql)){

            while (ab.next()) {
                System.out.println(ab.getInt("Horario") +  "\t" +
                        ab.getString("Fecha") + "\t" +
                        ab.getString("Hora") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean AgregarDoctor(String Nom, String ApellPat, String ApellMat, String Esp) throws SQLException {
        String sql = "insert into Doctores(Nom, ApellPat, ApellMat, Esp) "
                + "values (?,?,?,?)";
        PreparedStatement prepStmt = this.connection.prepareStatement(sql);
        prepStmt.setString(1, Nom);
        prepStmt.setString(2, ApellPat);
        prepStmt.setString(3, ApellMat);
        prepStmt.setString(4, Esp);
        return prepStmt.execute();
    }

    public boolean AgregarPaciente(String PNombre, String PApellidoPat, String PApellidoMat) throws SQLException {
        String sql = "insert into Paciente(PNombre, PApellidoPat, PApellidoMat)"
                + "values (?,?,?)";
        PreparedStatement prepStmt = this.connection.prepareStatement(sql);
        prepStmt.setString(1, PNombre);
        prepStmt.setString(2, PApellidoPat);
        prepStmt.setString(3, PApellidoMat);
        return prepStmt.execute();
    }

    public boolean CrearCita(Integer Doctor, Integer Paciente, Integer Horario) throws SQLException{

        String sql = "insert into Citas(Id_Doctor, Id_Paciente, Id_Horario)" + "values(?,?,?)";
        PreparedStatement prepStmt = this.connection.prepareStatement(sql);
        prepStmt.setInt(1, Doctor);
        prepStmt.setInt(2, Paciente);
        prepStmt.setInt(3, Horario);
        return prepStmt.execute();
    }

    public void VerCitasPendientes() throws SQLException {
        String sql = "SELECT Id_Cita, Id_Doctor, Id_Paciente, Id_Horario FROM Citas";
        try (Statement stmt = this.connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("Id_Cita") +  "\t" +
                        rs.getInt("Id_Doctor") + "\t" +
                        rs.getInt("Id_Paciente") + "\t" +
                        rs.getInt("Id_Horario"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void VerDoctoryPacienteConCita() throws SQLException {
        String sql = "SELECT Id_Paciente, Id_Doctor, Id_Horario FROM Citas";
        try (Statement stmt = this.connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(
                        rs.getString("Id_Paciente") + "\t" +
                                rs.getString("Id_Doctor")+"\t"+
                                rs.getString("Id_Horario"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}



