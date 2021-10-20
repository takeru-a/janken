package oit.is.z1017.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import oit.is.z1017.kaizi.janken.model.User;

@Mapper
public interface UserMapper {
  @Select("select id, name from users;")
  ArrayList<User> selectAllUsers();
  @Select("select id, name from users where name = #{name}")
  User selectByname(String name);

}
