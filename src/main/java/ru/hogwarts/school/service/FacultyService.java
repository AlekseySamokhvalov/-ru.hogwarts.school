package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private static long ID_COUNTER = 1;

    private final Map<Long, Faculty> facultyById = new HashMap<>();

    public Faculty add(Faculty faculty) {
        Faculty newFaculty = new Faculty(ID_COUNTER, faculty.getName(), faculty.getColor());
        ID_COUNTER++;
        facultyById.put(newFaculty.getId(), newFaculty);
        return newFaculty;
    }

    public Faculty update(Faculty faculty) {
        Faculty facultyForUpdate = facultyById.get(faculty.getId());
        if (facultyForUpdate == null) {
            throw new FacultyNotFoundException();
        } else {
            facultyForUpdate.setColor(faculty.getColor());
            facultyForUpdate.setName(faculty.getName());
        }
        return facultyForUpdate;
    }

    public Faculty delete(Long id) {
        if (!facultyById.containsKey(id)) {
            throw new FacultyNotFoundException();
        } else {
            return facultyById.remove(id);
        }
    }

    public Faculty get(Long id) {
        if (!facultyById.containsKey(id)) {
            throw new FacultyNotFoundException();
        } else {
            return facultyById.get(id);
        }
    }

    public List<Faculty> getByColor(String color) {
        return facultyById.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Map<Long, Faculty> getAll() {
        return facultyById;
    }
}
