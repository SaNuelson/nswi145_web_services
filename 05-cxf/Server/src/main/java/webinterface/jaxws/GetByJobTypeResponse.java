
package webinterface.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.5.1
 * Sun Mar 27 21:09:00 CEST 2022
 * Generated source version: 3.5.1
 */

@XmlRootElement(name = "GetByJobTypeResponse", namespace = "http://webinterface/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetByJobTypeResponse", namespace = "http://webinterface/")

public class GetByJobTypeResponse {

    @XmlElement(name = "return")
    private String[] _return;

    public String[] getReturn() {
        return this._return;
    }

    public void setReturn(String[] new_return)  {
        this._return = new_return;
    }

}

