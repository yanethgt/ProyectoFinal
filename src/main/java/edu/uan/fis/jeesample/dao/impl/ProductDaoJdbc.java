package edu.uan.fis.jeesample.dao.impl;

import edu.uan.fis.jeesample.dao.ProductDao;
import edu.uan.fis.jeesample.dto.Product;
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
public class ProductDaoJdbc implements ProductDao {
    
    @Override
    public Product create(Product product) {
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
            stmt.executeUpdate("INSERT INTO Producto VALUES(" + product.getProductId() + ",'" + product.getName() + "')");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error creating a new product", ex);
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return product;
    }
    
    @Override
    public Product update(Product product) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.createConnection();
            String update = "UPDATE Producto SET Producto = ? WHERE Id_Producto = ?";
            stmt = conn.prepareStatement(update);
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getProductId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while updating a product", ex);
        } finally {
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return product;
    }
    
    @Override
    public void delete(Product product) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.createConnection();
            String update = "DELETE FROM Producto WHERE Id_producto = ?";
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, product.getProductId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error while removing a product", ex);
        } finally {
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
    }
    
    @Override
    public Product findById(Integer productId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Product product = null;
        try {
            conn = ConnectionManager.createConnection();
            String query = "SELECT Id_Producto, Producto FROM productos WHERE Id_Producto = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, productId);
            rs = stmt.executeQuery();
            if(rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("Producto"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while looking for a product", ex);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return product;
    }
    
    @Override
    public List<Product> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionManager.createConnection();
            String query = "SELECT Id_Producto, Producto FROM products";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("Id_Producto"));
                product.setName(rs.getString("Producto"));
                products.add(product);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error while finding products", ex);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(stmt);
            ConnectionManager.closeConnection(conn);
        }
        return products;
    }

    
}
