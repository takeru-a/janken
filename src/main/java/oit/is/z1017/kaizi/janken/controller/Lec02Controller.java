package oit.is.z1017.kaizi.janken.controller;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import oit.is.z1017.kaizi.janken.model.Janken;

@Controller
@RequestMapping("/lec02")

public class Lec02Controller{
 @GetMapping()
  public String lec02() {
    return "lec02.html";
  }
 @PostMapping
  public String lec02(@RequestParam String name, ModelMap model) {
    String username = name;
    model.addAttribute("username", username);
    return "lec02.html";
  }
}
