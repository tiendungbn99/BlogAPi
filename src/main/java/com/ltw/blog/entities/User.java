package com.ltw.blog.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(name = "user_name", nullable = false, length = 100)
	private String fullName;
	
	private String email;
	
	private String password;
	
	private String about;
	
	@OneToMany(mappedBy = "user", 
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	 @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	 @JoinTable(name = "users_roles",
	            joinColumns = {@JoinColumn(name = "user_id")},
	            inverseJoinColumns = {@JoinColumn(name = "role_id")})
	    private Set<Role> roles;
}
