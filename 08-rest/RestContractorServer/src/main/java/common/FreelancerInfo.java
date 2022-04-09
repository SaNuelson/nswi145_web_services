package common;

public class FreelancerInfo {

    public static final int ID_MIN = 0x0B00_0000;

    private int id;
    private String name;
    private String[] jobTypes;
    private int hourlyRate;

    public FreelancerInfo() {
    	this.id = getNextId();
    }
    
    public FreelancerInfo(String name, String[] jobTypes, int hourlyRate) {
        this.id = getNextId();
        this.name = name;
        this.jobTypes = jobTypes;
        this.hourlyRate = hourlyRate;
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

    public String[] getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(String[] jobTypes) {
        this.jobTypes = jobTypes;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    private static int idCtr = ID_MIN;
    private static int getNextId() { return idCtr++; }

}
