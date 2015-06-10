package edu.uan.fis.jeesample.dao.impl;

import edu.uan.fis.jeesample.dao.EmpleadoDao;
import edu.uan.fis.jeesample.dto.Empleado;
import edu.uan.fis.jeesample.util.jdbc.ConnectionManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Empleado DAO implementation using JDBC
 */

public class EmpleadoDaoJdbc implements EmpleadoDao {
   
    @Override
    public Empleado create(Empleado empleado) {
        // Sample code using plain JDBC. Next methods use the ConnectionManager utility class
        Connection conn = null;
        Statement stmt = null;
        try {
            // 1. Register the JDBC driver
            //    for Derby: org.apache.derby.jdbc.ClientDriver
            //    for MySQL: com.mysql.jdbc.Driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // 2. Get the connection for the URL 
            //    MySQL: jdbc:mysql://address:port/dbname?user=username&password=userpassword
            //    Derby: jdbc:derby://localhost:1527/dbname;create=true;user=username;password=userpassword
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/YANETH" + "user=yaneth&password=comico");
            // 3. Creates the product in the database
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Empleado VALUES(" + empleado.getEmpleadoId() + ",'" + empleado.getName() + "')");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error creating a new empleado", ex);
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return empleado;
    }
    
    @Override
    public Empleado update(Empleado empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.createConnection();
            String update = "UPDATE Empleado SET nom_empleado = ? WHERE Id_Empleado = ?";
            stmt = conn.prepareStatement(update);
            stmt.setString(1, empleado.getName());
            stmt.setInt(2, empleado.getEmpleadoId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while updating a empleado", ex);
        } finally {
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return empleado;
    }
    
    @Override
    public void delete(Empleado empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.createConnection();
            String update = "DELETE FROM Empleado WHERE Id_empleado = ?";
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, empleado.getEmpleadoId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while removing a empleado", ex);
        } finally {
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
    }
    
    @Override
    public Empleado findById(Integer empleadoId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleado empleado = null;
        try {
            conn = ConnectionManager.createConnection();
            String query = "SELECT Id_Empleado, Empleado FROM Empelado WHERE Id_producto = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, empleadoId);
            rs = stmt.executeQuery();
            if(rs.next()) {
                empleado = new Empleado();
                empleado.setEmpleadoId(rs.getInt("Id_empleado"));
                empleado.setName(rs.getString("Empleado"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while looking for a empleado", ex);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return empleado;
    }
    
    @Override
    public List<Empleado> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empleado> empleados = new ArrayList<>();
        try {
            conn = ConnectionManager.createConnection();
            String query = "SELECT Id_Empleado, Empleado FROM empleados";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setEmpleadoId(rs.getInt("Id_Empleado"));
                empleado.setName(rs.getString("Empleado"));
                empleados.add(empleado);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while finding Empleados", ex);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return empleados;
    }
}
