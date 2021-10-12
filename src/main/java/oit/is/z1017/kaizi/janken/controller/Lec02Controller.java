package oit.is.z1017.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z1017.kaizi.janken.model.Janken;
import oit.is.z1017.kaizi.janken.model.Entry;

@Controller
public class Lec02Controller{
  @Autowired
  private Entry entry;

 @GetMapping("/lec02janken")
  public String lec02janken(@RequestParam String hand, ModelMap model) {
  Janken janken = new Janken();
  String myhand = hand;
  janken.setHand(myhand);

  model.addAttribute("hand", myhand);
  model.addAttribute("cpuhand", janken.getCpuhand());
  model.addAttribute("result", janken.getResult());
  return "lec02.html";
  }

@GetMapping("/lec02")
  public String lec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("room", this.entry);
    model.addAttribute("login_user", loginUser);

    return "lec02.html";
  }


}
