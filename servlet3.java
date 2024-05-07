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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servelet3")
public class servlet3 extends HttpServlet {

   

    
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con;
        PreparedStatement pat;
        ResultSet rs;
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/bar","root","root");
            ServletContext context = getServletContext();
            Object obj = context.getAttribute("accno");
            String accno = obj.toString();
            
            
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String date = df.format(now);
            
            String amount = request.getParameter("amount");
            
            pat = con.prepareStatement("insert into account_holder(accnum,Date,mdeposit) values(?,?,?)");
            pat.setString(1,accno);
            pat.setString(2,date);
            pat.setString(3,amount);
            
            int rows= pat.executeUpdate();
            
            if(rows==1)
            {
                out.println("Your Transaction have been done");
               
            }else
            {
                out.println("Your Transaction Failed");
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(servlet3.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        ServletContext context= getServletContext();
        context.setAttribute("accno","");
        
    }

  
  /*  @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>*/

}
