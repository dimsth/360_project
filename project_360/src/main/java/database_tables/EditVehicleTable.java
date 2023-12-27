/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database_tables;

import com.google.gson.Gson;
import database_connect.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Vehicle;

/**
 *
 * @author dimos
 */
public class EditVehicleTable {

    public void deleteVehicle(String id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM vehicles WHERE vehicle_id = '" + id + "'";
        stmt.executeUpdate(delete);
    }

    public ArrayList<Vehicle> getAllVehicles() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Vehicle> tmp = new ArrayList<>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM vehicles");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Vehicle _vehicle = gson.fromJson(json, Vehicle.class);
                tmp.add(_vehicle);
            }
            return tmp;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
        }
        return null;
    }

    public void updateVehicleField(String username, String field, String value) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE vehicles SET " + field + "='" + value + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void createVehicleTable() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE vehicles "
                + "(vehicle_id INTEGER not NULL AUTO_INCREMENT, "
                + "    brand VARCHAR(30) not null,"
                + "    model VARCHAR(30) not null,"
                + "    color VARCHAR(30) not null,"
                + "    type VARCHAR(10) not null,"
                + "    lic_plate VARCHAR(10) not null unique,"
                + "    range_km INTEGER not null,"
                + "    rented_count INT,"
                + "    total_days INT,"
                + "    quantity INT not null,"
                + "    daily_rental_cost INT not null,"
                + "    daily_insurance_cost INT not null,"
                + "    is_damaged VARCHAR(7) not null,"
                + "    subtype_name VARCHAR(50),"
                + "    FOREIGN KEY (subtype_name) REFERENCES subtypes(subtype_name),"
                + " PRIMARY KEY (vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }

    public void addNewVehicle(Vehicle _vehicle) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " vehicles (brand, model, color, type, lic_plate, range_km, rented_count, total_days, quantity, daily_rental_cost, daily_insurance_cost, is_damaged, sub_type)"
                    + " VALUES ("
                    + "'" + _vehicle.getBrand() + "',"
                    + "'" + _vehicle.getModel() + "',"
                    + "'" + _vehicle.getColor() + "',"
                    + "'" + _vehicle.getType() + "',"
                    + "'" + _vehicle.getLic_plate() + "',"
                    + "'" + _vehicle.getRange_km() + "',"
                    + "'" + _vehicle.getRented_count() + "',"
                    + "'" + _vehicle.getTotal_days() + "',"
                    + "'" + _vehicle.getQuantity() + "',"
                    + "'" + _vehicle.getDaily_rental_cost() + "',"
                    + "'" + _vehicle.getDaily_insurance_cost() + "',"
                    + "'" + _vehicle.Is_damaged() + "'"
                    + "'" + _vehicle.getSub_type() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditVehicleTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}