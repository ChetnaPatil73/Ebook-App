package com.user.servlet;

import java.io.IOException;

import com.DAO.userDAOimpl;
import com.DB.DBConnect;
import com.entity.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 try {
		 String name=req.getParameter("fname");
		 String email=req.getParameter("email");
		 String phno=req.getParameter("phno");
		 String password=req.getParameter("password");
		 String check=req.getParameter("check");
		 //System.out.println(name+""+email+""+phno+""+password+""+check);
		 user us=new user();
		 us.setName(name);
		 us.setEmail(email);
		 us.setPhno(phno);
		 us.setPassword(password);

		 HttpSession session=req.getSession();
		 if(check!=null)
		 {
		 userDAOimpl dao=new userDAOimpl(DBConnect.getConn());
		 boolean f2=dao.checkUser(email);
		 if(f2)
		 {
			 boolean f=dao.userRegister(us);
			    if(f) {
				 session.setAttribute("succMsg", "Registration Successfuly..");
				 resp.sendRedirect("register.jsp");
			 }
			 else {
				 session.setAttribute("failedMsg", "Something wrong on server..");
				 resp.sendRedirect("register.jsp");
			 }
		 }else
		 {
			 session.setAttribute("failedMsg", "user Already Exit Try Another Email id");
			 resp.sendRedirect("register.jsp");
		 }
		 }else {
			 session.setAttribute("failedMsg", "Please check Agree & Terms Condition.");
			 resp.sendRedirect("register.jsp");
		 }
		 
	 }
	 catch (Exception e) {
		 e.printStackTrace();
	 }
	 
		
	}

}
