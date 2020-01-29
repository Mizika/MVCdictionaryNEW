package mmtr.spring.dic.service;

import mmtr.spring.dic.repository.IAllActionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MyServicesTest implements IAllActionDB {
    private IAllActionDB actionDB;

    @Autowired
    public MyServicesTest(IAllActionDB actionDB) {
        this.actionDB = actionDB;
    }

    public List<String> showAllFromDic(String dicName){
        return actionDB.showAllFromDic(dicName);
    }

    @Override
    public String searchByKey(String dicName, String key) throws IOException { return actionDB.searchByKey(dicName, key); }

    @Override
    public String removeFromFile(String dicName, String key) throws IOException {
        return actionDB.removeFromFile(dicName, key);
    }

    @Override
    public String addValue(String dicName, String key, String value) throws IOException {
        return actionDB.addValue(dicName, key, value);
    }
}
