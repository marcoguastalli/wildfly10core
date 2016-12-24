package servlet.cds;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import base.http.BaseServlet;
import base.http.BaseSession;
import base.http.PopulateException;
import model.Cd;
import service.CdsService;

public class CdsServlet extends BaseServlet {
    private static final Logger logger = Logger.getLogger(CdsServlet.class);

    private static final String INDEX_JSP = "/index.jsp";
    private static final String STEP = "step";
    private static final String STEP_PRINT_ALL_CD_IN_HTML_TABLE = "printAllCdInHtmlTable";
    private static final String STEP_CD_STORED = "cdStored";

    @EJB
    private CdsService cdsService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String step = request.getParameter(STEP);
        if (step != null && STEP_PRINT_ALL_CD_IN_HTML_TABLE.equals(step)) {
            printAllCdInHtmlTable(request, response);
        } else {
            Collection<Cd> result = this.cdsService.findAllCds();
            response.setContentType("application/javascript");
            response.getWriter().print(createResultFromCollection(result));
        }
    }

    private String createResultFromCollection(Collection<Cd> collection) {
        JSONArray result = new JSONArray();
        try {
            for (Cd cd : collection) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", cd.getId());
                jsonObject.put("title", cd.getTitle());
                jsonObject.put("artist", cd.getArtist());
                jsonObject.put("year", cd.getYear());
                result.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseSession baseSession = getBaseSession(request);
        try {
            if (!baseSession.getStep().equals(STEP_CD_STORED)) {
                CdsHandler cdsHandler = new CdsHandler(createCdRequest(request), this.cdsService);
                request.setAttribute("cd", cdsHandler.createCd());
                baseSession.setStep(STEP_CD_STORED);
            }
            forward(request, response, INDEX_JSP);
        } catch (PopulateException e) {
            logger.error("Invalid Input Parameters", e);
        }
    }

    private CdsRequest createCdRequest(HttpServletRequest request) throws PopulateException {
        CdsRequest result = new CdsRequest();
        populate(request, result);
        return result;
    }

    protected void printAllCdInHtmlTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Cd> allCds = this.cdsService.findAllCds();

        StrBuilder sb = new StrBuilder();
        sb.append("<table><tr>")
                .append(appendColumn("id"))
                .append(appendColumn("title"))
                .append(appendColumn("artist"))
                .append(appendColumn("year"))
                .append("<tr>");
        for (Iterator<Cd> iterator = allCds.iterator(); iterator.hasNext();) {
            Cd cd = iterator.next();
            sb.append("<tr>")
                    .append(appendColumn(String.valueOf(cd.getId())))
                    .append(appendColumn(cd.getTitle()))
                    .append(appendColumn(cd.getArtist()))
                    .append(appendColumn(String.valueOf(cd.getYear())))
                    .append("<tr>");
        }
        sb.append("</table>");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(sb.toString());
    }

    private StrBuilder appendColumn(String columnValue) {
        return new StrBuilder().append("<td>").append(columnValue).append("</td>");
    }

}
