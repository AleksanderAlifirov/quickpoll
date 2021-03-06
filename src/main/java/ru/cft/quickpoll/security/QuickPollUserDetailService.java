package ru.cft.quickpoll.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.cft.quickpoll.model.User;
import ru.cft.quickpoll.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuickPollUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with the user name %s doesn't exist", userName));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.isAdmin()) {
            authorities = AuthorityUtils.createAuthorityList(("ROLE_ADMIN"));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), authorities);

        return userDetails;
    }
}
