package mainClasses;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dimos
 */
public class Maintenance {

    int maintenance_id, cost;
    String start_date, end_date, maint_type, status, lic_plate;

    public void setMaintenance_id(int maintenance_id) {
        this.maintenance_id = maintenance_id;
    }

    public void setLic_plate(String lic_plate) {
        this.lic_plate = lic_plate;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setMaint_type(String maint_type) {
        this.maint_type = maint_type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaintenance_id() {
        return maintenance_id;
    }

    public String getLic_plate() {
        return lic_plate;
    }

    public int getCost() {
        return cost;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getMaint_type() {
        return maint_type;
    }

    public String isStatus() {
        return status;
    }

    public Maintenance(String _lic_plate, int _cost, String _start_date, String _end_date, String _maint_type, String _status) {
        this.cost = _cost;
        this.lic_plate = _lic_plate;
        this.start_date = _start_date;
        this.end_date = _end_date;
        this.maint_type = _maint_type;
        this.status = _status;
    }

    public Maintenance(String _lic_plate, String _maint_type) {
        this.lic_plate = _lic_plate;
        this.maint_type = _maint_type;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date(); // Current date
        this.start_date = dateFormat.format(startDate);

        // Calculate end date based on issue type
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        if ("maint".equals(_maint_type)) {
            calendar.add(Calendar.DATE, 1);
            this.end_date = dateFormat.format(calendar.getTime());
            this.cost = 50;
        }

        if ("repair".equals(_maint_type)) {
            calendar.add(Calendar.DATE, 3);
            this.end_date = dateFormat.format(calendar.getTime());
            this.cost = 150;
        }

        this.status = "ongoing";
    }
}
