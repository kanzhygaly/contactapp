package kz.ya.contactlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author yerlana
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(Long id) {
        super("Contact with ID [" + id + "] was not found!");
    }
    
    public ContactNotFoundException(String name) {
        super("Contact with Name [" + name + "] was not found!");
    }
}
