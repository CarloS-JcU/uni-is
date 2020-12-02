package org.is.keycloak.uniis.model;

public class Subject {

    private String id;
    private String title;
    private String professor;

    public Subject(String id, String title, String professor) {
        this.id = id;
        this.title = title;
        this.professor = professor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
