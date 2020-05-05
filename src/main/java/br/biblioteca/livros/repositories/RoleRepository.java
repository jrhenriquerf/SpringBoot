package br.biblioteca.livros.repositories;

import br.biblioteca.livros.models.Role;
import br.biblioteca.livros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
