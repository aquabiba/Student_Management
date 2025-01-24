package Learning.student_management.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository sT;
    @Autowired
    public StudentService(StudentRepository sT) {
        this.sT = sT;
    }

    public List<Student> getStudents() {
        return sT.findAll();

    }


    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail =  sT.findByStudentEmail(student
                .getEmail());
        if (studentByEmail.isPresent()) {
           throw new IllegalStateException("Student with the same email " +
                   "already exists");
        }
        else {
            sT.save(student);
        }
    }
    public void deleteStudent(Long studentId){
       boolean exists =  sT.existsById(studentId);
       if (!exists) {
           throw new IllegalStateException("Student with the id : "+ studentId +" does not exist");
       }
       sT.deleteById(studentId);

//        Optional<Student> studentToDelete = sT
//                .getStudentById(studentId);
//        if (studentToDelete.isPresent()) {
//            sT.deleteById(studentId);
//        }
    }
    @Transactional // the entity goes into a managed state
    public void updateStudent(Long id, String name, String email) {
        Student student = sT.findById(id).orElseThrow(
                ()-> new IllegalStateException("Student with the id : "
                        + id + " does not exist")
                        );
        if(name!=null &&
                !name.isEmpty() &&
            !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if(email!=null &&
                !email.isEmpty() &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentByEmail =  sT.findByStudentEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("email takken ");
            }
            student.setEmail(email);
        }
    }
}
