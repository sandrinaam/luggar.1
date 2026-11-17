package com.example.luggar.user.services;

import com.example.luggar.user.User;
import com.example.luggar.user.UserRepository;
import com.example.luggar.common.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService implements Command<Long, Void> {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Void> execute (Long id){
        User user = userRepository.findUserOrThrow(id);

        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }
}
