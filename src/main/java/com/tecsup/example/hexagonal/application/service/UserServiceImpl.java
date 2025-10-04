package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.exception.InvalidUserDataException;
import com.tecsup.example.hexagonal.domain.exception.UserLastNameFoundException;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.Role;
import com.tecsup.example.hexagonal.domain.model.User;

//@RequiredArgsConstructor para implementar el constructor por la anotación
public class UserServiceImpl implements UserService {
    //Inyección de dependencias
    private final UserRepository userRepository;
    //Constructor
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User newUser) {
        //Validacion logica puede ser agregado aqui
        validateUserInput(newUser);

        // Set default values
        if (newUser.getRole() == null)
            newUser.setRole(Role.USER);

        //Guardar el usuario usando el repositorio
        User user = this.userRepository.save(newUser);
        //user.setName("Margot"); //Garbage line for testing propurse
        return user;
        //return this.userRepository.save(newUser);
    }

    @Override
    public User findUser(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        User user = this.userRepository.findById(id)
                .orElseThrow( ()-> new UserNotFoundException(id) );
        return user;
    }

    @Override
    public User findUserLastname(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Invalid user lastName");
        }
        User user = this.userRepository.findByLastname(lastName)
                .orElseThrow(() -> new UserLastNameFoundException("lastName"));
        return user;
    }

    private void validateUserInput(User newUser) {

        if (!newUser.hasValidName())
            throw new InvalidUserDataException("Invalid name");

        if (!newUser.hasValidLastname())
            throw new InvalidUserDataException("Invalid lastName");

        if (!newUser.hasValidEmail())
            throw new InvalidUserDataException("Invalid email");


    }
}
