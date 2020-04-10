package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImp;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private UserService userServiceImp;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printCars(ModelMap model) {
        model.addAttribute("listPersons", userServiceImp.listUsers());
        return "users";
    }

    @GetMapping(value = "edit")
    public String editCar(ModelMap model) {
        User user = new User();
        model.addAttribute("userr", user);
        return "edit";
    }

    @PostMapping(value = "edit")
    public String editCar(@ModelAttribute("listPersons") User user, ModelMap model) {
        userServiceImp.updateUser(user);
        model.addAttribute("listPersons", userServiceImp.listUsers());
        return "users";
    }

    @PostMapping(value = "newuser")
    public String createUser(@ModelAttribute("listPersons") User user, ModelMap model) {
        userServiceImp.add(user);
        model.addAttribute("listPersons", userServiceImp.listUsers());
        return "users";
    }

    @GetMapping(value = "newuser")
    public String createUsers(ModelMap model) {
        User users = new User();
        model.addAttribute("users", users);
        return "newUser";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam Long id, ModelMap model) {
        userServiceImp.deleteUser(id);
        model.addAttribute("listPersons", userServiceImp.listUsers());
        return "users";
    }
}
