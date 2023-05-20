package general;

public class phongdieutri {
    private String ID;
    private String bacsidieutri;
    private String ytaphutrach;
    private String tenbenhnhan;
    private String date;

    public phongdieutri() {

    }

    public phongdieutri(String ID, String bacsidieutri, String ytaphutrach, String tenbenhnhan, String date) {
        this.ID = ID;
        this.bacsidieutri = bacsidieutri;
        this.ytaphutrach = ytaphutrach;
        this.tenbenhnhan = tenbenhnhan;
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBacsidieutri() {
        return bacsidieutri;
    }

    public void setBacsidieutri(String bacsidieutri) {
        this.bacsidieutri = bacsidieutri;
    }

    public String getYtaphutrach() {
        return ytaphutrach;
    }

    public void setYtaphutrach(String ytaphutrach) {
        this.ytaphutrach = ytaphutrach;
    }

    public String getTenbenhnhan() {
        return tenbenhnhan;
    }

    public void setTenbenhnhan(String tenbenhnhan) {
        this.tenbenhnhan = tenbenhnhan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
