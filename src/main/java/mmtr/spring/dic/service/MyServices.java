package mmtr.spring.dic.service;

import mmtr.spring.dic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MyServices  {
    private IAllAction action;

    @Autowired
    public MyServices(IAllAction action) {
        this.action = action;
    }

    public List<String> showAllFromDic(String dicName){
        return action.showAllFromDic(dicName);
    }

    public String searchByKey(String dicName, String key) throws IOException {
        return action.searchByKey(dicName, key);
    }

    public String removeFromFile(String dicName, String key) throws IOException {
        return action.removeFromFile(dicName, key);
    }

    public String addValue(String dicName, String key, String value) throws IOException {
        return action.addValue(dicName, key, value);
    }
}
