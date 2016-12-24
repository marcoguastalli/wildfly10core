package servlet.autocomplete;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutoComplete extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AutoComplete.class);

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKey = request.getParameter("searchKey");
        logger.info("AutoComplete searchKey: " + searchKey);

        response.setContentType("text/javascript; charset=UTF-8");
        response.getWriter().print(createResult(searchKey));
    }

    private String createResult(String searchKey) {
        JSONArray result = new JSONArray();
        for (int i = 0; i < 3; i++) {
            result.put(searchKey + i);
        }
        return result.toString();
    }
}
