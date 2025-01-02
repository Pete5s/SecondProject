package com.example.Genesis.Project.service;

import com.example.Genesis.Project.model.User;
import com.example.Genesis.Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) throws Exception {
        if (!userRepository.findByPersonID(user.getPersonID()).isPresent()) {
            return userRepository.save(user);
        }
        throw new Exception("PersonID already exists!");
    }
    public Optional<User> getUserById(Long id, boolean detail) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers(boolean detail) {
        return userRepository.findAll();
    }
    public User updateUser(User updatedUser) throws Exception {
        if (updatedUser == null || updatedUser.getId() == null) {
            throw new IllegalArgumentException("Argument updatedUser or its id must not be null.");
        }
        Optional<User> userOptional = userRepository.findById(updatedUser.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setUuid(updatedUser.getUuid());
            return userRepository.save(user);
        } else {
            throw new Exception("User not found");
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public String getUserByUuid(String Uuid) {
        return null;
    }
    public String getUserByPersonID(String personId) throws IOException {
        String result;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/dataPersonID"))) {
            result = br.readLine();

        }

        return result;
    }



}




