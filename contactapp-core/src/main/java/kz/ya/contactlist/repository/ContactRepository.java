package kz.ya.contactlist.repository;

import java.util.List;
import kz.ya.contactlist.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yerlana
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    
    List<Contact> findAllByName(String name);
}
