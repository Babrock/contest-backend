package com.example.contestbackend.service;

import com.example.contestbackend.dto.FormRequest;
import com.example.contestbackend.dto.FormResponse;
import com.example.contestbackend.dto.projections.FormVerifyView;
import com.example.contestbackend.dto.SchoolClassDto;
import com.example.contestbackend.dto.projections.SchoolClassTableView;
import com.example.contestbackend.model.Form;
import com.example.contestbackend.model.School;
import com.example.contestbackend.model.SchoolDetails;
import com.example.contestbackend.model.User;
import com.example.contestbackend.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;

    private final SchoolService schoolService;

    private final UserService userService;

    private final SchoolClassService schoolClassService;

    private final SchoolDetailsService schoolDetailsService;

    public List<Form> getForms() {
        return formRepository.findAll();
    }

    public Form getFormById(int id) {
        return formRepository.findById(id).orElseThrow();
    }

//    public List<SchoolClassTableView> getAcceptedForms(){ return  formRepository.findSchoolClass(1);}

    public FormVerifyView getFormByCombinedInfo(String combinedInfo){
        String[] parts = combinedInfo.split("-");
        Integer id = Integer.parseInt(parts[0]);
        return formRepository.findById(id, FormVerifyView.class)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Form with given id not found"));
    }

    private Instant parseUploadDate(String uploadDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate localDate = LocalDate.parse(uploadDate, formatter);
        return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
    }
    @Transactional
    public FormResponse add(FormRequest formRequest, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        School school = schoolService.getSchool(formRequest.schoolData().getSchool());
        SchoolDetails schoolDetails = schoolDetailsService.addSchoolDetails(formRequest.schoolDetailsInfo());
        Form form = addForm(user, school, schoolDetails);
        for (SchoolClassDto schoolClassDto: formRequest.schoolClasses() ) {
            schoolClassService.addSchoolClass(schoolClassDto, form , school);
        }
        return new FormResponse(form.getId(), form.getUploadDate(), form.getSchoolDetails().getRegion());
    }

    public void setFormAccepted(int id){
        Form form = getFormById(id);
        if(form.getIsAccepted() != true){
            form.setIsAccepted(true);
            formRepository.save(form);
        }else{
            throw new ResponseStatusException(HttpStatus.ACCEPTED, "Form is accepted");
        }
    }

    public void setFormNotAccepted(int id){
        Form form = getFormById(id);
        if(form.getIsAccepted() == true){
            form.setIsAccepted(false);
            formRepository.save(form);
        }else{
            throw new ResponseStatusException(HttpStatus.ACCEPTED, "Form is not accepted");
        }
    }
    public Form addForm(User user, School school, SchoolDetails schoolDetails) {
        Form form = new Form(user, school, false, schoolDetails);
        return formRepository.save(form);
    }
    public FormVerifyView getFormVerifyView(int id) {
        return formRepository.findById(id, FormVerifyView.class)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Form with given id not found"));
    }

    @Transactional
    public void deleteForm(Integer id){
        Form form = getFormById(id);
        if (form != null) {
            schoolClassService.deleteSchoolClassesByFormId(id);
            formRepository.deleteById(id);
        } else {
            // Obsługa, gdy formularz o danym ID nie istnieje
            // Możesz rzucać wyjątki lub podjąć inne działania
        }
    }
}
