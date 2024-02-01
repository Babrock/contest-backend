package com.example.contestbackend.service;

import com.example.contestbackend.model.Form;
import com.example.contestbackend.model.User;
import com.example.contestbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;


@Service
@RequiredArgsConstructor
public class CsvExportService {
    private static final Logger log = getLogger(CsvExportService.class);

    private final UserService userService;

    private final TitleService titleService;

    private final RoleService roleService;

    private final FormService formService;

    public void writeUsersToCsv(Writer writer){
        List<User> users = userService.getUsers();
        try(CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord("id", "firstname", "lastname", "email", "phone", "wantsToRate", "enabled", "title", "role");
            for(User user : users){
                csvPrinter.printRecord(
                        user.getId(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getWantsToRate(),
                        user.getEnabled(),
                        titleService.getTitle(user.getTitle().getId()).getName(),
                        roleService.getRole(user.getRole().getId()).getName()
                );
            }
        }catch(IOException e){
            log.error("Error while writing CSV", e);
        }
    }

    public void writeFormsToCsv(Writer writer){
        List<Form> forms = formService.getForms();
        try(CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord("id", "uploadDate", "acceptedDate", "isAccepted", "user", "School", "SchoolDetails");
            for(Form form : forms){
                csvPrinter.printRecord(
                        form.getId(),
                        form.getUploadDate(),
                        form.getAcceptedDate(),
                        form.getIsAccepted(),
                        form.getUser(),
                        form.getSchool(),
                        form.getSchoolDetails()
                );
            }
        }catch(IOException e){
            log.error("Error while writing CSV", e);
        }
    }
}
