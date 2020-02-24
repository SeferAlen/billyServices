package com.billy.billyServices.dao;

import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Service for db access actions on {@link Login} model
 */
@Repository
public class LoginDbImpl extends DbConnection implements LoginDb {
    private static final String USERNAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";
    private static final String BILLY_USERID_COLUMN = "billy_userid";
    private static final String LOGIN_ID_COLUMN = "loginid";
    private static final String ADDRESS_COLUMN = "address";
    private static final String FIRST_NAME_COLUMN = "first_name";
    private static final String LAST_NAME_COLUMN = "last_name";
    private static final String PHONE_COLUMN = "phone";
    private static final String USERNAME_NULL = "username must not be null";

    private static final String FIND_BY_USERNAME_QUERY =
            "select l.loginid, l.username, l.password, b.billy_userid, b.address, b.first_name, b.last_name, b.phone from login l \n" +
            "inner join billy_user b on l.billy_userid = b.billy_userid where l.username = ? ";
    private static final String SELECT_ALL_ROLES = "Select r.roleid, r.name from role r inner join login_roles lr on \n" +
            "lr.roleid = r.roleid inner join login l on l.loginid = lr.loginid where l.loginid = ? ";

    /**
     * Method for getting Login from username
     *
     * @param username {@link String} the username
     * @return {@link Login} the login or null if login not found
     */
    public Login findByUsername(final String username) {
        Objects.requireNonNull(username, USERNAME_NULL);

        try (final Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             final PreparedStatement preparedStatement = conn.prepareStatement(FIND_BY_USERNAME_QUERY)) {

            preparedStatement.setString(ONE, username);
            final ResultSet rs = preparedStatement.executeQuery();
            Login login = null;

            while (rs.next()) {

                 login = new Login(rs.getString(USERNAME_COLUMN),
                                              rs.getString(PASSWORD_COLUMN),
                                              new BillyUser(UUID.fromString(rs.getString(BILLY_USERID_COLUMN)),
                                                            rs.getString(ADDRESS_COLUMN),
                                                            rs.getString(FIRST_NAME_COLUMN),
                                                            rs.getString(LAST_NAME_COLUMN),
                                                            rs.getString(PHONE_COLUMN)
                                              ),
                                              getRoles(rs.getString(LOGIN_ID_COLUMN))
                );
            }

            return login;
        } catch (final SQLException e) {
            System.out.println(DB_EXCEPTION + e.getLocalizedMessage());

            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    /**
     * Method for getting roles from loginId
     *
     * @param loginId {@link String} the loginId to find corresponding {@link Login}
     * @return {@link Set<Role>} the roles
     */
    private Set<Role> getRoles(final String loginId) {

        try (final Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             final PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_ROLES)) {

            preparedStatement.setObject(ONE, UUID.fromString(loginId));
            final ResultSet rs = preparedStatement.executeQuery();
            final Set<Role> roles = new HashSet<>();

            while (rs.next()) {
                roles.add(new Role(rs.getString(NAME_COLUMN)));
            }

            if (roles.size() != 0) return roles;
            else return null;
        } catch (final SQLException e) {
            System.out.println(DB_EXCEPTION + e.getLocalizedMessage());

            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
