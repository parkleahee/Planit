package com.planit.totalfrontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.ActionTo;
import com.planit.schedulecontroller.ScheduleFrontController;
import com.planit.userfrontcontroller.UserFrontController;




public class TotalFrontController extends HttpServlet{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
// --> total ->  *.* -> sevlet
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String uri = req.getRequestURI();
      String path = req.getContextPath();
      String command = uri.substring(path.length());
      //String subject = command.split("\\.")[1];
      String subject = command.split("/")[1];
      command = command.split("\\.")[0];
      ActionTo transfer = null;
      System.out.println(command);
      switch (subject) {
      case "user":
            transfer = new UserFrontController().flow(req, resp, command);
         break;
      case "schedule":
            transfer = new ScheduleFrontController().flow(req, resp, command);
         break;
      default:
         break;
      }
      
      if(transfer != null) {
         if(transfer.isRedirect()) {
            //Redirect 방식
            resp.sendRedirect(transfer.getPath());
         }
         else {
            //Forward 방식
            System.out.println(transfer.getPath());
            req.getRequestDispatcher(transfer.getPath()).forward(req, resp);
         }
      }
   }
   
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      doGet(req, resp);
   }
   
}