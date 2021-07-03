package com.spot.dash.spotdashproducer.service;

import com.spot.dash.spotdashproducer.mappers.UserMapper;
import com.spot.dash.spotdashproducer.model.dto.UserDto;
import com.spot.dash.spotdashproducer.model.entity.User;
import com.spot.dash.spotdashproducer.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final String TOPIC = "users";

    @Autowired
    private static final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public UserDto create(UserDto userDto) {
        var user = userMapper.toModel(userDto);
        user.setId(UUID.randomUUID().toString());

        userRepository.save(user);

        Optional<User> savedUser = userRepository.findById(user.getId());


        final var message = String
                .format(
                        "Usuário criado!%n\tID: %s%n\tNome: %s%n\tIdade: %s",
                        savedUser.get().getId(),
                        savedUser.get().getName(),
                        savedUser.get().getAge().toString()
                );

        kafkaTemplate.send(TOPIC, message);

        return userMapper.toDTO(savedUser.get());
    }
}
