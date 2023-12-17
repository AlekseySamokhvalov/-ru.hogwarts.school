package ru.hogwarts.school.TestRestTemplate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetFacultyById() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty" + "/by-id", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(2L);
        faculty.setName("Факультет1");
        faculty.setColor("белый");

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByColor() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty" + "/by-color", String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByNameOrColor() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty" + "/byNameOrColor", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByFacultyId() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty" + "/students-by-faculty-id", String.class))
                .isNotNull();
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(3L);
        faculty.setName("Факультет2");
        faculty.setColor("черный");

        ResponseEntity<Void> resp = testRestTemplate.exchange("http://localhost:" + port + "/faculty", HttpMethod.DELETE, null, Void.class);
    }

    @Test
    public void testPutStudent() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(4L);
        faculty.setName("Факультет3");
        faculty.setColor("Красный");

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }
}
