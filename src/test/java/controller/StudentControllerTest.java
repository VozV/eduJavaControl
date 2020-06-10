package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.edu.controllers.StudentController;
import ru.edu.entity.Student;
import service.mock.MockStudentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentController.class, MockStudentService.class})
public class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    private MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/student";

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(studentController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        Student student = new Student("Vasa", "123");
        String requestJson = objectMapper.writeValueAsString(student);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get(URL + "/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        Student student = new Student("Vasa", "123");
        String requestJson = objectMapper.writeValueAsString(student);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(URL + "/2"))
                .andExpect(status().isNoContent());
        mockMvc.perform(delete(URL))
                .andExpect(status().isMethodNotAllowed());
    }
}
