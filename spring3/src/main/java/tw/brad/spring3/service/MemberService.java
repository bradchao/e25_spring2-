package tw.brad.spring3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.brad.spring3.repo.MemberRepo;

@Service
public class MemberService {
    @Autowired
    private MemberRepo repo;
}
