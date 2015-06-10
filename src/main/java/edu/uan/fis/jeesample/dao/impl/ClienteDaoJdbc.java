package edu.uan.fis.jeesample.dao.impl;

import edu.uan.fis.jeesample.dao.ClienteDao;
import edu.uan.fis.jeesample.dto.Cliente;
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
 * Product DAO implementation using JDBC
 */

public class ClienteDaoJdbc implements ClienteDao {
     
    @Override
    public Cliente create(Cliente cliente) {
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
            stmt.executeUpdate("INSERT INTO Cliente VALUES(" + cliente.getClienteId() + ",'" + cliente.getName() + "')");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error creating a new cliente", ex);
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente;
    }
    
    @Override
    public Cliente update(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.createConnection();
            String update = "UPDATE clientes SET Cliente = ? WHERE Id_Cliente = ?";
            stmt = conn.prepareStatement(update);
            stmt.setString(1, cliente.getName());
            stmt.setInt(2, cliente.getClienteId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while updating a cliente", ex);
        } finally {
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return cliente;
    }
    
    @Override
    public void delete(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.createConnection();
            String update = "DELETE FROM Cliente WHERE Id_Cliente = ?";
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, cliente.getClienteId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while removing a cliente", ex);
        } finally {
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
    }
    
    @Override
    public Cliente findById(Integer clienteId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            conn = ConnectionManager.createConnection();
            String query = "SELECT Id_Cliente, Cliente FROM Clientes WHERE Id_Cliente = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, clienteId);
            rs = stmt.executeQuery();
            if(rs.next()) {
                cliente = new Cliente();
                cliente.setClienteId(rs.getInt("Id_Cliente"));
                cliente.setName(rs.getString("Cliente"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while looking for a Cliente", ex);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return cliente;
    }
    
    @Override
    public List<Cliente> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = ConnectionManager.createConnection();
            String query = "SELECT Id_Cliente, Cliente FROM Clientes";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(rs.getInt("Id_Cliente"));
                cliente.setName(rs.getString("Cliente"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while finding Clientes", ex);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return clientes;
    }
}
