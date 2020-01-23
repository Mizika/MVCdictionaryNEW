package mmtr.spring.dic.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class Action implements IAllAction {
    private static String path;
    private static Map<String, String> hashMap;
    private static Pattern number = Pattern.compile("\\d{5}");
    private static Pattern letters = Pattern.compile("\\D{4}");
    private static Matcher fileReg;

    @Value("${pathToDic}")
    public void setPath(String path) {
        Action.path = path;
    }
    public static String getPath() {
        return path;
    }

    public static  Map<String, String> readerFromFile(String dicName) {
        hashMap = new HashMap<>();
        File file=new File(getPath() + "//" + dicName);
        try (BufferedReader readFromFile = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = readFromFile.readLine())!= null){
                String[] tmp = line.split(" ");
                hashMap.put(tmp[0], tmp[1]);
            }
            return hashMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> showAllFromDic(String dicName) {
            File file = new File(getPath() + "//" + dicName);
            List<String> listResult = new ArrayList<>();
            try{
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()) {
                    listResult.add(sc.nextLine());
                }
            }catch (Exception err){
                err.printStackTrace();
            }
            return listResult;
        }

    @Override
    public String searchByKey(String dicName, String key) throws IOException {
            Map<String, String> data = readerFromFile(dicName);
            return data.getOrDefault(key, "Значение с данным ключом не существует!");
        }

    @Override
    public String removeFromFile(String dicName, String key) throws IOException {
        Map<String, String> data = readerFromFile(dicName);

        if (data.get(key) != null) {
            FileWriter fstream = new FileWriter(getPath()+"//"+dicName);
            BufferedWriter out = new BufferedWriter(fstream);
            String value = data.get(key);
            data.remove(key);
            for (Map.Entry entry : data.entrySet()) {
                out.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            out.close();
            return "Значение \"" +" "+key+" "+value + "\" было удалено!";
        }else {
            return "Значение с данным ключом не найдено !";
        }
    }

    @Override
    public String addValue(String dicName, String key, String value) throws IOException {
        Map<String, String> data = readerFromFile(dicName);

        if (data.get(key) == null) {
            if (dicName.equals("second.txt")) {
                fileReg = number.matcher(value);
            }
            else if (dicName.equals("first.txt")) {
                fileReg = letters.matcher(value);
            }
            if (fileReg.matches()) {
                FileWriter fstream = new FileWriter(getPath() + "//" + dicName);
                BufferedWriter out = new BufferedWriter(fstream);
                data.put(key, value);

                for (Map.Entry entry : data.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue() + "\n");
                }
                out.close();
                return "Значение \"" + key + " " + value + "\" было добавленно!";
            } else { return "Введено не корректное значение!"; }
        }else { return "Значение с данным ключом уже создано!"; }
    }
}
