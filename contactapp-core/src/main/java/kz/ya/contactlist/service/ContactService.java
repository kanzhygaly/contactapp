package kz.ya.contactlist.service;

import java.util.List;
import kz.ya.contactlist.dto.ContactDTO;
import kz.ya.contactlist.entity.Contact;

/**
 *
 * @author yerlana
 */
public interface ContactService {
    
    List<ContactDTO> findAll();
    
    List<ContactDTO> findAllByName(String name);

    ContactDTO findById(Long id);

    Contact saveOrUpdate(ContactDTO dto);

    void delete(Long id);
}
