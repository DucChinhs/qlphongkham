package general;

public class benhnhan {
    private String id;
    private String name;
    private int age;
    private String startDay;
    private String mota;

    public benhnhan() {
    }

    public benhnhan(String id, String name, int age, String startDay, String mota) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.startDay = startDay;
        this.mota = mota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
