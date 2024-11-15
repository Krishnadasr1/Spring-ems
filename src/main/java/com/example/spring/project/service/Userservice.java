package com.example.spring.project.service;

import com.example.spring.project.model.User;
import com.example.spring.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Userservice {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public Userservice(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registeruser(User user){
        Optional<User> existingemailuser = userRepository.findByEmail(user.getEmail());
        if(existingemailuser.isPresent()){
            return "email already exists";
        }
        Optional<User> existingphoneuser = userRepository.findByPhone(user.getPhone());
        if(existingphoneuser.isPresent()){
            return "phone already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "user registered successfully";
    }

    public String loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();

        if (passwordEncoder.matches(password, user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid password";
        }
    }

    public List<User> listusers(){
     return userRepository.findAll();
    }

    public Optional<User> getbyid(long id){
        return userRepository.findById(id);
    }

    public String updateUser(Long id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            // Only update fields if they are not null in updatedUser
            if (updatedUser.getName() != null) {
                existingUser.setName(updatedUser.getName());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPhone() != null) {
                existingUser.setPhone(updatedUser.getPhone());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }

            // Save the updated user to the database
            userRepository.save(existingUser);
            return "User updated successfully";
        } else {
            return "User not found";
        }
    }

    public String deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);  // Deletes the user from the database
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }
}
