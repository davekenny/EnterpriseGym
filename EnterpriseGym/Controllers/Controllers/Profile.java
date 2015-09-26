package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entities.Account;
import Entities.UserEntity;
import Models.UserModel;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.http.HttpSession;
import lib.Convertors;

/**
 *
 * @author kristiyangeorgiev
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile", "/Profile/*", "/EditProfile/*"})
@MultipartConfig

public class Profile extends HttpServlet {
    
    private HashMap CommandsMap = new HashMap();

    /**
     * Constructor
     */
    public Profile() {
        super();
        CommandsMap.put("Points", 1);
        CommandsMap.put("ChangePassword", 2);
    }

    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    }

    /**
     * Method to get the account details for display
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String args[] = Convertors.SplitRequestPath(request);
        
        if (args.length == 2 && args[1].equals("Profile")) {
            displayprofile(response, request);
        } else if (args[1].equals("EditProfile")) {
            editdetails(response, request);
        }
        
        int command;
        try {
            command = (Integer) CommandsMap.get(args[2]);
        } catch (Exception et) {
            return;
        }
        switch (command) {
            case 1:
                displaypoints(response, request);
                break;
            default:
            //Error message here.
        }
    }
    
    private void displayprofile(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        UserModel model = new UserModel();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        //Need to pass the profile attributes accross here.
        java.util.LinkedList<UserEntity> userdetails = model.getDetails(account.getUsername());
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        request.setAttribute("userdetails", userdetails);
        dispatcher.forward(request, response);
    }
    
    private void displaypoints(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        //Populate the points entity.
        UserModel model = new UserModel();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        //Need to pass the profile attributes accross here.
        LinkedList<UserEntity> points = new LinkedList();
        UserEntity user = model.getPoints(account.getUsername());
        points.add(user);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mypoints.jsp");
        request.setAttribute("points", points);
        dispatcher.forward(request, response);
    }
    
    private void editdetails(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        UserModel model = new UserModel();
        String username = getUsername(request);
        java.util.LinkedList<UserEntity> userdetails = model.getDetails(username);
        request.setAttribute("userdetails", userdetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editdetails.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Get the username of the logged in person unless request is for another account and access levels are ok
     * @param request
     * @return 
     */
    private String getUsername(HttpServletRequest request) {
        String username = null;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String args[] = Convertors.SplitRequestPath(request);
        if (args.length == 3 && account.hasAccessLevel(10)) {
            username = args[2];
        } else {
            
            username = account.getUsername();
        }
        return username;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String args[] = Convertors.SplitRequestPath(request);
        
        if (args.length == 2) {
            // RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
            //   dispatcher.forward(request, response);
            //displayprofile(response,request);
        }
        
        int command;
        try {
            command = (Integer) CommandsMap.get(args[2]);
        } catch (Exception et) {
            return;
        }
        switch (command) {
            case 3:
                changePassword(response, request);
                break;
            default:
            //Error message here.
        }
    }
    
    private boolean changePassword(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword1 = request.getParameter("newPassword1");
        String newPassword2 = request.getParameter("newPassword2");

        //TODO: Check if user password is correct
        if (newPassword1.equals(newPassword2)) {
            UserModel user = new UserModel();
            user.setPassword(username, newPassword1);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/changePasswordFailed.jsp");
            dispatcher.forward(request, response);
        }
        return true;
    }
}
