package kz.ya.contactlist.converter;

import kz.ya.contactlist.dto.ContactDTO;
import kz.ya.contactlist.entity.Contact;

/**
 *
 * @author yerlana
 */
public class ContactConverter {

    public static Contact dtoToEntity(ContactDTO dto) {
        Contact entity = new Contact(dto.getName(), dto.getPhoto());
        entity.setId(dto.getId());
        return entity;
    }

    public static ContactDTO entityToDto(Contact entity) {
        return new ContactDTO(entity.getId(), entity.getName(), entity.getPhoto());
    }
}
