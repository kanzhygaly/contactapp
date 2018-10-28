package kz.ya.contactlist.service;

import java.util.List;
import java.util.stream.Collectors;
import kz.ya.contactlist.converter.ContactConverter;
import kz.ya.contactlist.dto.ContactDTO;
import kz.ya.contactlist.entity.Contact;
import kz.ya.contactlist.exception.ContactNotFoundException;
import kz.ya.contactlist.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author yerlana
 */
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDTO> findAll() {
        return contactRepository.findAll().stream().map(ContactConverter::entityToDto).collect(Collectors.toList());
    }
    
    @Override
    public List<ContactDTO> findAllByName(String name) {
        return contactRepository.findAllByName(name).stream().map(ContactConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ContactDTO findById(Long id) {
        return ContactConverter.entityToDto(contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id)));
    }

    @Override
    public Contact saveOrUpdate(ContactDTO dto) {
        Contact contact = contactRepository.save(ContactConverter.dtoToEntity(dto));
        return contact;
    }

    @Override
    public void delete(Long id) {
        try {
            contactRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ContactNotFoundException(id);
        }
    }
}
