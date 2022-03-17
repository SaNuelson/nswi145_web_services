package webservices;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "name",
        "jobTypes"
})
@XmlRootElement(name = "freelancerInfo")
public class FreelancerInfo implements Serializable {

    @XmlElement(name = "id", required = true)
    private String id;

    @XmlElement(name = "name", required = true)
    String name;

    @XmlElement(name = "jobType", required = true)
    List<String> jobTypes;

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
