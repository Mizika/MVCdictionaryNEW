package mmtr.spring.dic.controller;

import mmtr.spring.dic.service.MyServicesDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MyController {
    private String dictionaryName;
    private String keyValue;
    private String value;

    private MyServicesDB myServices;

    @Autowired
    public MyController(MyServicesDB myServices) {
        this.myServices = myServices;
    }

    @GetMapping("/")
    public String getHome(){
        return "main";
    }

    @PostMapping("/")
    public String postHome(@RequestParam String dictionaryName) {
        this.dictionaryName = dictionaryName;
        return "menu";
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu";
    }

    @GetMapping("/showAll")
    public void getShowAll(Model model) {
        model.addAttribute("records", myServices.showAllFromDic(dictionaryName));
        }

    @GetMapping("/keyForm")
    public String getKey(){
        return "keyForm";
    }

    @PostMapping("/searchByKey")
    public String searchByKey(@RequestParam("keyValue") String keyValue, Model model) throws IOException {
        this.keyValue = keyValue;
        model.addAttribute("byKey", myServices.searchByKey(dictionaryName, keyValue));
        return "searchByKey";
    }

    @GetMapping("/removeKeyForm")
    public String getRemoveKey(){
        return "removeKeyForm";
    }

    @PostMapping("/removeValue")
    public String removeFromFileByKey(@RequestParam("keyValue") String keyValue, Model model) throws IOException {
        this.keyValue = keyValue;
        model.addAttribute("removeV", myServices.removeFromFile(dictionaryName, keyValue));
        return "removeValue";
    }

    @GetMapping("/addKeyForm")
    public String getAddKey(){
        switch (dictionaryName){
            case "first.txt":
                return "addKeyForm";
            case "second.txt":
                return "addkeyFormSecond";
        }
        return null;
    }

    @PostMapping("/addValue")
    public String addValue(@RequestParam String keyValue, String value, Model model) throws IOException {
        this.keyValue = keyValue;
        this.value = value;
        model.addAttribute("addV", myServices.addValue(dictionaryName, keyValue, value));
        return "addValue";
    }


}

