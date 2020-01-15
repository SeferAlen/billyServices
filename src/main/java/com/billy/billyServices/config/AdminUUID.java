package com.billy.billyServices.config;

import java.util.UUID;

/**
 * Singleton for admin uuid
 */
public class AdminUUID {

    private static AdminUUID instance;
    private UUID adminUUID;

    /**
     * Prevent creating instance
     */
    private AdminUUID() {}

    /**
     * Gets (and if needed create) instance of singleton class
     *
     * @return {@link AdminUUID} the instance
     */
    public static AdminUUID getInstance() {
        if (instance == null) instance = new AdminUUID();
        return instance;
    }

    /**
     * Sets admin UUID
     *
     * @param uuid {@link UUID} the uuid
     */
    public void setAdminUUID(final UUID uuid) {

        instance.adminUUID = uuid;
    }

    /**
     * Gets admin UUID
     *
     * @return {@link UUID} the uuid
     */
    public UUID getAdminUUID() {
        return instance.adminUUID;
    }
}
