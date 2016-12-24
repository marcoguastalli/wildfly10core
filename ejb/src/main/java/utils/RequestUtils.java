package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class RequestUtils {

    private static final Logger logger = Logger.getLogger(RequestUtils.class);

    public static HttpServletRequest getRequestFromFacesContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = null;
        if (facesContext != null) {
            request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        }
        return request;
    }

    /**
     * @return Server IP address string
     */
    public static String getServerIP() {
        String server = null;
        try {
            server = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException uhex) {
            logger.warn("No IP address for localhost could be found!!!", uhex);
        }
        return server;
    }

    public static int getServerPort() {
        HttpServletRequest request = getRequestFromFacesContext();
        return request != null ? getServerPort(request) : 0;
    }

    /**
     * @param request
     * @return Server port number
     */
    public static int getServerPort(HttpServletRequest request) {
        return request.getLocalPort();
    }

    public static String getUserIP() {
        HttpServletRequest request = getRequestFromFacesContext();
        return request != null ? getUserIp(request) : null;
    }

    /**
     * Returns the "real" user IP string, trying to bypass any normal proxy between client and server.<br/>
     * Take into account that clients located behind anonymous proxies are not populating their real IP address,
     * and there is no trick to get it from the server.
     *
     * @param request
     * @return "Real" user IP string
     * @link http://www.eslomas.com/index.php/archives/2005/04/26/obtencion-ip-real-php/
     * @link http://www.cyruxnet.org/foro/viewtopic.php?t=634
     * @link http://webmasters.navegalis.com/articulos/mostrar/83/
     */
    public static String getUserIp(HttpServletRequest request) {
        //-----------------
        // 22/06/2007 Danielo. When the client is behind one or more transparent proxies, every proxy appends the
        // incoming IP to the header 'HTTP_X_FORWARDED_FOR' and put their own IP as 'REMOTE_ADDR'.
        // So, when 'HTTP_X_FORWARDED_FOR' header is received, the first public IP must be extracted from the IPs
        // listed in that header.
        //-----------------
//        String userIP = request.getRemoteAddr();
//        if (request.getHeader("HTTP_X_FORWARDED_FOR") != null) {   // Proxy detection
//            userIP = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
        //-----------------
        // Header 'HTTP_X_FORWARDED_FOR'
        String userIP = getHttpXForwardedForIP(request);
        if (userIP == null) {
            // Header 'HTTP_CLIENT_IP'
            userIP = request.getHeader("HTTP_CLIENT_IP");
            if ((userIP == null) || "".equals(userIP.trim())) {
                // Normal RemoteAddr
                userIP = request.getRemoteAddr();
            }
        }
        //-----------------
        // Ok
        return userIP;
    }

    /**
     * Gets the first public IP located in http header 'HTTP_X_FORWARDED_FOR', if this header is present.<br/>
     * Normal proxies fill this header as a list of IP addresses separated by ',' or ' ', appending the client's IP at
     * the end of the header every time.<br/>
     * Sample in PHP found in:<pre>
     *      http://www.eslomas.com/index.php/archives/2005/04/26/obtencion-ip-real-php/
     * </pre>
     *
     * @param request
     * @return First public IP string found in http header 'HTTP_X_FORWARDED_FOR', or <code>null</code> otherwise
     * @link http://www.eslomas.com/index.php/archives/2005/04/26/obtencion-ip-real-php/
     */
    private static String getHttpXForwardedForIP(HttpServletRequest request) {
        // Get http header 'HTTP_X_FORWARDED_FOR'
        String headerForwardedIP = request.getHeader("HTTP_X_FORWARDED_FOR");

        //-----------------
        // 22/06/2007 Danielo. Uncomment this if you want to test the split and test!
        //                     JUST FOR TEST!!!
        //-----------------
//        headerForwardedIP = "192.168.0.1,192.168.10.10 213.171.218.198,192.168.10.10";
        //-----------------

        // Check header value
        if ((headerForwardedIP == null) || "".equals(headerForwardedIP.trim())) {
            // Header HTTP_X_FORWARDED_FOR not provided!
            return null;
        }

        // Split forwarded IP list
        String[] forwardedIPs = headerForwardedIP.split("[, ]");

        // Return the first non private IP
        InetAddress oIP;
        for (int i = 0; i < forwardedIPs.length; i++) {
            try {
                oIP = InetAddress.getByName(forwardedIPs[i]);
                if (!oIP.isSiteLocalAddress()) {
                    return forwardedIPs[i];
                }
            } catch (UnknownHostException uhex) {
                logger.warn("IP [" + forwardedIPs[i] + "] has not a valid InetAddress format!!!", uhex);
            }
        }

        // No public IP found!!!
        logger.debug("No public IP found in forwarded list [" + headerForwardedIP + "]!!!");
        return null;
    }

    public static String getUserSessionId(HttpServletRequest request) {
        String session = getUserSessionIdAndServerName(request);
        int index = session.indexOf('.');
        if (index > 0) {
            return session.substring(0, index);
        }
        return session;
    }

    public static String getUserSessionIdAndServerName(HttpServletRequest request) {
        return request.getSession(false).getId();
    }

    public static String getServerHost(HttpServletRequest request) {
        return request.getServerName();
    }

    public static String getReferer(final HttpServletRequest request) {
        return request.getHeader("referer");
    }

}
