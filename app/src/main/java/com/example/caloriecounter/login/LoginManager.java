package com.example.caloriecounter.login;

/**
 * Defines the structure of the login manager.
 */
public interface LoginManager {
    /**
     * Set the login event listener for the manager
     * @param onLoginListener
     */
    void setOnLoginListener(OnLoginListener onLoginListener);

    /**
     * Attempt a login to the applicatin
     * @param username  The login username
     * @param password  The login paossword
     */
    void login(String username, String password);

    /**
     * Logout of the application.
     */
    void logout();

    /**
     * Register a new user for the application
     * @param username          The bew username.
     * @param password          The password.
     * @param passwordCheck     To verify the password.
     */
    void register(String username,String password, String passwordCheck);
    /**
     * Determine if there is a logged in user
     * @return
     */
    boolean isLoggedIn();
    /**
     * Get the username of the currently logged in user
     * @return The username
     */
    String getUsername();
    /**
     * Get the UUID of the currently logged in user
     * @return The UUID
     */
    String getUuid();


}
