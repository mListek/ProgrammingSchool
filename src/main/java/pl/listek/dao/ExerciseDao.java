package pl.listek.dao;

import pl.listek.DbUtil;
import pl.listek.model.Exercise;
import pl.listek.model.User;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDao {
    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercises(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY =
            "SELECT * FROM exercises WHERE id = ?";
    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercises SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercises WHERE id = ?";
    private static final String FIND_ALL_EXERCISES_QUERY =
            "SELECT * FROM exercises";

    public Exercise create(Exercise exercise) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                exercise.setExercise_id(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Exercise read(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStm = conn.prepareStatement(READ_EXERCISE_QUERY);
            preStm.setInt(1, id);
            ResultSet resultSet = preStm.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setExercise_id(resultSet.getInt("exercise_id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Exercise exercise) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStm = conn.prepareStatement(UPDATE_EXERCISE_QUERY);
            preStm.setString(1, exercise.getTitle());
            preStm.setString(2, exercise.getDescription());
            preStm.setInt(3, exercise.getExercise_id());
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int exerciseId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Exercise[] addToArray(Exercise e, Exercise[] exercises) {
        Exercise[] tmpExercises = Arrays.copyOf(exercises, exercises.length + 1);
        tmpExercises[exercises.length] = e;
        return tmpExercises;
    }

    public Exercise[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISES_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setExercise_id(resultSet.getInt("exercise_id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises = addToArray(exercise, exercises);
                return exercises;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}//UserDao
