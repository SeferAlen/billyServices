package com.billy.billyServices.dao;

import com.billy.billyServices.model.Role;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Service for db access actions on {@link Role} model
 */
@Repository
public class RoleDbImpl extends DbConnection implements RoleDb {
    private static final String NAME_NULL = "name must not be null";

    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM ROLE WHERE NAME = ? ";

    /**
     * Method for getting Role from name
     *
     * @param name {@link String} the name
     * @return {@link Role} the role or null if role not found
     */
    public Role findByName(final String name) {
        Objects.requireNonNull(name, NAME_NULL);

        try (final Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             final PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_NAME_QUERY)) {

            preparedStatement.setString(ONE, name);
            final ResultSet rs = preparedStatement.executeQuery();
            Role role = null;

            while (rs.next()) {
                role = new Role(rs.getString(NAME_COLUMN));
            }

            return role;
        } catch (final SQLException e) {
            System.out.println(DB_EXCEPTION + e.getLocalizedMessage());

            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
