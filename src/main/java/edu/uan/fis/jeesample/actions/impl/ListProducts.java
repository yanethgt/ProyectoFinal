package edu.uan.fis.jeesample.actions.impl;

import edu.uan.fis.jeesample.actions.CommandResult;
import edu.uan.fis.jeesample.actions.ICommand;
import edu.uan.fis.jeesample.dao.ProductDao;
import edu.uan.fis.jeesample.dao.impl.ProductDaoJdbc;
import edu.uan.fis.jeesample.dto.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sample command action to query all the available products
 * @author wjforero
 */
public class ListProducts implements ICommand {

    @Override
    public CommandResult findAll(HttpServletRequest request, HttpServletResponse response) {
        
        CommandResult result = new CommandResult();
        
        try {
// 1. Get the request parameters
            String Id_Producto = request.getParameter("Id_Producto"); // dummy parameter

        // 2. Do something with the data. usually you:
            //    a. Validate the data
            //    b. Delegate business logic
            //    c. Execute presentation logic
            ProductDao dao = new ProductDaoJdbc();
            List<Product> products = dao.findAll();
            // store the products in the request, so them can be painted in the view
            request.setAttribute("productList", products);
            // 3. Finally, returns the command result
            result.setResult("listProducts");
        } catch (Exception e) {
            Logger.getLogger(ListProducts.class.getName()).log(Level.WARNING, null, e);
            result.setErrorCode("ERR");
            result.setErrorMessage(e.getMessage());
            result.setResult("error");
        }
        request.setAttribute("commandResult", result);
        return result;
    }
    
    @Override
    public CommandResult findById(HttpServletRequest request, HttpServletResponse response) {
        
        CommandResult result = new CommandResult();
        
        try {
// 1. Get the request parameters
            String Id_Producto = request.getParameter("Id_Producto"); // dummy parameter

        // 2. Do something with the data. usually you:
            //    a. Validate the data
            //    b. Delegate business logic
            //    c. Execute presentation logic
            ProductDao dao = new ProductDaoJdbc();
            Product products = dao.findById(Id_Product);
            // store the products in the request, so them can be painted in the view
            request.setAttribute("productList", products);
            // 3. Finally, returns the command result
            result.setResult("ProductList");
        } catch (Exception e) {
            Logger.getLogger(ListProducts.class.getName()).log(Level.WARNING, null, e);
            result.setErrorCode("ERR");
            result.setErrorMessage(e.getMessage());
            result.setResult("error");
        }
        request.setAttribute("commandResult", result);
        return result;
        
         @Override
    public CommandResult findAll(HttpServletRequest request, HttpServletResponse response) {
        
        CommandResult result = new CommandResult();
        
        try {
// 1. Get the request parameters
            String Id_Producto = request.getParameter("Id_Producto"); // dummy parameter
            String nom_producto = request.getParameter("nom_producto");
        // 2. Do something with the data. usually you:
            //    a. Validate the data
            //    b. Delegate business logic
            //    c. Execute presentation logic
            ProductDao dao = new ProductDaoJdbc();
            //List<Product> products = dao.findAll();
            // store the products in the request, so them can be painted in the view 
            request.setAttribute("productList", product);
            // 3. Finally, returns the command result
            result.setResult("listProducts");
        } catch (Exception e) {
            Logger.getLogger(ListProducts.class.getName()).log(Level.WARNING, null, e);
            result.setErrorCode("ERR");
            result.setErrorMessage(e.getMessage());
            result.setResult("error");
        }
        request.setAttribute("commandResult", result);
        return result;
        
         try {
// 1. Get the request parameters
            String Id_Producto = request.getParameter("Id_Producto"); // dummy parameter
            String Nombre = request.getParameter("Nombre");
        // 2. Do something with the data. usually you:
            //    a. Validate the data
            //    b. Delegate business logic
            //    c. Execute presentation logic
            ProductDao dao = new ProductDaoJdbc();
            
            Product datos = new product();
            datos.setIdproducto(Id_Producto);
            datos.setnomproducto(nom_producto);
            datos.setvalor(valor_producto);
            //List<Product> products = dao.findAll();
            // store the products in the request, so them can be painted in the view 
            request.setAttribute("productList", product);
            // 3. Finally, returns the command result
            result.setResult("listProducts");
        } catch (Exception e) {
            Logger.getLogger(ListProducts.class.getName()).log(Level.WARNING, null, e);
            result.setErrorCode("ERR");
            result.setErrorMessage(e.getMessage());
            result.setResult("error");
        }
        request.setAttribute("commandResult", result);
        return result;
    }
    
        @Override
    public CommandResult update(HttpServletRequest request, HttpServletResponse response) {
        
        CommandResult result = new CommandResult();
        
        try {
// 1. Get the request parameters
          
            String Id_Producto = request.getParameter("Id_Producto");
            String nom_producto = request.getParameter("nom_producto"); // dummy parameter
            String valor = request.getParameter("valor_producto");

        // 2. Do something with the data. usually you:
            //    a. Validate the data
            //    b. Delegate business logic
            //    c. Execute presentation logic
            ProductoDao dao = new ProductoDaoJdbc();
            
            Producto datos = new Producto();
            datos.setIdproducto(Id_Producto);
            datos.setNombreproducto(nom_producto);
            datos.setValor(valor);
           
            
            Producto producto = dao.update(datos); 
            // store the products in the request, so them can be painted in the view
            request.setAttribute("productList", producto);
            // 3. Finally, returns the command result
            result.setResult("productList");
        } catch (Exception e) {
            Logger.getLogger(ListProducts.class.getName()).log(Level.WARNING, null, e);
            result.setErrorCode("ERR");
            result.setErrorMessage(e.getMessage());
            result.setResult("error");
        }
        request.setAttribute("commandResult", result);
        return result;
    }
    
    @Override
    public CommandResult delete(HttpServletRequest request, HttpServletResponse response) {
        
        CommandResult result = new CommandResult();
        
        try {
// 1. Get the request parameters
     
            String Id_Producto = request.getParameter("Id_Producto"); // dummy parameter

        // 2. Do something with the data. usually you:
            //    a. Validate the data
            //    b. Delegate business logic
            //    c. Execute presentation logic
            ProductoDao dao = new ProductoDaoJdbc();
            Producto datos = new Producto();
            datos.setIdproducto(id_producto);
            dao.delete(datos);
            // store the products in the request, so them can be painted in the view
            //request.setAttribute("productList", producto);
            // 3. Finally, returns the command result
            result.setResult("productList");
        } catch (Exception e) {
            Logger.getLogger(ListProducts.class.getName()).log(Level.WARNING, null, e);
            result.setErrorCode("ERR");
            result.setErrorMessage(e.getMessage());
            result.setResult("error");
        }
        request.setAttribute("commandResult", result);
        return result;
    }

