package ws;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import model.Cd;
import service.CdsService;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CdsWebService {
    @EJB
    private CdsService cdsService;

    @WebMethod
    public Cd saveCd(@WebParam(name = "id") String id,
            @WebParam(name = "title") String title,
            @WebParam(name = "artist") String artist,
            @WebParam(name = "year") String year) {
        Cd result = new Cd();
        try {
            Cd cd = new Cd();
            cd.setId(Integer.valueOf(id));
            cd.setTitle(title);
            cd.setArtist(artist);
            cd.setYear(Integer.valueOf(year));

            Cd instance = this.cdsService.findCdById(Integer.valueOf(id));
            if (instance == null) {
                this.cdsService.persistCd(cd);
            } else {
                this.cdsService.mergeCd(cd);
            }

        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    @WebMethod
    public Cd getCd(String id) {
        Cd result = null;
        try {
            result = this.cdsService.findCdById(Integer.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
