package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserDAO userDAO;

    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String usersList(ModelMap model) {

        //HttpServletRequest request
        //String count = request.getParameter("count");
        model.addAttribute("users", userDAO.usersList());
        return "users/users";
    }

    @GetMapping(value = "/{id}")
    public String getUser(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.getUser(id));
        return "users/user";
    }

    @GetMapping(value = "/new")
    public String getUser(ModelMap model) {
        model.addAttribute(new User());
        return "users/new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/new";
        }
        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", userDAO.getUser(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "/users/edit";
        }
        userDAO.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDAO.delete(id);
        return "redirect:/users";
    }
}






