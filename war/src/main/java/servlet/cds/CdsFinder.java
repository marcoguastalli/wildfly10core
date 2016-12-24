package servlet.cds;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Cd;
import service.CdsService;

@Path("/CdsFinder")
public class CdsFinder {
    @EJB
    private CdsService cdsService;

    @GET
    @Produces("application/json")
    public CdsFinderResult get() {
        CdsFinderResult result = new CdsFinderResult();
        return result;
    }

    @GET
    @Produces("application/json")
    @Path("/findCdById")
    public Cd findCdById(@MatrixParam("id") String id) {
        try {
            return this.cdsService.findCdById(Integer.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
