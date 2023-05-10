package pl.edu.pw.PRK.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.pw.PRK.service.MovieService;

@Controller
public class HomeController {

    private MovieService movieService;

    @Autowired
    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/home")
    public String showHome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userName", authentication.getName());

        if(authentication.getName().equals("anonymousUser")){
            model.addAttribute("userAuthorities","none");
        }else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("userAuthorities", userDetails.getAuthorities());
        }

        return "home";
    }

}