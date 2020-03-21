package pl.listek.controller;

import pl.listek.dao.SolutionDao;
import pl.listek.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletHome")
public class ServletHome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        int nr = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        Solution[] solutions = solutionDao.findRecent(nr);
        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
