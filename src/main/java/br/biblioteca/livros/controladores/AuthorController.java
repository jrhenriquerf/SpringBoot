package br.biblioteca.livros.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @GetMapping("/list")
    public ModelAndView home() {
        return new ModelAndView("authors/list");
    }

    @GetMapping("/novo")
    public ModelAndView newBook() {
        return new ModelAndView("authors/new");
    }

    @PostMapping("/gravar")
    public ModelAndView save(String author) {
        return new ModelAndView("redirect:/authors/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return new ModelAndView("authors/edit");
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        return new ModelAndView("redirect:/authors/list");
    }
}
