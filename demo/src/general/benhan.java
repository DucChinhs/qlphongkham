package general;

public class benhan {
    private String id;
    private String date;
    private String idbenhnhan;
    private String bsphutrach;
    private String mota;

    public benhan(String id, String date, String idbenhnhan, String bsphutrach, String mota) {
        this.id = id;
        this.date = date;
        this.idbenhnhan = idbenhnhan;
        this.bsphutrach = bsphutrach;
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdbenhnhan() {
        return idbenhnhan;
    }

    public void setIdbenhnhan(String idbenhnhan) {
        this.idbenhnhan = idbenhnhan;
    }

    public String getBsphutrach() {
        return bsphutrach;
    }

    public void setBsphutrach(String bsphutrach) {
        this.bsphutrach = bsphutrach;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
