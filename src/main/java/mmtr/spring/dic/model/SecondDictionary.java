package mmtr.spring.dic.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "second")
public class SecondDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "key")
    private String key;

    @Pattern(regexp = "\\d{5}")
    @Column(name = "value")
    private String value;

    public SecondDictionary() {}

    public SecondDictionary(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getId() { return id; }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    @Override
    public String toString() {
        return key + " " + value;
    }

}
