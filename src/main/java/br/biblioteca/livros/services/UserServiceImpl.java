package br.biblioteca.livros.services;

import br.biblioteca.livros.models.Author;
import br.biblioteca.livros.models.User;
import br.biblioteca.livros.repositories.RoleRepository;
import br.biblioteca.livros.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(
                user.getPassword()));

        user.getRoles().add(roleRepository.findByRole("ROLE_VISUALIZAR"));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}