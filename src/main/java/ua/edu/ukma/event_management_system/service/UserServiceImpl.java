package ua.edu.ukma.event_management_system.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.domain.UserRole;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.UserRepository;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public User createUser(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return toDomain(userRepository.save(dtoToEntity(user)));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().
                stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public User getUserById(long userId) {
        return toDomain(userRepository.findById(userId).orElseThrow());
    }

    @Override
    public void updateUser(long id, UserDto updatedUser) {
        Optional<UserEntity> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            UserEntity existingUser = existingUserOpt.get();
            existingUser.setUserRole(updatedUser.getUserRole());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setAge(updatedUser.getAge());

            if (!existingUser.getPassword().equals(updatedUser.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            userRepository.save(existingUser);
        }
    }

    @Override
    public boolean removeUser(long userId) {
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPermission(User user, UserRole role) {
        List<UserRole> list = Arrays.asList(UserRole.values());
        return list.indexOf(user.getUserRole()) >= list.indexOf(role);
    }

    @Override
    public String verify(String username, String password) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (auth.isAuthenticated()) {
            return jwtService.generateToken(username);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        }
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User toDomain(UserEntity user) {
        return modelMapper.map(user, User.class);
    }

    private UserEntity toEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }

    private UserEntity dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
