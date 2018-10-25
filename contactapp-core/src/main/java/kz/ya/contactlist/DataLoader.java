package kz.ya.contactlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import kz.ya.contactlist.entity.Contact;
import kz.ya.contactlist.repository.ContactRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * This class populates DB with the initial contact data from people.csv
 *
 * @author yerlana
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final Logger _LOG = LogManager.getLogger(DataLoader.class);
    private final ContactRepository contactRepository;

    @Autowired
    public DataLoader(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        _LOG.info("Start loading of initial initial contact data");
        try {
            File file = new ClassPathResource("people.csv").getFile();
            InputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            
            List<Contact> items = br.lines().skip(1).map((line) -> {
                String[] row = line.split(",");
                return new Contact(row[0], row[1]);
            }).collect(Collectors.toList());
            
            items.forEach(item -> contactRepository.save(item));

            _LOG.info("Loading data success!");
        } catch (IOException ex) {
            _LOG.error(ex);
        }
    }
}
