package mmtr.spring.dic.repository;

import java.io.IOException;
import java.util.List;

public interface IAllActionDB {
    List<String> showAllFromDic(String dicName);

    String searchByKey(String dicName, String key) throws IOException;
}
