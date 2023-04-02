package com.kolay.library.service;

import com.kolay.library.data.Role;
import com.kolay.library.data.UserData;
import com.kolay.library.dto.UserSaveDto;
import com.kolay.library.dto.UserShowDto;
import com.kolay.library.repository.RoleRepository;
import com.kolay.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public UserShowDto addUser(UserSaveDto userSaveDto) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if (userRepository.findByUsername(userSaveDto.getUsername()).isPresent()) {
            throw new RuntimeException("User with name '%s' already exist".formatted(userSaveDto.getUsername()));
        }
        UserData userData = UserData.builder()
                .username(userSaveDto.getUsername())
                .password(passwordEncoder.encode(userSaveDto.getPassword()))
                .role("ROLE_USER")
                .enabled(true)
                .build();
        UserData savedUser = userRepository.save(userData);
        return toUserShowDto(savedUser);
    }

    public List<UserShowDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserShowDto)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::toUserDetails)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with name '%s' not found".formatted(username)));
    }

    public UserData getUserDataByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with name '%s' not found".formatted(username)));
    }
    private UserDetails toUserDetails(UserData user) {
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(collectAuthorities(user.getRole()))
                .disabled(!user.isEnabled())
                .build();
    }

    private List<GrantedAuthority> collectAuthorities(String role) {
        return roleRepository.getRole(role)
                .map(Role::getPrivileges)
                .stream().flatMap(Collection::stream)
                .map(priv -> (GrantedAuthority) new SimpleGrantedAuthority("PRIV_" + priv))
                .toList();
    }

    private UserShowDto toUserShowDto(UserData userData) {
        return UserShowDto.builder()
                .id(userData.getId())
                .username(userData.getUsername())
                .build();
    }
}
