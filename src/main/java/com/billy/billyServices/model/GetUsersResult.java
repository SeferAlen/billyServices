package com.billy.billyServices.model;

import com.billy.billyServices.enums.GetUsersStatus;

import java.util.List;

/**
 * Class for result of get user(s) action
 */
public class GetUsersResult {

    private List<BillyUserResponse> users;
    private GetUsersStatus status;

    /**
     * Instantiates a new Get users result.
     *
     * @param status the status
     */
    public GetUsersResult(GetUsersStatus status) {
        this.status = status;
    }

    /**
     * Instantiates a new Get users result.
     *
     * @param users  the users
     * @param status the status
     */
    public GetUsersResult(List<BillyUserResponse> users, GetUsersStatus status) {
        this.users = users;
        this.status = status;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<BillyUserResponse> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(List<BillyUserResponse> users) {
        this.users = users;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public GetUsersStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(GetUsersStatus status) {
        this.status = status;
    }
}
