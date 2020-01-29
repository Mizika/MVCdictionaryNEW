package mmtr.spring.dic.model;

import javax.persistence.*;

@Entity
@Table(name = "second")
public class SecondDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "key")
    private String key;

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
