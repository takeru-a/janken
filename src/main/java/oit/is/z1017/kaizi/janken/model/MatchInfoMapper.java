package oit.is.z1017.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Select("SELECT * from matchinfo where isActive=true;")
  ArrayList<MatchInfo> selectActiveMatchinfo();
  @Select("SELECT * from matchinfo where isActive=true and user2=#{user};")
  MatchInfo selectByuser(int user);
  @Insert("INSERT INTO matchinfo (user1,user2,user1Hand,isActive) VALUES (#{user1},#{user2},#{user1Hand},#{isActive});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(MatchInfo matchinfo);
  @Update("UPDATE MATCHINFO SET isActive=false WHERE id = #{id}")
  void updateById(int id);



}
