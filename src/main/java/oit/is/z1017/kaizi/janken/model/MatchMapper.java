package oit.is.z1017.kaizi.janken.model;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("SELECT * from matches;")
  ArrayList<Match> selectAllMatches();
  @Select("SELECT * from match where isActive=true;")
  ArrayList<Match> selectActiveMatch();
  @Insert("INSERT INTO matches (user1,user2,user1Hand, user2Hand) VALUES (#{user1},#{user2},#{user1Hand},#{user2Hand});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match match);
  @Update("UPDATE MATCHINFO SET isActive=false WHERE id = #{id}")
  void updateById(int id);
}
