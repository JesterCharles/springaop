package com.revature.spring_mvc.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.revature.spring_mvc.daos.LoginDAO;
import com.revature.spring_mvc.exceptions.AuthenticationException;
import com.revature.spring_mvc.exceptions.InvalidRequestException;
import com.revature.spring_mvc.exceptions.ResourcePersistenceException;
import com.revature.spring_mvc.models.User;
import com.revature.spring_mvc.web.dtos.LoginSuccessResponse;
import com.revature.spring_mvc.web.dtos.SignUp;

@Service
public class UserService {

    public LoginSuccessResponse login(String username, String password) {

        if (username == null || username.equals("") || password == null || password.equals("")) {
            throw new InvalidRequestException("Illegal login credentials provided!"); 
        }

        String uuid = UUID.randomUUID().toString();
        return new LoginSuccessResponse(username, "Bearer" + uuid);

    }
    
    private final LoginDAO loginDAO ;

    public UserService(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public boolean registerNewUser(SignUp signUp) throws ResourcePersistenceException {

        User newUser = new User();
        newUser.setName(signUp.getName());
        newUser.setEmail(signUp.getEmail());
        newUser.setUsername(signUp.getUsername());
        newUser.setPassword(signUp.getPassword());

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provided!");
        }

        boolean usernameAvailable = loginDAO.findByUsername(newUser.getUsername()) == null;
        boolean emailAvailable = loginDAO.findByEmail(newUser.getEmail()) == null;

        if (!usernameAvailable || !emailAvailable) {
            String msg = "The values provided for the following fields are already taken by other users:";
            if (!usernameAvailable) msg = msg + "\n\t- username";
            if (!emailAvailable) msg = msg + "\n\t- email";
            throw new ResourcePersistenceException(msg);
        }

        boolean registeredUser = loginDAO.create(newUser);

        if (!registeredUser) {
            throw new ResourcePersistenceException("The user could not be persisted to the datasource!");
        }


        return true;

    }

    public User authenticateUser(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        User authenticatedUser = loginDAO.findByUsernameAndPassword(username, password);

        if (authenticatedUser == null) {
            throw new AuthenticationException("Unauthorized");
        }

        return authenticatedUser;

    }

    public boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getName() == null || user.getName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }
}