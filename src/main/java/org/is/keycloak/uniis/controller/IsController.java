package org.is.keycloak.uniis.controller;

import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.is.keycloak.uniis.model.Subject;
import org.is.keycloak.uniis.repository.SubjectRepository;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class IsController {

    private final HttpServletRequest request;
    private final SubjectRepository subjectRepository;

    @Autowired
    public IsController(HttpServletRequest request, SubjectRepository subjectRepository) {
        this.request = request;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping(value = "/")
    public String getHome() {
        return "index";
    }

    @GetMapping(value = "/student")
    public String getSubject(Model model) {
        configCommonAttributes(model);
        configCustomAttributes(model);
        model.addAttribute("subjects", subjectRepository.readAll());
        return "student";
    }

    @GetMapping(value = "/admin/login")
    public String getManager(Model model) {
        configCommonAttributes(model);
        model.addAttribute("subjects", subjectRepository.readAll());
        return "admin";
    }

    @GetMapping(value = "/logout")
    public String logout() throws ServletException {
        request.logout();
        return "redirect:/";
    }

    @PostMapping(value = "/admin/add")
    public ResponseEntity postController(@RequestBody Subject subject) {
        subjectRepository.create(subject);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/remove")
    public ResponseEntity removeController(@RequestBody Subject subject) {
        subjectRepository.delete(subject.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void configCommonAttributes(Model model) {
        model.addAttribute("firstName", getKeycloakSecurityContext().getIdToken().getGivenName());
        model.addAttribute("lastName", getKeycloakSecurityContext().getIdToken().getFamilyName());
        model.addAttribute("email", getKeycloakSecurityContext().getIdToken().getEmail());
        model.addAttribute("birthdate", getKeycloakSecurityContext().getIdToken().getBirthdate());
    }

    private void configCustomAttributes(Model model) {
        String addrezz = "";
        String age = "";
        String placeOfBirth = "";

        Map<String, Object> customClaims = getKeycloakSecurityContext().getIdToken().getOtherClaims();
        if (customClaims.containsKey("addrezz")) {
            addrezz = String.valueOf(customClaims.get("addrezz"));

        }
        if (customClaims.containsKey("age")) {
            age = String.valueOf(customClaims.get("age"));

        }
        if (customClaims.containsKey("placeOfBirth")) {
            placeOfBirth = String.valueOf(customClaims.get("placeOfBirth"));

        }
        model.addAttribute("addrezz", addrezz);
        model.addAttribute("age", age);
        model.addAttribute("placeOfBirth", placeOfBirth);

    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
