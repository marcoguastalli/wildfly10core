package servlet.book;

import model.Book;
import service.BookService;
import service.BookServiceBean;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class DisplayAllBooks extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DisplayAllBooks.class);

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Collection<Book> books = getBookService().getAllBooks();

            JSONArray jsonArray = new JSONArray();

            for (Book book : books) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("author", book.getAuthor());
                jsonObject.put("title", book.getTitle());
                jsonArray.put(jsonObject);
            }

            response.setContentType("text/javascript; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("displayAllBooks(" + jsonArray.toString() + ");");

        } catch (JSONException e) {
            logger.error("Error creating book", e);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private BookService getBookService() {
        return new BookServiceBean();
    }
}
