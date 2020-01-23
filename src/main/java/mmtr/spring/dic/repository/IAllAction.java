package mmtr.spring.dic.repository;

import java.io.IOException;
import java.util.List;

public interface IAllAction {
    List<String> showAllFromDic(String dicName);

    String searchByKey(String dicName, String key) throws IOException;

    String removeFromFile(String dicName, String key) throws IOException;

    String addValue(String dicName, String key, String value) throws IOException;
}
