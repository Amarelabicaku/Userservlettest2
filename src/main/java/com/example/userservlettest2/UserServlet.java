package com.example.userservlettest2;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoService.IuserDao;
import daoServiceImplementation.LoginDao;
import daoServiceImplementation.UserDAO;
import daoServiceImplementation.UserDhePosteServiceImpl;
import model.User;
import model.UserDhePoste;


@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IuserDao userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/login":
                    loginAndShowFeeds(request, response);
                    break;
                case "/showlogin":
                    showlogin(request, response);
                    break;
                case "/post-feed/":
                    loginAndShowFeeds(request, response);
                    break;
                case "/do-post":
                    doposts(request, response);
                    break;
                case "/delete-post":
                    deletepost(request, response);
                    break;
                case "/update-post":
                    updatepost(request, response);
                case "/edit-post":
                    showeditpost(request, response);
                    break;
                default:

                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showeditpost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,SQLException {
        int postime_id = Integer.parseInt(request.getParameter("postime_id"));
        UserDhePosteServiceImpl userDhePosteService = new UserDhePosteServiceImpl();
        UserDhePoste existingPost = userDhePosteService.selectpostby(postime_id);
        request.setAttribute("post", existingPost);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/updatefeed.jsp");

      dispatcher.forward(request,response);

    }

    private void updatepost(HttpServletRequest request, HttpServletResponse response) throws  SQLException, IOException {
        try {
            int postime_id = Integer.parseInt(request.getParameter("postime_id"));
            String description = request.getParameter("description");
            UserDhePoste userDhePoste = new UserDhePoste(postime_id,description);
            UserDhePosteServiceImpl userDhePosteService = new UserDhePosteServiceImpl();
            userDhePosteService.updatepost(userDhePoste);
            response.sendRedirect("list-post");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void deletepost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int postime_id = Integer.parseInt(request.getParameter("postime_id"));
        UserDhePosteServiceImpl userDhePosteService = new UserDhePosteServiceImpl();
        userDhePosteService.Deletepost(postime_id);
        response.sendRedirect("list-post");
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUser();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/User-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/User-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/User-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String surname = request.getParameter("surname");
        User newUser = new User(name, email, country, password, address, surname);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String surname = request.getParameter("surname");
        User book = new User(id, name, email, country, password, address, surname);
        userDAO.updateUser(book);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteuser(id);
        response.sendRedirect("list");

    }

    private void showlogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void loginAndShowFeeds(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginDao loginDao = new LoginDao();
        if (loginDao.checkEmail(email, password)) {
            request.setAttribute("loggedUser", email);
            UserDhePosteServiceImpl userDhePosteService = new UserDhePosteServiceImpl();
            List<UserDhePoste> userDhePoste = userDhePosteService.getAllPosts();
            request.setAttribute("userDhePoste", userDhePoste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/feed.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("User doesnt exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorservlet.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doposts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("message");
        String email = request.getParameter("email");

        UserDhePosteServiceImpl userDhePosteService = new UserDhePosteServiceImpl();
        Date date = new Date(new java.util.Date().getTime());
        UserDhePoste userDhePoste = new UserDhePoste(description, date);
        userDhePosteService.doposts(userDhePoste, email);
        List<UserDhePoste> userDhePosteUpdated = userDhePosteService.getAllPosts();
        request.setAttribute("userDhePoste", userDhePosteUpdated);
        request.setAttribute("loggedUser", email);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/feed.jsp");
        dispatcher.forward(request, response);
    }
}
