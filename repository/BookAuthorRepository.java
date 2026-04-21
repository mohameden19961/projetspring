package mr.supnum.library_app.repository;

import mr.supnum.library_app.entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthor.BookAuthorId> {
    List<BookAuthor> findByBookId(Long bookId);
}
