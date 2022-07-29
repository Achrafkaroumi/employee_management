package com.giantlink.grh.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_entity_departement")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "entity_id", nullable = false)
	private CompanyEntity companyEntity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date timestamp;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "departement")
	@JsonManagedReference
	private Set<Team> teams;

	@PrePersist
	private void onCreate() {
		this.timestamp = new Date();
	}
}
