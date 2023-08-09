package com.adminapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String userName;

    private String userEmail;

    private String userPwd;

    @Column(name = "phone_number")
    private String userPhoneNumber;

    private String gender;

    private LocalDate dob;

    private String ssn;

    private String status ;

    @Column(name = "state")
    private String userState;

    @CreationTimestamp
    @Column(insertable = false)
    private LocalDate userCreatedDate;

    @UpdateTimestamp
    @Column(updatable = false)
    private LocalDate userUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;


}
