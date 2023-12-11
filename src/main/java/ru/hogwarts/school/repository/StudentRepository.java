package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByAge(Integer age);
    List<Student> findByAgeBetween(Integer min, Integer max);

    List<Student> findByFacultyId(Long id);
    @Query(value = "SELECT COUNT(*)" + "FROM student", nativeQuery = true)
    Integer getCount();

    @Query(value = "SELECT AVG(s.age)" + "FROM student s", nativeQuery = true)
    Double getAvgAge();

    @Query(value = "SELECT * " + "FROM student " + "ORDER BY id DESC " + "LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudent();
}
