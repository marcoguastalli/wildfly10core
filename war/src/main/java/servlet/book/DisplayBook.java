package servlet.book;

import exception.InputException;
import model.Book;
import service.BookService;
import service.BookServiceBean;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DisplayBook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DisplayBook.class);

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DisplayBookRequest displayBookRequest = new DisplayBookRequest(request);
            Book book = getBookService().getBook(displayBookRequest.getAuthor(), displayBookRequest.getTitle());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("author", book.getAuthor());
            jsonObject.put("title", book.getTitle());

            response.setContentType("text/javascript; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("displayBook(" + jsonObject.toString() + ");");

        } catch (InputException e) {
            logger.error("Error creating book", e);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (JSONException e) {
            logger.error("Error creating book", e);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private BookService getBookService() {
        return new BookServiceBean();
    }
}