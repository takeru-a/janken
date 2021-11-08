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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import oit.is.z1017.kaizi.janken.model.Janken;
import oit.is.z1017.kaizi.janken.model.Entry;
import oit.is.z1017.kaizi.janken.model.User;
import oit.is.z1017.kaizi.janken.model.UserMapper;
import oit.is.z1017.kaizi.janken.model.Match;
import oit.is.z1017.kaizi.janken.model.MatchMapper;
import oit.is.z1017.kaizi.janken.model.MatchInfoMapper;
import oit.is.z1017.kaizi.janken.model.MatchInfo;
import oit.is.z1017.kaizi.janken.service.AsyncKekka;

@Controller
public class Lec02Controller{
  @Autowired
  AsyncKekka kka;
  @Autowired
  private Entry entry;
  @Autowired
  private MatchInfo matchinfo;
  @Autowired
  UserMapper userMapper;
  @Autowired
  MatchMapper matchMapper;
  @Autowired
  MatchInfoMapper matchinfoMapper;

 @GetMapping("/match")
 public String match(@RequestParam int id,Principal prin,ModelMap model){
   String loginUser = prin.getName();
   User user = new User();
   user = userMapper.selectByid(id);
   matchinfo.setUser2(id);
   model.addAttribute("you",user.getName());
   model.addAttribute("yourid",id);
   model.addAttribute("username",loginUser);
   return "match.html";
 }

 @GetMapping("/lec02janken")
 @Transactional
  public String lec02janken(@RequestParam String hand,Principal prin, ModelMap model) {
  Match match = new Match();
  String loginUser = prin.getName();
  String myhand = hand;
  User user1 = userMapper.selectByname(loginUser);
  ArrayList<Match> matches = matchMapper.selectActiveMatch();
  matchinfo.setUser1(user1.getId());
  matchinfo.setUser1Hand(myhand);
  matchinfo.setIsActive(true);
  model.addAttribute("login_user", loginUser);
  if (matchinfoMapper.selectByuser(user1.getId())){
    matchinfo = matchinfoMapper.selectByuser(user1.getId())
    match.setUser1 = matchinfo.getUser2();
    match.setUser2 = user1.getId();
    match.setUser1Hand = matchinfo.getUser1Hand();
    match.setUser2Hand = myhand;
    match.setIsActive = true;
    matchMapper.insertMatch(Match match);
  }else{
    matchinfoMapper.insertMatchInfo(matchinfo);
  }
  model.addAttribute("matches",matches);

  return "wait.html";
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
    ArrayList<MatchInfo> matchinfos = matchinfoMapper.selectActiveMatchinfo();
    int flag = 0;
    for (int i=0;i<users.size();i++){
          User user = new User();
          user = users.get(i);
          if (user.getName().equals(loginUser)){
            flag = 1;
          }
    }
    if(flag==0){
      User user = new User();
      user.setName(loginUser);
      userMapper.insertUser(user);
    }

    model.addAttribute("Users",users);
    model.addAttribute("Matches",matches);
    model.addAttribute("matchinfos",matchinfos);

    return "lec02.html";
  }

  @GetMapping("lec02janken")
  public SseEmitter kekka() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.kka.kekka(sseEmitter);
    return sseEmitter;
  }


}
