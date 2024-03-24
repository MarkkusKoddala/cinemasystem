package com.example.CGI_projekt.service;

import com.example.CGI_projekt.DTO.UserWithPastShowtimesDTO;
import com.example.CGI_projekt.entity.User;
import com.example.CGI_projekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Boolean hasVisitHistory(Long id){
        return userRepository.hasVisitHistory(id);
    }

    public List<UserWithPastShowtimesDTO> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
