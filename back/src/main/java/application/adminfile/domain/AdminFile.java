package application.adminfile.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class AdminFile {

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Integer id;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private LocalDate birthDate;

    @NotNull
    @Column
    private Integer socialID;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column
    private String country;

    public Integer getId() {
        return id;
    }
}
