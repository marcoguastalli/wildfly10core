package apps.fsprint.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apps.fsprint.service.FileSystemPrintService;

@WebServlet("/fsprint")
public class FileSystemPrintServlet extends HttpServlet {

    @Inject
    FileSystemPrintService fileSystemPrintService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(this.fileSystemPrintService.print("SYSTEM"));
        writer.close();
    }
}