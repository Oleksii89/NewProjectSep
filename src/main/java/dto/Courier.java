package dto;

public class Courier {
    private Long id;
    private String login;
    private String password;
    private String name;

    public Long getId() {
        return id;
    }
    public void setId (Long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id='" + id + '\''+
                "login='" + login + '\''+
                ", password='" + password + '\''+
                ", name='" + name + '\'' +
               '}';
    }

}
