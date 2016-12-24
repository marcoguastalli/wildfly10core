package base.http;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.log4j.Logger;

import utils.RequestUtils;
import utils.string.StringUtilities;

public class BaseServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(BaseServlet.class);

    private static final String POPULATING_EXCEPTION_TEXT = "Exception when populating from request.";

    /**
     * Populate an object from the parameters in the request.
     * <p/>
     * Also, invokes the {@link base.http.Populatable#validate()} method.
     *
     * @throws PopulateException
     */
    protected void populate(HttpServletRequest request, Populatable objectToPopulate) throws PopulateException {
        Map<String, String[]> parameters = (Map<String, String[]>) request.getParameterMap();
        BeanUtilsBean populatorBean = BeanUtilsBean.getInstance();
        for (Map.Entry<String, String[]> param : parameters.entrySet()) {
            try {
                populatorBean.setProperty(objectToPopulate, param.getKey(), param.getValue());
            } catch (IllegalAccessException iaex) {
                throw new PopulateException(POPULATING_EXCEPTION_TEXT, iaex);
            } catch (InvocationTargetException itex) {
                throw new PopulateException(POPULATING_EXCEPTION_TEXT, itex);
            } catch (ConversionException cex) {
                logger.warn("Conversion exception for parameter: " + param.getKey() + " for value: " + StringUtilities.arrayAsString(param.getValue()) + " from referer " + RequestUtils.getReferer(request), cex);
                // TODO THIS CATCH IS FOR EMPTY PARAMETERS CONVERSION, DO NOT REMOVE IT
                //throw new PopulateException(cex);
                // Accumulate errors: error for field  param.getKey()
            }
        }
        objectToPopulate.validate();
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String pathToForwardTo) throws IOException, ServletException {
        getServletContext().getRequestDispatcher(pathToForwardTo).forward(request, response);
    }

    protected void redirect(HttpServletResponse response, String pathToForwardTo) throws IOException {
        String encodeUrl = response.encodeRedirectURL(pathToForwardTo);
        response.sendRedirect(encodeUrl);
    }

    protected BaseSession getBaseSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return session.getAttribute("baseSession") != null ? (BaseSession) session.getAttribute("baseSession") : new BaseSession(session);
    }
}
