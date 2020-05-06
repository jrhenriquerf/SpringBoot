package br.biblioteca.livros.services;

import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.models.Role;
import br.biblioteca.livros.repositories.BookRepository;
import br.biblioteca.livros.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}