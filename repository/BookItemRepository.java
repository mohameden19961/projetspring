package mr.supnum.library_app.repository;

import mr.supnum.library_app.entity.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Long> {
    Optional<BookItem> findByBarcode(String barcode);
}
