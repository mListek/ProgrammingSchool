package pl.listek.controller;

import pl.listek.dao.GroupDao;
import pl.listek.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletGroups", urlPatterns = "/groups")
public class ServletGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDao groupDao = new GroupDao();
        Group[] groups = groupDao.findAll();
        request.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/groups.jsp").forward(request, response);
    }
}
