package com.admin.servlet;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editbooks")
public class EditBooksServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String bookName = req.getParameter("bname");
            String author = req.getParameter("author");
            String price = req.getParameter("price");
            String status = req.getParameter("status");

            BookDtls b = new BookDtls();
            b.setBookId(id);
            b.setBookname(bookName);
            b.setAuthor(author);
            b.setPrice(price); // Assuming the price is stored as a String in the database
            b.setStatus(status);

            BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
            boolean f = dao.updateEditBooks(b);
            HttpSession session = req.getSession();
            if (f) {
                session.setAttribute("succMsg", "Book Update Successfully..");
                resp.sendRedirect("admin/all_books.jsp");
            } else {
                session.setAttribute("failedMsg", "Something wrong on the server");
                resp.sendRedirect("admin/all_books.jsp");
            }
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
            
        } catch (Exception e) {
            e.printStackTrace();
           
        }
    }

}
