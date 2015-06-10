package edu.uan.fis.jeesample.util.jdbc;

import edu.uan.fis.jeesample.util.jdbc.impl.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {

    public static Connection createConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }
    
    public static void closeConnection(Connection conn) {
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeStatement(Statement stmt) {
        try {
            if(stmt!=null)
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeResultSet(ResultSet rs) {
        try {
            if(rs!=null)
                rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
