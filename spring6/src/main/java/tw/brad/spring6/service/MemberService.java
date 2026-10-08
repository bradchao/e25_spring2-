package tw.brad.spring6.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tw.brad.spring6.entity.Member;
import tw.brad.spring6.repo.MemberRepo;

@Service
public class MemberService  implements UserDetailsService {

    @Autowired
    private MemberRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = repo.findByAccount(username).orElse(null);
        if (member == null) throw new UsernameNotFoundException("Account NOT FOUND");

        return User.builder()
                .username(member.getAccount())
                .password(member.getPasswd())
                .roles(member.getRole())
                .build();
    }
}
