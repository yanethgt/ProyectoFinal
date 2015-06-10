/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uan.fis.jeesample.util.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC Connection Factory definition,
 */
public interface IConnectionFactory {
    
    Connection getConnection() throws SQLException;
    
}
