package br.biblioteca.livros.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/books")
@Controller
public class LivroController {
    @GetMapping("/list")
    public ModelAndView home() {
        return new ModelAndView("books/list");
    }

    @GetMapping("/novo")
    public ModelAndView newBook() {
        return new ModelAndView("books/new");
    }

    @PostMapping("/gravar")
    public ModelAndView save(String livro) {
        return new ModelAndView("redirect:/books/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return new ModelAndView("books/edit");
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        return new ModelAndView("redirect:/books/list");
    }
}
