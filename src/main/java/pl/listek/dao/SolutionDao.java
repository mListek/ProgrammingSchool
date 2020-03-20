package pl.listek.dao;

import pl.listek.DbUtil;
import pl.listek.model.Exercise;
import pl.listek.model.Solution;
import pl.listek.model.User;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {
    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solutions(created, updated, description, exercise_id, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solutions WHERE solution_id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solutions SET created = ?, updated = ?, description = ? , exercise_id = ?, user_id = ? WHERE solution_id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solutions WHERE solution_id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solutions";
    private static final String FIND_ALL_BY_USER_ID =
            "SELECT * FROM solutions WHERE user_id = ?";
    private static final String FIND_RECENT =
            "SELECT * FROM solutions ORDER BY updated DESC LIMIT ?";

    public Solution create(Solution solution) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, solution.getCreated());
            statement.setDate(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUser_id());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                solution.setSolution_id(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStm = conn.prepareStatement(READ_SOLUTION_QUERY);
            preStm.setInt(1, id);
            ResultSet resultSet = preStm.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setSolution_id(resultSet.getInt("solution_id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStm = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            preStm.setDate(1, solution.getCreated());
            preStm.setDate(2, solution.getUpdated());
            preStm.setString(3, solution.getDescription());
            preStm.setInt(4, solution.getExercise_id());
            preStm.setInt(5, solution.getUser_id());
            preStm.setInt(6, solution.getSolution_id());
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Solution[] addToArray(Solution s, Solution[] solutions) {
        Solution[] tmpSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tmpSolutions[solutions.length] = s;
        return tmpSolutions;
    }

    public Solution[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setSolution_id(resultSet.getInt("solution_id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Exercise[] addExerciseToArray(Exercise e, Exercise[] exercises) {
        Exercise[] tmpExercises = Arrays.copyOf(exercises, exercises.length + 1);
        tmpExercises[exercises.length] = e;
        return tmpExercises;
    }

    public Exercise[] findAllByUserId(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement preStm = conn.prepareStatement(FIND_ALL_BY_USER_ID);
            preStm.setInt(1, userId);
            ResultSet resultSet = preStm.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setExercise_id(resultSet.getInt("exercise_id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises = addExerciseToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Solution[] findRecent(int nr) {
        try (Connection conn = DbUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement preStm = conn.prepareStatement(FIND_RECENT);
            preStm.setInt(1, nr);
            ResultSet resultSet = preStm.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setSolution_id(resultSet.getInt("solution_id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(resultSet.getInt("exercise_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}//SolutionDao
