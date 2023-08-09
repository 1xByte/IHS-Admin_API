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
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer planId;

    private String planName;

    @CreationTimestamp
    @Column(insertable = false)
    private LocalDate startDate;

    @UpdateTimestamp
    @Column(updatable = false)
    private LocalDate endDate;

    @Column(name = "catagory")
    private String planCatagory;

    private String planState;


    @CreationTimestamp
    @Column(insertable = false)
    private LocalDate planCreatedDate;

    @UpdateTimestamp
    @Column(updatable = false)
    private LocalDate planUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;


}
