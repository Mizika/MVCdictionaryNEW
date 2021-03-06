package mmtr.spring.dic.repository;

import java.io.IOException;
import java.util.List;

public interface IAllActionDB {
    List<?> showAllFromDic(String dicName) throws Exception;

    String searchByKey(String dicName, String key) throws IOException;

    String removeFromFile(String dicName, String key) throws IOException;

    String addValue(String dicName, String key, String value) throws IOException;

    String generateData();
}
