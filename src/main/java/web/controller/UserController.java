package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/cars")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printCars(ModelMap model) {
        model.addAttribute("listPersons", userServiceImpl.listUsers());
        return "users";
    }

    @GetMapping(value = "edit")
    public String editCar(@RequestParam Long id, ModelMap model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("userr", user);
        return "edit";
    }

    @PostMapping(value = "edit")
    public String editCar(@ModelAttribute("listPersons") User user, ModelMap model) {
        userServiceImpl.updateUser(user);
        model.addAttribute("listPersons", userServiceImpl.listUsers());
        return "redirect:/cars/";
    }

    @PostMapping(value = "newuser")
    public String createUser(@ModelAttribute("listPersons") User user, ModelMap model) {
        if(userServiceImpl.checkUser(user)) {
            userServiceImpl.add(user);
            model.addAttribute("listPersons", userServiceImpl.listUsers());
            return "redirect:/cars/";
        }
        return "redirect:error";
    }

    @GetMapping(value = "newuser")
    public String createUsers(ModelMap model) {
        User users = new User();
        model.addAttribute("users", users);
        return "newUser";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam Long id, ModelMap model) {
        userServiceImpl.deleteUser(id);
        model.addAttribute("listPersons", userServiceImpl.listUsers());
        return "redirect:/cars/";
    }

    @GetMapping("error")
    public String errorUser(ModelMap model) {
        return "error";
    }
}
