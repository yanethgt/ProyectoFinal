package edu.uan.fis.jeesample.servlet;

import edu.uan.fis.jeesample.actions.CommandResult;
import edu.uan.fis.jeesample.actions.impl.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = {"*.do"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Front controller servlet parses the URL to get the requested 
        // action and send the control of this action to the appropriate 
        // command class

        String desiredAction = request.getServletPath();
        String destination = null;
        CommandResult result = null;
        // TODO: read the cases from a configuration file. Extra points!!!
         switch (desiredAction) {
             
             //Productos
            case "/listProducts.do":
                result = new ListProducts().findAll(request, response);
                destination = "/ServletListaProductos";
                //the front controller redirects the user to the appropiate view
                dispatcher = request.getServletContext().getRequestDispatcher(destination);
                dispatcher. forward(request, response);
                break;
                
            case "/findProduct.do":
                result = new ListProducts().findById(request, response);
                destination = "/ServletDatosProductos";
                //the front controller redirects the user to the appropiate view
                dispatcher = request.getServletContext().getRequestDispatcher(destination);
                dispatcher. forward(request, response);
                break;
                
            case "/insertProduct.do":
                //response.getWrite().print("insertar OK");
                result = new ListProducts().create(request, response);
                break;
                
            case "/updateProduct.do":
                result = new ListProducts().update(request, response);
                break;
                
            case "/deleteProduct.do":
                result = new ListProducts().delete(request, response);
                break;
             
            // Using a dispatcher class and the result of the command execution
        // the front controller redirects the user to the appropriate view
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
         
}

        // Depending on the command result choose the appropriate view
        // You can add new cases according tou your design
        // TODO: read the cases from a configuration file. Extra points!!!

  
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Front controller servlet";
    }// </editor-fold>

}
