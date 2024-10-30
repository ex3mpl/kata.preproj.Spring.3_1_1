package kata.preproj.controller;

import jakarta.validation.Valid;
import kata.preproj.model.User;
import kata.preproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    // CRUD methods
    // adding a new user
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.save(user);
        return "redirect:/index";
    }

    // get user by id
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);

        model.addAttribute("user", user);
        return "update-user";
    }

    // update user by id
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userService.save(user);
        return "redirect:/index";
    }

    // delete user by id
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        userService.delete(user.getId());
        return "redirect:/index";
    }



    // mapping for the /index URL
    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }
}
