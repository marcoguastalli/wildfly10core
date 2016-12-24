package servlet.cds;

import javax.ejb.EJB;

import model.Cd;
import service.CdsService;

public class CdsHandler {

    private CdsRequest cdsRequest;
    @EJB
    private CdsService cdsService;

    public CdsHandler(CdsRequest cdsRequest, CdsService cdsService) {
        this.cdsRequest = cdsRequest;
        this.cdsService = cdsService;
    }

    public Cd createCd() {
        Cd result = new Cd();
        result.setArtist(this.cdsRequest.getArtist());
        result.setTitle(this.cdsRequest.getTitle());
        result.setYear(this.cdsRequest.getYear());

        this.cdsService.persistCd(result);
        return result;
    }

}


