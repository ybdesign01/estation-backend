package com.app.estation.entity;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;



@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;
    private String password;
    private String matricule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profile")
    private Profile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<StationUser> stations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PompeUser> pompes;

    public User() {
    }

    public User(String nom, String prenom, String email, String password, String matricule, Profile profile) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.matricule = matricule;
        this.profile = profile;
    }


    public User(Long id, String nom, String prenom, String email, String password, String matricule, Profile profile, List<StationUser> stations, List<PompeUser> pompes) {
        this.id_user = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.matricule = matricule;
        this.profile = profile;
        this.stations = stations;
        this.pompes = pompes;
    }

    public User(Long id, String nom, String prenom, String email, String password, String matricule, Profile profile) {
        this.id_user = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.matricule = matricule;
    }

    public RefreshToken getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(final RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(profile.getNom()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
