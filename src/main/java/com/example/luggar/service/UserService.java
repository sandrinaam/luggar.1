package com.example.luggar.service;

import com.example.luggar.dto.UserRequest;
import com.example.luggar.dto.UserResponse;
import com.example.luggar.entity.User;
import com.example.luggar.exception.DuplicateResourceException;
import com.example.luggar.exception.ResourceNotFoundException;
import com.example.luggar.mapper.UserMapper;
import com.example.luggar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(UserRequest request){
        if (userRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("Email " + request.email() + " is already used.");
        }

        User user = userMapper.toEntity(request);

        User saved = userRepository.save(user);

        return userMapper.toResponse(saved);
    }

    private User findUserOrThrow(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " +id + " not found."));
    }

    public UserResponse getUserById(Long id){
        return  userMapper.toResponse(findUserOrThrow(id));
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(userMapper::toResponse)
                .toList();

    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User user = findUserOrThrow(id);

        if (!request.email().equals(user.getEmail()) && userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email " + request.email() + " is already used.");
        }

        userMapper.updateEntity(user, request);

        User saved = userRepository.save(user);

        return userMapper.toResponse(saved);
    }

    public void deleteUser(Long id){
        User user = findUserOrThrow(id);

        userRepository.delete(user);

    }

}
