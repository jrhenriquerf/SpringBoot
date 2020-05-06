package br.biblioteca.livros.controllers;

import br.biblioteca.livros.models.Author;
import br.biblioteca.livros.models.Role;
import br.biblioteca.livros.models.User;
import br.biblioteca.livros.services.RoleService;
import br.biblioteca.livros.services.SecurityService;
import br.biblioteca.livros.services.UserService;
import br.biblioteca.livros.validator.LoginValidator;
import br.biblioteca.livros.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserControllers {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private LoginValidator loginValidator;

	@Autowired
	private RoleService roleService;

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("user/form", "userForm", new User());
	}

	@PostMapping("/autentication")
	public ModelAndView autentication(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

		loginValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("user/form");
		}

		securityService.login(userForm.getUsername(), userForm.getPassword());
		return new ModelAndView("redirect:/books/list");
	}

	@GetMapping("/list")
	public ModelAndView list(User user) {
		List<User> users = userService.findAll();

		return new ModelAndView("user/list", "users", users);
	}

	@GetMapping(value = "/registration")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView("user/registration");
		modelAndView.addObject("userForm", new User());

		if (hasRole("ROLE_GERENCIA_USUARIOS")) {
			List<Role> roles = roleService.findAll();

			modelAndView.addObject("roles", roles);
		}

		return  modelAndView;
	}

	@PostMapping(value = "/registration")
	public ModelAndView registrationform(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("user/registration");

			if (hasRole("ROLE_GERENCIA_USUARIOS")) {
				List<Role> roles = roleService.findAll();
				modelAndView.addObject("roles", roles);
			}

			return modelAndView;
		}
		
		String password = userForm.getPassword();

		userService.save(userForm);

		if (hasRole("ROLE_GERENCIA_USUARIOS")) {
			return new ModelAndView("redirect:/user/list");
		}

		try {
			securityService.login(userForm.getUsername(), password);
			return new ModelAndView("redirect:/books/list");
			
		} catch (Exception e) {
			
			return new ModelAndView("redirect:/user/login");
		}
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		List<Role> roles = roleService.findAll();

		ModelAndView modelAndView = new ModelAndView("user/registration");
		modelAndView.addObject("userForm", user);
		modelAndView.addObject("roles", roles);

		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ModelAndView("redirect:/user/list");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/user/login";
	}

	private boolean hasRole(String roleName)
	{
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
	}

}
