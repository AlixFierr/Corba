package Asistencia;

import Conexion.ConexionBD;
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
public class Cargo extends AsistenciaApp.cargoPOA {
    
    private ORB orb;
    private final ConexionBD mysql = new ConexionBD();
    private final Connection conn = mysql.conectar();
    private String SQL = "";
    
    public void setORB(ORB orb) {
        this.orb = orb;
    }

    @Override
    public boolean insertarCargo(String descripcion) {
        boolean resultado = false;
        
        try {
            SQL = "INSERT INTO cargo (descripcion) VALUES ('" + descripcion +"')";
            try (Statement stmt = conn.createStatement()) {
                int valor = stmt.executeUpdate(SQL);
                
                if (valor > 0) {
                    resultado = true;
                }
            }
            mysql.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar un nuevo cargo: " + e.getMessage());
        }
        
        return resultado;
    }

    @Override
    public String consultarCargo(int idcargo) {
        String lista = "";
        
        try {
            SQL = "SELECT * FROM cargo WHERE idcargo = " + idcargo;
            Statement stmt = conn.createStatement();
            try (ResultSet rst = stmt.executeQuery(SQL)) {
                while (rst.next()) {
                    lista += "Cargo: " + rst.getString(1) + "\n";
                }
                JOptionPane.showMessageDialog(null, lista);
            }
            mysql.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error en el catch: " + e.getMessage());
        }
        
        return lista;
    }

    @Override
    public boolean actualizarCargo(int idcargo, String descripcion) {
        boolean resultado = false;
        
        try {
            SQL = "UPDATE cargo SET descripcion = " + descripcion + " WHERE idcargo = " + idcargo;
            try (Statement stmt = conn.createStatement()) {
                int valor = stmt.executeUpdate(SQL);
                
                if (valor > 0) {
                    resultado = true;
                }
            }
            mysql.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al actualizar el cargo: " + e.getMessage());
        }
        
        return resultado;
    }

    @Override
    public boolean eliminarCargo(int idcargo) {
        boolean resultado = false;
        
        try {
            SQL = "DELETE FROM cargo WHERE idcargo = " + idcargo;
            try (Statement stmt = conn.createStatement()) {
                int valor = stmt.executeUpdate(SQL);
                
                if (valor > 0) {
                    resultado = true;
                }
            }
            mysql.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el cargo: " + e.getMessage());
        }
        
        return resultado;
    }

    @Override
    public String listarCargo() {
        String lista = "";
        
        try {
            SQL = "SELECT * FROM cargo";
            Statement stmt = conn.createStatement();
            try (ResultSet rst = stmt.executeQuery(SQL)) {
                while (rst.next()) {
                    lista += "Cargo: " + rst.getString(1) + "\n";
                }
                JOptionPane.showMessageDialog(null, lista);
            }
            mysql.desconectar();
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error en el catch: " + e.getMessage());
        }
        
        return lista;
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
    
    public DefaultTableModel cargarTablaCargo() {
        DefaultTableModel modelo;
        String[] titulos = {"IdCargo", "Descripcion"};
        String[] registros = new String[2];
        modelo = new DefaultTableModel(null, titulos);
        SQL = "SELECT * FROM cargo";
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultado = stmt.executeQuery(SQL);
            while (resultado.next()) {            
                registros[0] = resultado.getString("idcargo");
                registros[1] = resultado.getString("descripcion");
                
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error: " + e.getMessage());
            return null;
        }
    }
    
}
