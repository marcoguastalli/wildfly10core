package servlet.cds;

import model.Cd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CdsFinderResult {
    public String id = "2727";
    @XmlElement
    public Cd cd;
}
