package com.giantlink.grh.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tbl_occupation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOccupation;
    private boolean isCurrent;
    private String occupationName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "employee-occupation")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    @JsonBackReference(value = "job-occupation")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Job job;
}
