package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	FacultyRepository facultyRepository;
	@Autowired
	private StudentController studentController;
	@Autowired
	private FacultyController facultyController;
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void contextLoads() {
		Assertions.assertThat(studentController).isNotNull();
		Assertions.assertThat(facultyController).isNotNull();
	}
	@Test
	void findStudent() {
		Assertions.
				assertThat(this.testRestTemplate.getForObject(
						"http://localhost:" + port + "/student/1", String.class)).isNotNull();
	}
	@Test
	void findFaculty() {
		Assertions.
				assertThat(this.testRestTemplate.getForObject(
						"http://localhost:" + port + "/faculty/1", String.class)).isNotNull();
	}

	@Test
	void testPostStudent(){
		Student student = new Student("Гарри Поттер",21);
		Assertions.
				assertThat(this.testRestTemplate.postForObject(
						"http://localhost:" + port + "/student", student, String.class)).isNotNull();
	}
	@Test
	void testPostFaculty(){
		Faculty faculty = new Faculty("Факультет1","Белый");
		Assertions.
				assertThat(this.testRestTemplate.postForObject(
						"http://localhost:" + port + "/faculty", faculty, String.class)).isNotNull();
	}

	@Test
	void findByColor() {
		Assertions.
				assertThat(this.testRestTemplate.getForObject(
						"http://localhost:" + port + "/faculty/?color=red", String.class)).isNotNull();

	}
	@Test
	void getFacultyOfStudent() {
		Assertions.
				assertThat(this.testRestTemplate.getForObject(
						"http://localhost:" + port + "/faculty/?studentId=1", String.class)).isNotNull();

	}
	@Test
	void findByAge() {
		Assertions.
				assertThat(this.testRestTemplate.getForObject(
						"http://localhost:" + port + "/students/?age=29", String.class)).isNotNull();

	}
	@Test
	void findByAgeBetween() {
		Assertions.
				assertThat(this.testRestTemplate.getForObject(
						"http://localhost:" + port + "/students/?minAge=25&&maxAge=30", String.class)).isNotNull();

	}

	@Test
	void findStudentsByFaculty() {
		Assertions.
				assertThat(this.testRestTemplate.getForObject(
						"http://localhost:" + port + "/students/?id=1", String.class)).isNotNull();

	}
}
