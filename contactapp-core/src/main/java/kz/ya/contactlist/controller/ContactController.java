package kz.ya.contactlist.controller;

import java.net.URI;
import java.util.List;
import kz.ya.contactlist.dto.ContactDTO;
import kz.ya.contactlist.entity.Contact;
import kz.ya.contactlist.exception.ContactNotFoundException;
import kz.ya.contactlist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author yerlana
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;
    
    @GetMapping
    public List<ContactDTO> findAll() {
        return contactService.findAll();
    }
    
    @GetMapping("/{name}")
    public List<ContactDTO> search(@PathVariable String name) {
        List<ContactDTO> list = contactService.findAllByName(name);
        if (list.isEmpty()) {
            throw new ContactNotFoundException(name);
        }
        return list;
    }

    @GetMapping("/{id}")
    public ContactDTO findOne(@PathVariable long id) {
        ContactDTO dto = contactService.findById(id);
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        contactService.delete(id);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ContactDTO dto) {
        Contact savedContact = contactService.saveOrUpdate(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedContact.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody ContactDTO dto, @PathVariable long id) {
        contactService.findById(id);

        dto.setId(id);
        contactService.saveOrUpdate(dto);

        return ResponseEntity.noContent().build();
    }
}
