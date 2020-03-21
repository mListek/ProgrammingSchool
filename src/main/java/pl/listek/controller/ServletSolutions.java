package pl.listek.controller;

import pl.listek.dao.ExerciseDao;
import pl.listek.dao.SolutionDao;
import pl.listek.dao.UserDao;
import pl.listek.model.Exercise;
import pl.listek.model.Solution;
import pl.listek.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletSolutions", urlPatterns = "/solution")
public class ServletSolutions extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();

        int solution_id = Integer.parseInt(request.getParameter("solution_id"));

        Solution solution = solutionDao.read(solution_id);
        User user = userDao.read(solution.getUser_id());
        Exercise exercise = exerciseDao.read(solution.getExercise_id());

        request.setAttribute("username", user.getUserName());
        request.setAttribute("solution", solution);
        request.setAttribute("exercise", exercise);
        getServletContext().getRequestDispatcher("/solution.jsp").forward(request, response);
    }
}
