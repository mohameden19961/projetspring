package mr.supnum.library_app.repository;

import mr.supnum.library_app.entity.Borrow;
import mr.supnum.library_app.entity.Member;
import mr.supnum.library_app.entity.enums.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    
    long countByMemberAndStatus(Member member, BorrowStatus status);
    
    List<Borrow> findByMemberId(Long memberId);
}
