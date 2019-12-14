package com.billy.billyServices.model;

import com.billy.billyServices.enums.GetUsersStatus;

import java.util.List;

public class GetUsersResult {

    private List<BillyUserResponse> users;
    private GetUsersStatus status;

    public GetUsersResult(GetUsersStatus status) {
        this.status = status;
    }

    public GetUsersResult(List<BillyUserResponse> users, GetUsersStatus status) {
        this.users = users;
        this.status = status;
    }

    public List<BillyUserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<BillyUserResponse> users) {
        this.users = users;
    }

    public GetUsersStatus getStatus() {
        return status;
    }

    public void setStatus(GetUsersStatus status) {
        this.status = status;
    }
}
