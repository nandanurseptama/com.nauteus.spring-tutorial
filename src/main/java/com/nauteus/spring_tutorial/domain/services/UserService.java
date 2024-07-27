package com.nauteus.spring_tutorial.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Validator;

import com.nauteus.spring_tutorial.commons.exception.BadRequestException;
import com.nauteus.spring_tutorial.data.entities.UserEntity;
import com.nauteus.spring_tutorial.data.repositories.UserRepository;
import com.nauteus.spring_tutorial.domain.requests.UserRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public Validator validator;

    @Transactional
    public void registerWithEmail(UserRequest.RegisterWithEmailRequest request) {
        final Set<ConstraintViolation<UserRequest.RegisterWithEmailRequest>> errors = validator.validate(request);

        if (errors.size() > 0) {
            throw new ConstraintViolationException(
                    errors);
        }
        final Optional<UserEntity> findUser = userRepository.findOneByEmail(request.getEmail());

        if (findUser.isPresent()) {
            throw BadRequestException.builder().build().setError("email", "email already taken");
        }

        UserEntity newUser = new UserEntity();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());

        userRepository.save(newUser);
        return;
    }
}
