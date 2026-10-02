package tw.brad.spring3.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tw.brad.spring3.dto.MemberForm;
import tw.brad.spring3.entity.Member;
import tw.brad.spring3.repo.MemberRepo;

@Service
public class MemberService {
    @Autowired
    private MemberRepo repo;

    public Member register(MemberForm memberForm) throws Exception{
        String account = memberForm.getAccount();
        if (repo.findByAccount(account) != null) throw new Exception();

        Member member = new Member();
        member.setAccount(account);
        member.setPasswd(BCrypt.hashpw(memberForm.getPasswd(), BCrypt.gensalt()));
        member.setName(memberForm.getName());

        MultipartFile iconFile = memberForm.getIconFile();
        byte[] icon = iconFile != null && !iconFile.isEmpty() ? iconFile.getBytes() : null;
        member.setIcon(icon);

        return repo.save(member);
    }
}
