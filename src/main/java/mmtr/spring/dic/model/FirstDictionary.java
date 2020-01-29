package mmtr.spring.dic.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "first" )
public class FirstDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "key", unique=true)
    private String key;

    @Pattern(regexp = "\\D{4}")
    @Column(name = "value")
    private String value;

    public FirstDictionary() {}

    public FirstDictionary(String key, String value) {
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
