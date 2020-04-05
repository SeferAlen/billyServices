package com.billy.billyServices.model;

import com.billy.billyServices.BillyServicesApplication;
import com.billy.billyServices.enums.GetUsersStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for result of get user(s) action
 */
public class GetUsersResult {

    private List<BillyUserResponse> users;
    private GetUsersStatus status;

    /**
     * Prevent instance creation
     */
    private GetUsersResult() {
    }

    /**
     * Builder class for {@link GetUsersResult}
     */
    public static class Builder {

        private List<BillyUserResponse> users;
        private GetUsersStatus status;

        /**
         * Instantiates a new Builder.
         *
         * @param getUsersStatus the get users status
         */
        public Builder(GetUsersStatus getUsersStatus) {
            this.status = getUsersStatus;
            this.users = new ArrayList<>();
        }

        /**
         * With users builder.
         *
         * @param users the users
         * @return the builder
         */
        public Builder withUsers(List<BillyUserResponse> users) {
            this.users = users;

            return this;
        }

        /**
         * Build get users result.
         *
         * @return the get users result
         */
        public GetUsersResult build() {

            GetUsersResult getUsersResult = new GetUsersResult();
            getUsersResult.users = this.users;
            getUsersResult.status = this.status;

            return getUsersResult;
        }
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
     * Gets status.
     *
     * @return the status
     */
    public GetUsersStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (!(obj instanceof GetUsersResult)) return false;

        final GetUsersResult comparable = (GetUsersResult) obj;

        return this.users.equals(comparable.users) &&
               this.status.equals(comparable.status);
    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + users.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
