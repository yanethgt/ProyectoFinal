package edu.uan.fis.jeesample.dao.impl;

import edu.uan.fis.jeesample.dao.UsuarioDao;
import edu.uan.fis.jeesample.dto.Usuario;
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

public class UsuarioDaoJdbc implements UsuarioDao {
        
    @Override
    public Usuario create(Usuario usuario) {
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
            stmt.executeUpdate("INSERT INTO Usuario VALUES(" + usuario.getUsuarioId() + ",'" + usuario.getName() + "')");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error creating a new usuario", ex);
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }
    
    @Override
    public Usuario update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.createConnection();
            String update = "UPDATE Usuario SET Usuario = ? WHERE Id_Usuario = ?";
            stmt = conn.prepareStatement(update);
            stmt.setString(1, usuario.getName());
            stmt.setInt(2, usuario.getUsuarioId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while updating a usuario", ex);
        } finally {
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return usuario;
    }
    
    @Override
    public void delete(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.createConnection();
            String update = "DELETE FROM usuario WHERE Id_Usuario = ?";
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, usuario.getUsuarioId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while removing a usuario", ex);
        } finally {
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
    }
    
    @Override
    public Usuario findById(Integer usuarioId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            conn = ConnectionManager.createConnection();
            String query = "SELECT Id_Usuario, Usuario FROM Usuarios WHERE Id_Usuario = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, usuarioId);
            rs = stmt.executeQuery();
            if(rs.next()) {
                usuario = new Usuario();
                usuario.setUsuarioId(rs.getInt("Id_Usuario"));
                usuario.setName(rs.getString("Usuario"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while looking for a usuario", ex);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return usuario;
    }
    
    @Override
    public List<Usuario> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = ConnectionManager.createConnection();
            String query = "SELECT Id_Usuario, Usuario FROM Usuarios";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuarioId(rs.getInt("Id_Usuario"));
                usuario.setName(rs.getString("Usuario"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while finding usuario", ex);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return usuarios;
    }
}
