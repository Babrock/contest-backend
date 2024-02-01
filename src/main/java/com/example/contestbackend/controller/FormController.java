package com.example.contestbackend.controller;

import com.example.contestbackend.dto.FormRequest;
import com.example.contestbackend.dto.FormResponse;
import com.example.contestbackend.dto.projections.AcceptedFormView;
import com.example.contestbackend.dto.projections.FormVerifyView;
import com.example.contestbackend.dto.projections.SchoolClassTableView;
import com.example.contestbackend.model.Form;
import com.example.contestbackend.service.CsvExportService;
import com.example.contestbackend.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/form")
public class FormController {
    private final FormService formService;
    private final CsvExportService csvExportService;

    @GetMapping("/{id}")
    public FormVerifyView getFormById(@PathVariable int id){
        return formService.getFormVerifyView(id);
    }

    @GetMapping("/combinedInfo/{combinedInfo}")
    public FormVerifyView getFormById(@PathVariable String combinedInfo){
        return formService.getFormByCombinedInfo(combinedInfo);
    }

//    @GetMapping("/accepted")
//    public List<SchoolClassTableView> getAllAcceptedForms() {
//        return formService.getAcceptedForms();
//    }

    @PostMapping("/accepted/{id}")
    public void acceptedForm(@PathVariable Integer id){
        formService.setFormAccepted(id);
    }

    @PostMapping("/not-accepted/{id}")
    public void notAcceptedForm(@PathVariable Integer id){
        formService.setFormNotAccepted(id);
    }

    @PostMapping
    public FormResponse createForm(@RequestBody FormRequest formRequest, Principal principal) {
        FormResponse formResponse = formService.add(formRequest, principal);
        return new FormResponse(formResponse.getId(), formResponse.getUploadDate(), formResponse.getRegionId());
    }

    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable Integer id){
        formService.deleteForm(id);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @RequestMapping (path = "/export")
    public void getAllFormsInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"forms.csv\"");
        csvExportService.writeFormsToCsv(servletResponse.getWriter());
    }
}
