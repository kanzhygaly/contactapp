package kz.ya.contactlist.dto;

/**
 *
 * @author yerlana
 */
public class ContactDTO {
    
    private Long id;
    private String name;
    private String photo;

    public ContactDTO() {
    }

    public ContactDTO(Long id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
