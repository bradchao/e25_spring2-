package tw.brad.spring2.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.brad.spring2.entity.Info;
import tw.brad.spring2.entity.Member;
import tw.brad.spring2.repository.InfoRepo;
import tw.brad.spring2.repository.MemberRepo;

@Service
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private InfoRepo infoRepo;

    @Transactional
    public Member save(Member member, Info info){
        member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        member.setInfo(info);
        return  memberRepo.save(member);
    }

    @Transactional
    public Info saveInfoToMember(Long memberId, Info info){
        Member member = memberRepo.findById(memberId).orElse(null);
        if (member != null){
            Info i = member.getInfo();
            if (i != null){
                info.setId(memberId);
            }
            member.setInfo(info);
            Member saveMember = memberRepo.save(member);
            return saveMember.getInfo();
        }
        return null;
    }


    public Member findMemberById(Long memberId){
        return memberRepo.findById(memberId).orElse(new Member());
    }

}
