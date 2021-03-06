package Controllers;

import Entities.Account;
import Entities.EventEntity;
import Entities.NewsEntity;
import Entities.Picture;
import Models.EventModel;
import Models.NewsModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import lib.Convertors;

/**
 *
 * @author Dave
 */
@WebServlet(name = "Events", urlPatterns = {"/Events/*", "/EditEvent", "/NewEvent", "/SignUpEvent", "/EventAttended","/EventNotAttended"})
@MultipartConfig(maxFileSize = 16177215)
public class Events extends HttpServlet {

    /**
     * Constructor
     */
    private HashMap eventItems;
    private EventModel eventModel;
    private java.util.LinkedList<EventEntity> eventList;
    private HashMap CommandsMap = new HashMap();

    public Events() {
        CommandsMap.put("Picture", 1);
        CommandsMap.put("Event", 2);
        CommandsMap.put("All", 3);
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
            displayEvents(response, request);
        }

        int command;
        try {
            command = (Integer) CommandsMap.get(args[2]);
        } catch (Exception et) {
            return;
        }
        switch (command) {
            case 1:
                displayPicture(response, request, args[3]);
                break;
            case 2:
                displayEvent(response, request, args[3]);
                break;
            case 3:
                getCalendarEvents(response, request);
            default:
            //Error message here.
        }
    }

    public void displayEvents(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        EventModel model = new EventModel();
        java.util.LinkedList<EventEntity> eventitem = model.getAllEvents();
        request.setAttribute("events", eventitem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
        dispatcher.forward(request, response);
    }

    public void displayEvent(HttpServletResponse response, HttpServletRequest request, String id) throws ServletException, IOException {
        EventModel model = new EventModel();
        int eventID = Integer.parseInt(id);
        HttpSession session = request.getSession();
        //if user is logged in get a bool for if they are attending or not. 
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            Boolean attending = model.isAttending(eventID, account.getId());
            request.setAttribute("attending", attending);
        } else {
            System.out.println("No account found");
        }

        java.util.LinkedList<EventEntity> eventitem = model.GetEventByID(eventID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/eventStory.jsp");
        request.setAttribute("event", eventitem);
        dispatcher.forward(request, response);
    }

    public void displayPicture(HttpServletResponse response, HttpServletRequest request, String id) throws ServletException, IOException {
        int eventID = Integer.parseInt(id);
        System.out.println(eventID);
        EventModel news = new EventModel();

        Picture p = news.getPic(eventID);
        OutputStream out = response.getOutputStream();

        response.setContentType(p.getType());
        response.setContentLength(p.getLength());

        InputStream is = new ByteArrayInputStream(p.getBytes());
        BufferedInputStream input = new BufferedInputStream(is);
        byte[] buffer = new byte[8192];
        for (int length = 0; (length = input.read(buffer)) > 0;) {
            out.write(buffer, 0, length);
        }
        out.close();
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
        System.out.println(parts[1]);
        switch (parts[1]) {
            case "NewEvent":
                CreateEvent(request, response);
                break;
            case "EditEvent":
                changeEvent(request, response);
                break;
            case "SignUpEvent":
                signUp(request, response);
                break;
            case "EventAttended":
                eventAttended(request, response);
                break;
            case"EventNotAttended" :
                eventNotAttended(request,response);
                break;
        }
    }

    private void eventAttended(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EventModel model = new EventModel();
        int userID = Integer.parseInt(request.getParameter("id"));
        int eventID = Integer.parseInt(request.getParameter("eventid"));

        //System.out.println(userID+"the user id"+ eventID +"the event id");
        if (model.awardPoints(userID, eventID) == true) {
            //The content was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("1");
        } else {
            //Nothing was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("0");
        }
    }
    
    private void eventNotAttended(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EventModel model = new EventModel();
        int userID = Integer.parseInt(request.getParameter("id"));
        int eventID = Integer.parseInt(request.getParameter("eventid"));

        //System.out.println(userID+"the user id"+ eventID +"the event id");
        if (model.revertPoints(userID, eventID) == true) {
            //The content was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("1");
        } else {
            //Nothing was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("0");
        }
    }
    

    private void signUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EventModel model = new EventModel();
        int userID = Integer.parseInt(request.getParameter("id"));
        int eventID = Integer.parseInt(request.getParameter("eventid"));

        System.out.println(userID + "the user id" + eventID + "the event id");

        if (model.signUp(userID, eventID) == true) {
            //The content was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("1");
        } else {
            //Nothing was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("0");
        }
    }

    private void changeEvent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int eventID = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("eventTitle");
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");
        String description = request.getParameter("eventDescription");
        String location = request.getParameter("eventLocation");
        int theme = Integer.parseInt(request.getParameter("eventTheme"));
        int points = Integer.parseInt(request.getParameter("points"));
        Part filePart = request.getPart("image");

        EventModel model = new EventModel();

        if (model.updateEvent(filePart, title, description, location, startDate, endDate, points, theme, eventID) == true) {
            request.setAttribute("newsUpdated", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("News Story Updated.");
        } else {
            request.setAttribute("newsUpdated", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("News Story Failed To Update");
        }

    }

//       public java.sql.Timestamp convertJavaDateToSqlDate(java.util.Date date) {
//    return new java.sql.Timestamp(date.);
//}
    private void CreateEvent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("eventTitle");
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");
        String description = request.getParameter("eventDescription");
        String location = request.getParameter("eventLocation");
        int theme = Integer.parseInt(request.getParameter("eventTheme"));
        int points = Integer.parseInt(request.getParameter("points"));
        Part filePart = request.getPart("image");

        EventModel event = new EventModel();

        try {

            if (event.newEvent(filePart, title, description, location, startDate, endDate, points, theme) == false) {
                  request.setAttribute("storyNotAdded", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Adding Event failed");
            } else {
                  request.setAttribute("storyAdded", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Event Added.");
            }

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("expection thrown");
            HttpSession session = request.getSession();
            session.setAttribute("error", "No event title entered.");
            System.out.println("false, exception");
                 request.setAttribute("storyNotAdded", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            System.out.println("Adding Event failed");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String args[] = Convertors.SplitRequestPath(request);
        System.out.println(args[2]);
        switch (args[2]) {
            case "DeleteEvent":
                deleteEvent(request, response, args[3]);
                break;
            case "NotGoing":
                notGoing(request, response, args[3], args[4]);
                break;

        }
    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response, String id2) throws IOException, ServletException {
        int id = Integer.parseInt(id2);
        EventModel model = new EventModel();
        if (model.deleteEvent(id) == true) {
            //The content was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("1");
        } else {
            //Nothing was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("0");
        }

    }

    private void notGoing(HttpServletRequest request, HttpServletResponse response, String userid, String eventid) throws IOException, ServletException {
        int userID = Integer.parseInt(userid);
        int eventID = Integer.parseInt(eventid);

        EventModel model = new EventModel();
        if (model.leaveEvent(userID, eventID) == true) {
            //The content was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("1");
        } else {
            //Nothing was deleted
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("0");
        }

    }

    private void getCalendarEvents(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        EventModel eventM = new EventModel();
        LinkedList<EventEntity> events = eventM.getAllEvents();
        if (events != null & events.size() > 0) {
            JsonArray jsonArray = new JsonArray();
            for (EventEntity event : events) {
                JsonObject innerObject = new JsonObject();
                innerObject.addProperty("id", event.getID());
                innerObject.addProperty("title", event.getName());
                innerObject.addProperty("start", event.getStartDateAsUnix());
                innerObject.addProperty("end", event.getEndDateAsUnix());
                jsonArray.add(innerObject);
            }
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonArray);
                out.flush();
                out.close();
            }
        }
    }
}
