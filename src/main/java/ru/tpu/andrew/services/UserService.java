package ru.tpu.andrew.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tpu.andrew.dtos.UserDto;
import ru.tpu.andrew.enums.RoleEnum;
import ru.tpu.andrew.exceptions.UserAlreadyExistException;
import ru.tpu.andrew.exceptions.UserNotFoundException;
import ru.tpu.andrew.models.User;
import ru.tpu.andrew.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;



    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDto createUser(UserDto userDto) {
        Optional<User> user = userRepository.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("Username already exists");
        }
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setRole(RoleEnum.ROLE_USER);
        userRepository.save(newUser);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }
}
