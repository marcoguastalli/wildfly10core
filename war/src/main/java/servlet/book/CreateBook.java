package servlet.book;

import exception.InputException;
import model.Book;
import model.BookCreateRequest;
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

public class CreateBook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CreateBook.class);

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BookCreateRequest bookCreateRequest = new BookCreateRequest(request);
            Book book = getBookService().createBasicBook(bookCreateRequest);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("author", book.getAuthor());
            jsonObject.put("title", book.getTitle());

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(jsonObject.toString());

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
