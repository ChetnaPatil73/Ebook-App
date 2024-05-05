package com.user.servlet;

import java.io.IOException;

import com.DAO.userDAOimpl;
import com.DB.DBConnect;
import com.entity.user;
import com.sun.tools.sjavac.Log;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//Log.error(req.getRemoteAddr());
		try {
			userDAOimpl dao = new userDAOimpl(DBConnect.getConn());

			HttpSession session = req.getSession();
			
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			if ("admin@gmail.com".equals(email) && "admin".equals(password)) {
				user us=new user();
				us.setName("Admin");
				session.setAttribute("userobj", us);
				
				resp.sendRedirect("admin/home.jsp");
			} else {

				user us = dao.login(email, password);
				
				if (us != null) {
					session.setAttribute("userobj",us);
					resp.sendRedirect("index.jsp");
				}else {
					session.setAttribute("failedMsg","Email & password invalid");
					resp.sendRedirect("login.jsp");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
