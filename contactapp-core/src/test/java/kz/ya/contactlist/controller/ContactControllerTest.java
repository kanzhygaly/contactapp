package kz.ya.contactlist.controller;

import kz.ya.contactlist.ContactListApplication;
import kz.ya.contactlist.dto.ContactDTO;
import kz.ya.contactlist.entity.Contact;
import kz.ya.contactlist.repository.ContactRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author yerlana
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactListApplication.class)
@WebAppConfiguration
public class ContactControllerTest {

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ContactRepository contactRepository;

    private Contact contact;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny().orElse(null);

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.contact = contactRepository.save(new Contact("Cyrail", "https://pin.it/yurvpxljksekht"));
    }

    @After
    public void tearDown() throws IOException {
        contactRepository.delete(this.contact);
    }

    @Test
    public void testFindContactByID() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/contacts/" + this.contact.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is(this.contact.getName())));
    }

    @Test
    public void testContactNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/contacts/321"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteContact() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/contacts/" + this.contact.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteContactFailed() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/contacts/321"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateContact() throws Exception {
        String requestJson = convertToJson(new ContactDTO("Raqmo", "https://pin.it/tv5m6v265i52xy"));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/contacts")
                .contentType(this.contentType).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCreateContactFailed() throws Exception {
        String requestJson = convertToJson(new ContactDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/contacts")
                .contentType(this.contentType).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testUpdateContact() throws Exception {
    }

    @Test
    public void testUpdateContactFailed() throws Exception {
    }

    @Test
    public void testFindContactsByName() throws Exception {
    }

    protected String convertToJson(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
