package ru.hogwarts.school.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;

import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerTest {

    private static final Faculty MOCK_FACULTY = new Faculty("Факультет1","белый");
    static final String MOCK_FACULTY_NAME = "Факультет1";
    static final String MOCK_FACULTY_COLOR = "белый";
    static final long  MOCK_FACULTY_ID = 2L;
    static final String MOCK_FACULTY_NEW_NAME = "черный";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean//    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    private ObjectMapper mapper = new ObjectMapper();



    @Test
    public void createFaculty() throws Exception {

        Faculty facultyForCreate = new Faculty(MOCK_FACULTY_NAME, MOCK_FACULTY_COLOR);

        String request = mapper.writeValueAsString(facultyForCreate);

        //Подготовка ожидаемого результата
        Faculty facultyAfterCreate = new Faculty(MOCK_FACULTY_NAME, MOCK_FACULTY_COLOR);
        facultyAfterCreate.setId(MOCK_FACULTY_ID);

        when(facultyService.add(MOCK_FACULTY)).thenReturn(facultyAfterCreate);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty") //send
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(MOCK_FACULTY_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(facultyForCreate.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(facultyForCreate.getColor()))
                .andReturn();
    }

    @Test
    public void getFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(MOCK_FACULTY_ID))
//                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
//                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR))
                        .andReturn();
    }

    @Test
    public void deleteFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void updateFaculty() throws Exception {

        Faculty facultyForUpdate = new Faculty(MOCK_FACULTY_NAME, MOCK_FACULTY_COLOR);
        facultyForUpdate.setId(MOCK_FACULTY_ID);

        String request = mapper.writeValueAsString(facultyForUpdate);

        //Подготовка ожидаемого результата
        when(facultyService.update(MOCK_FACULTY)).thenReturn(facultyForUpdate);

        MOCK_FACULTY.setName(MOCK_FACULTY_NEW_NAME);

        when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(MOCK_FACULTY);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
/*
@Test
public void FacultyCreateTest () throws Exception {
    final String name = "test1";
    final String color = "test1";
    final Long id = 1L;


    JSONObject objectFaculty = new JSONObject();
    objectFaculty.put("name", name);
    objectFaculty.put("color", color);

    Faculty faculty = new Faculty();
    faculty.setId(id);
    faculty.setName(name);
    faculty.setColor(color);

    when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
    when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

    mockMvc.perform(MockMvcRequestBuilders
                    .post("/faculty")
                    .content(objectFaculty.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value(name))
            .andExpect(jsonPath("$.color").value(color));
}

@Test
    public void getFacultyByNameOrByColor() throws Exception {
        when(facultyRepository.findByColorContainsIgnoreCaseOrNameContainsIgnoreCase (anyString(), anyString())).thenReturn((List<Faculty>) MOCK_FACULTY);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filter?name=" + MOCK_FACULTY_NAME + "&color=" + MOCK_FACULTY_COLOR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(MOCK_FACULTY)));
    }

    @Test
    public void getStudentsByFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/students/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
*/
}