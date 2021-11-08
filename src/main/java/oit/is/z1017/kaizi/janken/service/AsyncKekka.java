package oit.is.z1017.kaizi.janken.service;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import oit.is.z1017.kaizi.janken.model.MatchMapper;
import oit.is.z1017.kaizi.janken.model.Match;
import org.springframework.beans.factory.annotation.Autowired;

@Autowired
MatchMapper matchMapper;
@Service
public class AsyncKekka {
  @Async
  public void kekka(SseEmitter emitter) {
    dbUpdated = true;
    try {
      while (true) {// 無限ループ
        if (false == dbUpdated) {
          continue;
        }
        // DBが更新されていれば更新後のフルーツリストを取得してsendし，1s休み，dbUpdatedをfalseにする
        ArrayList<Match> matches = matchMapper.selectActiveMatch();
        emitter.send(matches);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
        Mapper.updateById(matches);
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
  }
}
