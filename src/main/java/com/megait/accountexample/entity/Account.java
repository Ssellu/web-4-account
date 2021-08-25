package com.megait.accountexample.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@DynamicUpdate
@DynamicInsert
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    private List<AccountAuthority> authorities;

    @Column(nullable = false)
    private LocalDate regdate;

    public Collection<GrantedAuthority> asGrantedAuthorities(){
        return authorities.stream().map(
                e -> new SimpleGrantedAuthority(e.getAuthority().name())).collect(Collectors.toList()
        );
    }
}
