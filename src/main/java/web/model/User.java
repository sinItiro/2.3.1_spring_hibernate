package web.model;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "name non blank")
    @Size(min = 2, max = 32, message = "min/max length name")
    private String name;

    @NotEmpty(message = "surname non blank")
    @Size(min = 2, max = 32, message = "min/max length surname")
    private String surname;

    @Min(value = 0, message = "age > 0")
    private byte age;

    @NotEmpty(message = "login non blank")
    @Size(min = 2, max = 32, message = "min/max length login")
    private String login;
    private static long indexId=0;

    public User() {

    }
    public User(String name, String surname, byte age, String login) {
        this.id = indexId++;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && name.equals(user.name) && surname.equals(user.surname) && login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, login);
    }

    public static long getIndexId() {
        return indexId;
    }

    public static void upIndexId() {
        indexId++;
    }
}
