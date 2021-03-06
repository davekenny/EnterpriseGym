package Controllers;

import Entities.Account;
import Entities.EventEntity;
import Entities.EventUserEntity;
import Entities.NewsEntity;
import Entities.UserEntity;
import Models.AdminModel;
import Models.EventModel;
import Models.NewsModel;
import Models.UserModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lib.Convertors;

/**
 *
 * @author Andy
 */
@WebServlet(name = "Admin", urlPatterns = {"/Admin/*", "/AddNews", "/UserPrivileges", "/DeleteUser", "/AddEvent"})
@MultipartConfig(maxFileSize = 16177215) //Set the pictures size up to 16MB  

public class Admin extends HttpServlet {

    private HashMap CommandsMap = new HashMap();

    /**
     * Constructor
     */
    public Admin() {
        super();
        CommandsMap.put("News", 1);
        CommandsMap.put("Event", 2);
        CommandsMap.put("Account", 3);
        CommandsMap.put("Leaderboard",4);
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

        if (args.length == 2) {
            // RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
            //   dispatcher.forward(request, response);
            displayPannel(response, request);
        }

        int command;
        try {
            command = (Integer) CommandsMap.get(args[2]);
        } catch (Exception et) {
            return;
        }
        switch (command) {
            //News
            case 1:
                if (args.length == 3) {
                    displayNewsPanel(response, request);
                } else {
                    displayEditNews(response, request, args[3]);
                }
                break;
            //Events    
            case 2:
                if (args.length == 3) {
                    displayEventPanel(response, request);
                } else {
                    if (args.length == 4) {
                        displayEditEvent(response, request, args[3]);
                    } else {
                        manageEvent(response, request, args[4]);
                    }
                }
                break;
            //Accounts
            case 3:
                if (args.length == 3) {
                    displayAccountPanel(response, request);
                } else {
                    //displayEditNews(response, request, args[3]);
                }
                break;
            case 4:
                displayLeaders(response,request);
                break;
            default:
                break;
            //Error message here.
        }
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
        String[] parts = Convertors.SplitRequestPath(request);
        switch (parts[1]) {
            case "AddNews":
                addNews(request, response);
                break;
            case "ResetPoints":
                resetPoints(request, response);
                break;
            case "DeleteUser":
                deleteUser(request, response);
                break;
            case "UserPrivileges":
                userPrivileges(request, response);
                break;
            case "AddEvent":
                addEvent(request, response);
                break;
            case "editQuiz":
                editQuiz(request, response);
                break;
        }
    }

    private void addNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = request.getParameter("editor1");
        String title = request.getParameter("title");
        AdminModel admin = new AdminModel();

        InputStream inputStream = null;
        Part filePart = request.getPart("image");

        if (admin.addNewsStory(filePart, content, title) == true) {
            request.setAttribute("storyAdded", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("News Story Added.");
        } else {
            request.setAttribute("storyNotAdded", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("News Story failed");
        }
    }

    private void resetPoints(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminModel admin = new AdminModel();

        if (admin.resetPoints() == true) {
            request.setAttribute("pointsReset", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Points Successfully Reset.");
        } else {
            request.setAttribute("pointsNotReset", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Error Resetting Points.");
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("usernameField");
        AdminModel admin = new AdminModel();

        if (admin.deleteUser(username) == true) {
            request.setAttribute("accountDeleted", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Account Successfully Deleted.");
        } else {
            request.setAttribute("accountNotDeleted", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Error Deleting Account.");
        }
    }

    private void userPrivileges(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("adminUsernameField");
        AdminModel admin = new AdminModel();

        if (admin.userPrivileges(username) == true) {
            request.setAttribute("accountAdmin", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Account Successfully Granted Admin Privileges.");
        } else {
            request.setAttribute("accountNotAdmin", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Error Granting Permissions.");
        }
    }

    private void addEvent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void editQuiz(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void displayPannel(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(request, response);
    }
    
    private void displayLeaders(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        UserModel model = new UserModel();
        java.util.LinkedList<UserEntity> useritems = model.getLeaders();
         RequestDispatcher dispatcher = request.getRequestDispatcher("/leaderBoard.jsp");
         request.setAttribute("users",useritems);
        dispatcher.forward(request, response);
    }

    private void displayNewsPanel(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        NewsModel model = new NewsModel();
        java.util.LinkedList<NewsEntity> newsitems = model.getAllNews();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newsadmin.jsp");
        request.setAttribute("news", newsitems);
        //Need to pass the profile attributes accross here.     
        dispatcher.forward(request, response);
    }

    private void displayEditNews(HttpServletResponse response, HttpServletRequest request, String id) throws ServletException, IOException {
        NewsModel model = new NewsModel();
        int NewsID = Integer.parseInt(id);
        //Need to pass the profile attributes accross here.
        java.util.LinkedList<NewsEntity> newsitems = model.getNewsArticle(NewsID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/newsEdit.jsp");
        request.setAttribute("news", newsitems);
        dispatcher.forward(request, response);
    }

    private void displayEventPanel(HttpServletResponse response, HttpServletRequest request) {
        try {
            EventModel model = new EventModel();
            java.util.LinkedList<EventEntity> eventItems = model.getAllEvents();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/eventAdmin.jsp");
            request.setAttribute("events", eventItems);
            //Need to pass the profile attributes accross here.     
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {

        }
    }

    private void displayEditEvent(HttpServletResponse response, HttpServletRequest request, String id) throws ServletException, IOException {
        //To change body of generated methods, choose Tools | Templates.
        EventModel model = new EventModel();
        int eventID = Integer.parseInt(id);
        //Need to pass the profile attributes accross here.
        java.util.LinkedList<EventEntity> event = model.GetEventByID(eventID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editEvent.jsp");
        request.setAttribute("event", event);
        dispatcher.forward(request, response);
    }

    private void manageEvent(HttpServletResponse response, HttpServletRequest request, String id) throws ServletException, IOException {
        EventModel model = new EventModel();
        int eventId = Integer.parseInt(id);
        java.util.LinkedList<EventUserEntity> eventuser = model.getEventUsers(eventId);
        request.setAttribute("eventuser", eventuser);
        request.setAttribute("eventid", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventAttend.jsp");
        dispatcher.forward(request, response);
    }

    private void displayAccountPanel(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        UserModel model = new UserModel();
        java.util.LinkedList<Account> accountList = model.getAllAccounts();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/accountAdmin.jsp");
        request.setAttribute("accountList", accountList);
        //Need to pass the profile attributes accross here.     
        dispatcher.forward(request, response);
    }

}
