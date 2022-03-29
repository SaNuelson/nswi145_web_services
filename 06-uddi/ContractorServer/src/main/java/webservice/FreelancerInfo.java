package webservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FreelancerInfo implements Serializable {

	private static final long serialVersionUID = 7246839260494307136L;

	private String id;
	public String getId() { return id; }
	public void setId(String val) { id = val; }

    String name;
    public String getName() { return name; }
    public void setName(String val) { name = val; }

    List<String> jobTypes;
    public List<String> getJobTypes() { return jobTypes; }
    public void setJobTypes(List<String> val) { jobTypes = val; }
    
    public FreelancerInfo() {
        this.id = "null";
        this.name = "null";
        this.jobTypes = new ArrayList<String>();
    }

    public FreelancerInfo(String id, String name, List<String> jobTypes) {
        this.id = id;
        this.name = name;
        this.jobTypes = jobTypes;
    }

    public String toLongString() {
        return "Freelancer Info" +
                "\nID:       " + id +
                "\nName:     " + name +
                "\nJobTypes: " + jobTypes.toString();
    }

    public static FreelancerInfo Empty() {
        return new FreelancerInfo("null", "null", new ArrayList<String>());
    }

}
