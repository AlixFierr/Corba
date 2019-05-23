package Asistencia;

import Conexion.ConexionBD;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.omg.CORBA.ORB;

/**
 *
 * @author Dan Arevalo
 */
public class Asistencia extends AsistenciaApp.asistenciaPOA {
    
    private ORB orb;
    private final ConexionBD mysql = new ConexionBD();
    private final Connection conn = mysql.conectar();
    private String SQL = "";
    
    public void setORB(ORB orb) {
        this.orb = orb;
    }

    @Override
    public boolean insertarAsistencia(int idpersonal, int identificacion, String fecha, String hora_ingreso) {
        boolean resultado = false;
        
        try {
            SQL = "INSERT INTO asistencia_personal (idpersonal, identificacion, fecha, hora_ingreso) VALUES (" + idpersonal + ", " + identificacion + ", '" + fecha + "', '" + hora_ingreso + "')";
            try (Statement stmt = conn.createStatement()) {
                int valor = stmt.executeUpdate(SQL);
                
                if (valor > 0) {
                    resultado = true;
                }
            }
            mysql.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar un nuevo registro: " + e.getMessage());
        }
        
        return resultado;
    }

    @Override
    public String consultarAsistencia(int idasistencia) {
        String lista = "";
        
        try {
            SQL = "SELECT * FROM asistencia_personal WHERE idasistencia = " + idasistencia;
            Statement stmt = conn.createStatement();
            try (ResultSet rst = stmt.executeQuery(SQL)) {
                while (rst.next()) {
                    lista += "Identificación: " + rst.getLong(3) + " - Fecha: "
                            + rst.getString(4) + " - Hora de Ingreso: "
                            + rst.getString(5) + "\n";
                }
                JOptionPane.showMessageDialog(null, lista);
            }
            mysql.desconectar();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error en el catch: " + e.getMessage());
        }
        
        return lista;
    }

    @Override
    public boolean actualizarAsistencia(int idasistencia, int idpersonal, int identificacion, String fecha, String hora_ingreso) {
        boolean resultado = false;
        
        try {
            SQL = "UPDATE asistencia_personal SET idpersonal = " + idpersonal + ", identificacion = " + identificacion + ", fecha = " + fecha + ", hora_ingreso = " + hora_ingreso + " WHERE idasistencia = " + idasistencia;
            try (Statement stmt = conn.createStatement()) {
                int valor = stmt.executeUpdate(SQL);
                
                if (valor > 0) {
                    resultado = true;
                }
            }
            mysql.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al actualizar el registro: " + e.getMessage());
        }
        
        return resultado;
    }

    @Override
    public boolean eliminarAsistencia(int idasistencia) {
        boolean resultado = false;
        
        try {
            SQL = "DELETE FROM asistencia_personal WHERE idasistencia = " + idasistencia;
            try (Statement stmt = conn.createStatement()) {
                int valor = stmt.executeUpdate(SQL);
                
                if (valor > 0) {
                    resultado = true;
                }
            }
            mysql.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el registro: " + e.getMessage());
        }
        
        return resultado;
    }

    @Override
    public String listarAsistencia() {
        String lista = "";
        
        try {
            SQL = "SELECT * FROM asistencia_personal";
            Statement stmt = conn.createStatement();
            try (ResultSet rst = stmt.executeQuery(SQL)) {
                while (rst.next()) {
                    lista += "Identificación: " + rst.getLong(2) + " - Fecha: "
                            + rst.getString(3) + " - Hora de Ingreso: "
                            + rst.getString(4) + "\n";
                }
                JOptionPane.showMessageDialog(null, lista);
            }
            mysql.desconectar();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error en el catch: " + e.getMessage());
        }
        
        return lista;
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
    
    public DefaultTableModel cargarTablaAsistencia() {
        DefaultTableModel modelo;
        String[] titulos = {"IdAsistencia", "IdPersonal", "Identificación", "Fecha", "Hora de Ingreso"};
        String[] registro = new String[5];
        modelo = new DefaultTableModel(null, titulos);
        SQL = "SELECT * FROM asistencia_personal";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultado = stmt.executeQuery(SQL);
            while (resultado.next()) {                
                registro[0] = resultado.getString("idasistencia");
                registro[1] = resultado.getString("idpersonal");
                registro[2] = resultado.getString("identificacion");
                registro[3] = resultado.getString("fecha");
                registro[4] = resultado.getString("hora_ingreso");
                
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error: " + e.getMessage());
            return null;
        }
    }
    
}
