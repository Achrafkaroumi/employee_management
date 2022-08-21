package com.giantlink.grh.entities;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tbl_company")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String email;
	private String address;
	private String phone;

	@JsonManagedReference
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
	private Set<CompanyEntity> entities;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER , mappedBy = "company")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<CompanyImage> images;
}
