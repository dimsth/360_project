/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ajax.calls;

import database_tables.EditMaintenanceTable;
import database_tables.EditRentalTable;
import database_tables.EditVehicleTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dimos
 */
public class AdminStatistics extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminStatistics</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminStatistics at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        EditVehicleTable evt = new EditVehicleTable();
        EditRentalTable ert = new EditRentalTable();
        EditMaintenanceTable emt = new EditMaintenanceTable();
        String ret = "";
        try (PrintWriter out = response.getWriter()) {
            ret += "Yearly Rentals|";
            ret += evt.getVehicleRentalDuration();
            ret += "?";
            ret += "Revenue by Time Period and Category|";
            ret += ert.getVehicleYearlyRevenue();
            ret += "?";
            ret += "Total Maintenance and Repair Costs|";
            ret += emt.getVehicleYearlyMaintCost();
            ret += "?";
            ret += "Most Popular Vehicle|";
            ret += evt.getPopularVehicle();
            System.out.println(ret);

            response.setStatus(200);
            out.println(ret);
        } catch (SQLException ex) {
            Logger.getLogger(AdminStatistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        return "Short description";
    }// </editor-fold>

}
