package Learning.student_management.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findByStudentEmail(String email);

    @Query("SELECT s from Student s where s.id= ?1")
    Optional<Student> getStudentById(Long id);
}
