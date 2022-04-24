package io.swagger.service;

import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User User) {
        return userRepository.save(User);
    }

    @Override
    public User deleteUserById(long id) {
        Optional<User> os = userRepository.findById(id);
        if(os.isPresent()) userRepository.deleteById(id);
        return os.get();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        Optional<User> os = userRepository.findById(id);
        if(os.isPresent()) return os.get();
        else return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return  userRepository.findUserByEmail(email);
    }
}
