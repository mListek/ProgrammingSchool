package pl.listek.dao;

import pl.listek.DbUtil;
import pl.listek.model.Group;
import pl.listek.model.User;

import java.sql.*;
import java.util.Arrays;

public class GroupDao {
    private static final String CREATE_GROUP_QUERY =
            "INSERT INTO groups(name) VALUES (?)";
    private static final String READ_GROUP_QUERY =
            "SELECT * FROM groups WHERE id = ?";
    private static final String UPDATE_GROUP_QUERY =
            "UPDATE groups SET name = ? WHERE id = ?";
    private static final String DELETE_GROUP_QUERY =
            "DELETE FROM groups WHERE id = ?";
    private static final String FIND_ALL_GROUP_QUERY =
            "SELECT * FROM groups";

    public Group create(Group group) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                group.setGroup_id(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group read(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStm = conn.prepareStatement(READ_GROUP_QUERY);
            preStm.setInt(1, id);
            ResultSet resultSet = preStm.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setGroup_id(resultSet.getInt("group_id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Group group) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStm = conn.prepareStatement(UPDATE_GROUP_QUERY);
            preStm.setString(1, group.getName());
            preStm.setInt(2, group.getGroup_id());
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int group_id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, group_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Group[] addToArray(Group g, Group[] groups) {
        Group[] tmpGroups = Arrays.copyOf(groups, groups.length + 1);
        tmpGroups[groups.length] = g;
        return tmpGroups;
    }

    public Group[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            Group[] groups = new Group[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroup_id(resultSet.getInt("group_id"));
                group.setName(resultSet.getString("name"));
                groups = addToArray(group, groups);
                return groups;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}//UserDao
