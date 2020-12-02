package org.is.keycloak.uniis.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.is.keycloak.uniis.model.Subject;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepository {

    private static Map<String, Subject> subjects = new ConcurrentHashMap<>();

    static {
        subjects.put("UAI7000", new Subject("UAI7000", "ID projekt", "Karel Novák"));
        subjects.put("UAI8000", new Subject("UAI8000", "Bezpečnost", "Jaromír Bestie"));
    }

    public List<Subject> readAll() {
        List<Subject> allSubjects = new ArrayList<>(subjects.values());
        allSubjects.sort(Comparator.comparing(Subject::getId));
        return allSubjects;
    }

    public void create(Subject subject) {
        subjects.put(subject.getId(), subject);
    }

    public Subject read(String id) {
        return subjects.get(id);
    }

    public void update(Subject subject) {
        subjects.put(subject.getId(), subject);
    }

    public void delete(String id) {
        subjects.remove(id);
    }
}
