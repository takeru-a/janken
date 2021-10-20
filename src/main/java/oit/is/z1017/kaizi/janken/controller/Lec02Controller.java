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
import org.springframework.transaction.annotation.Transactional;
import oit.is.z1017.kaizi.janken.model.Janken;
import oit.is.z1017.kaizi.janken.model.Entry;
import oit.is.z1017.kaizi.janken.model.User;
import oit.is.z1017.kaizi.janken.model.UserMapper;
import oit.is.z1017.kaizi.janken.model.Match;
import oit.is.z1017.kaizi.janken.model.MatchMapper;

@Controller
public class Lec02Controller{
  @Autowired
  private Entry entry;
  @Autowired
  UserMapper userMapper;
  @Autowired
  MatchMapper matchMapper;

 @GetMapping("/match")
 public String match(@RequestParam String name, ModelMap model){
   model.addAttribute("username",name);
   return "match.html";
 }

 @GetMapping("/lec02janken")
 @Transactional
  public String lec02janken(@RequestParam String hand, Principal prin, ModelMap model) {
  Janken janken = new Janken();
  Match match = new Match();
  String loginUser = prin.getName();
  String myhand = hand;
  janken.setHand(myhand);
  User user1 = userMapper.selectByname(loginUser);
  User user2 = userMapper.selectByname("CPU");
  match.setUser1(user1.getId());
  match.setUser2(user2.getId());
  match.setUser1Hand(myhand);
  match.setUser2Hand(janken.getCpuhand());
  model.addAttribute("hand", myhand);
  model.addAttribute("cpuhand", janken.getCpuhand());
  model.addAttribute("result", janken.getResult());
  matchMapper.insertMatch(match);
  return "match.html";
  }

@GetMapping("/lec02")
@Transactional
  public String lec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("room", this.entry);
    model.addAttribute("login_user", loginUser);
    ArrayList<User> users = userMapper.selectAllUsers();
    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("Users",users);
    model.addAttribute("Matches",matches);

    return "lec02.html";
  }


}
