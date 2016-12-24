package servlet.book;

import exception.InputException;

import javax.servlet.http.HttpServletRequest;

public class DisplayBookRequest {
    private final String author;
    private final String title;

    public DisplayBookRequest(HttpServletRequest request) throws InputException {
        this.author = request.getParameter("author");
        this.title = request.getParameter("title");
        validate();
    }

    private void validate() throws InputException {
        if (getAuthor() == null || getAuthor().isEmpty()) {
            throw new InputException("author");
        }
        if (getTitle() == null || getTitle().isEmpty()) {
            throw new InputException("title");
        }
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

}
