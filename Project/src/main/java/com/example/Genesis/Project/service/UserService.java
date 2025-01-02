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
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public User addUser(User user) throws Exception {

        boolean isInFile = isPersonIDInTextFile(user.getPersonID());
        if (!isInFile) {
            return null;
        }

        boolean isInDatabase = isPersonIDInDatabase(user.getPersonID());
        if (isInDatabase) {
            return null;
        }

        String uuid = generateUuid();
        user.setUuid(uuid);
        return userRepository.save(user);
    }
    private boolean isPersonIDInDatabase(String personId) {
        Optional<User> user = userRepository.findByPersonID(personId);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

    private String generateUuid() { return java.util.UUID.randomUUID().toString(); }

     private boolean isPersonIDInTextFile(String personID) {
        String fileName = "src/main/resources/static/dataPersonID";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
             String line;
             while ((line = reader.readLine()) != null) {
                 String[] lineParts = line.split("\n");
                 if (lineParts.length > 0 && lineParts[0].equals(personID)) {
                    return true;
                }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
         return false;
    }

    public Optional<User> getUserById(Long id, boolean detail) {
        Optional<User> user = userRepository.findById(id);

        if (!detail) {
            user.get().setPersonID(null);
            user.get().setUuid(null);
        }

        return user;
    }

    public List<User> getAllUsers(boolean detail) {
        List<User> users = userRepository.findAll();
        if (!detail) {
            for (User u : users) {
                u.setPersonID(null);
                u.setUuid(null);
            }
        }

        return users;
    }
    public User updateUser(User updatedUser) throws Exception {
        Optional<User> userOptional = userRepository.findById(updatedUser.getId());
        if (userOptional.isPresent()) {
             User user = userOptional.get();
             user.setName(updatedUser.getName());
             user.setSurname(updatedUser.getSurname());
             return userRepository.save(user);
        } else {
            throw new Exception("User not found");
        }
    }
    public void deleteUser(Long id) { userRepository.deleteById(id); }

     public String getUserByUuid(String Uuid) { return null; }
     public String getUserByPersonID(String personId) throws IOException {
        String result;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/dataPersonID"))) {
            result = br.readLine();
        }
        return result;
    }

 }










