package com.example.caloriecounter.login;

/**
 * Defines the structure of the login manager
 */

public interface LoginManager {

    /**
     * Attempt a login
     * @param username The login username.
     * @param password The login password
     */
    void login(String username, String password);

    /**
     * Logout of the application.
     */
    void logout();

    /**
     * Register a new user for the application.
     * @param username The new username.
     * @param password The password.
     * @param passwordCheck To verify the password.
     */
    void register(String username, String password, String passwordCheck);

    /**
     * Determine if there's a logged in user.
     * @return
     */
    boolean isLoggedIn();

    /**
     * Get the username of the currently logged in user.
     * @return The username
     */
    String getUsername();

    /**
     * Get the UUID of the currently logged in user.
     * @return The UUID.
     */
    String getUid();

    /**
     * Set the login event listener for the manager
     * @param onLoginListener
     */
    void setOnLoginListener(OnLoginListener onLoginListener);
}
