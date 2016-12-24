package base.http;

import javax.servlet.http.HttpSession;

public class BaseSession {

    private HttpSession httpSession = null;

    private String username = "";
    private String password = "";
    private String language = "en";
    private String step = "";

    private boolean isLogged;

    public BaseSession() {
    }

    public BaseSession(HttpSession httpSession) {
        if (httpSession == null) {
            throw new IllegalArgumentException("BaseSession cant be initialized with null HttpSession");
        }
        this.httpSession = httpSession;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
