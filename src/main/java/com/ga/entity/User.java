package com.ga.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts;
	
	@OneToMany(mappedBy = "userComment", cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	public User() {
		
	}
	
	
	
	
	
}
