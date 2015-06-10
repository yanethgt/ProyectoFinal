package edu.uan.fis.jeesample.util.jdbc.impl;

import edu.uan.fis.jeesample.util.jdbc.IConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Connection Factory implementation
 */
public class ConnectionFactory implements IConnectionFactory {
    
    private static ConnectionFactory connectionFactory;
    
    private ConnectionFactory() {
        
    }
    
    public static ConnectionFactory getInstance() {
        if(connectionFactory==null) {
            // Create the singleton instance for this factory
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/jeesample;create=true;user=arq;password=arq");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}
