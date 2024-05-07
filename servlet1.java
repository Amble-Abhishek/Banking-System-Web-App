/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {

PrintWriter out = null;
Connection con;
PreparedStatement pat;
ResultSet rs;
    
  /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/bar","root","root");
        ServletContext context= getServletContext();
        context.setAttribute("accno","");
        String accno = request.getParameter("accno");
            String password = request.getParameter("password");
            pat= con.prepareStatement("select *from login where accno = ? and password= ?");
            pat.setString(1,accno);
            pat.setString(2,password);
            rs= pat.executeQuery();
            
            boolean row = false;
            row= rs.next();
            if(row == true)
            {
                result = rs.getString(2);
                context.setAttribute("accno",result);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/secondservlet");
                if(dispatcher == null)
                {
                    
                }
                dispatcher.forward(request, response);
                con.close();
            }else{
                        
                        out= response.getWriter();
                        response.setContentType("text/html");
                        out.println("<html>");
                        out.println("<body bgcolor=pink>");
                        out.println("please check the account number and Balance");
                        out.println("</body>");
                        out.println("</html>");
                        out.close();
                  }
            
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(servlet1.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    }

 /*   @Override
    public String getServletInfo() {
        return "Short description";
    }*/
}
