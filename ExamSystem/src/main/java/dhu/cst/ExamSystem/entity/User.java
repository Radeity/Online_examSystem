package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user")
public class User  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String roles;

    @Column(name = "password")
    private String password;

    @Column(name = "adminclass")
    private String adminclass;

    @Column(name = "gender")
    private String gender;

    @Column(name = "department")
    private String department;

    @Column(name = "state")
    private long state;

    public String getName() {
        return name;
    }
}