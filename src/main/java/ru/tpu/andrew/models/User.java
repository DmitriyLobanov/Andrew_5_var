package ru.tpu.andrew.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tpu.andrew.enums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_info")
@Data
@RequiredArgsConstructor
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", columnDefinition = "varchar(10) default 'ROLE_USER'")
    private RoleEnum role;

//    @Column(name="messages_received", columnDefinition = "bigint default 0")
//    private Long messagesReceived;

//    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
//    private List<Message> messages;
//
//    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
//    private List<Message> messages;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List< SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
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
}
