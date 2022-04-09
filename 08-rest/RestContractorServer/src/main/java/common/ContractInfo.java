package common;

public class ContractInfo {

    public static final int ID_MIN = 0x0C00_0000;

    private int id;
    private String title;

    private String description;
    private String jobType;

    private int customerId;
    private int freelancerId;

    public ContractInfo() {
    	this.id = getNextId();
    }
    
    public ContractInfo(String title, String description, String jobType, int customerId, int freelancerId) {
        this.id = getNextId();
        this.title = title;
        this.description = description;
        this.jobType = jobType;
        this.customerId = customerId;
        this.freelancerId = freelancerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    private static int idCtr = ID_MIN;
    private static int getNextId() { return idCtr++; }
}
