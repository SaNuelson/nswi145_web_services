package common;

public class CustomerInfo {

    public static final int ID_MIN = 0xA000_0000;

    private int id;
    private String name;
    private String pass;

    public CustomerInfo() {}

    public CustomerInfo(String name, String pass) {
        this.id = getNextId();
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private static int idCtr = ID_MIN;
    private static int getNextId() { return idCtr++; }

}
