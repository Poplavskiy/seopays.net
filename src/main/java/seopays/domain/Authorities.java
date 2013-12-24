
package seopays.domain;


import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

/*
    @ManyToOne
    @JoinColumn(name="username")
    private User user;
*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

/*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
*/
}
