package at.htlgkr.prototype;

import static at.htlgkr.prototype.ContainsIgnoringWhitespace.containsIgnoringWhitespace;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrototypeApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testWithoutLogin() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isFound());
    }

    @WithMockUser
    @Test
    public void testWithLogin() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void addedPatientShowsUpInTable() throws Exception {
        Patient patient = new Patient((long)1, "TEST", "User");
        patientRepository.save(patient);

        mvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsIgnoringWhitespace("<tbody><tr><td><a href=\"/show?searchId=1\">1</a></td><td><span>TEST</span></td><td><span>User</span></td></tr></tbody>")));
    }

    @WithMockUser
    @Test
    public void postAddsToDatabase() throws Exception {
        Patient patient = new Patient();
        patient.setFirstname("Test");
        patient.setLastname("User");
        Patient patient2 = new Patient();
        patient2.setFirstname("Test2");
        patient2.setLastname("User2");

        mvc.perform(post("/")
                .flashAttr("patient", patient))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsIgnoringWhitespace("<p>Added[1]TestUser</p>")));
        mvc.perform(post("/")
                .flashAttr("patient", patient2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsIgnoringWhitespace("<p>Added[2]Test2User2</p>")));

        Optional<Patient> p1 = patientRepository.findById((long)1);
        assertTrue(p1.isPresent());
        assertEquals((long)p1.get().getId(), 1);
        assertEquals(p1.get().getFirstname(), patient.getFirstname());
        assertEquals(p1.get().getLastname(), patient.getLastname());

        p1 = patientRepository.findById((long)2);
        assertTrue(p1.isPresent());
        assertEquals((long)p1.get().getId(), 2);
        assertEquals(p1.get().getFirstname(), patient2.getFirstname());
        assertEquals(p1.get().getLastname(), patient2.getLastname());
    }
}
