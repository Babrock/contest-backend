package com.example.contestbackend.service;

import com.example.contestbackend.model.School;
import com.example.contestbackend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RSPOService {
    private final SchoolService schoolService;
    private final UserService userService;
    private final TitleService titleService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
//    @PostConstruct
//    public void updateData() {
//        createAdmins();
//        String rspoData = fetchRSPOData();
//        convertStringToCsv(rspoData);
//    }

    private void createAdmins(){
        User existingAdmin1 = userService.getUserByEmail("damian.rocha00@gmail.com");
        User existingAdmin2 = userService.getUserByEmail("admin@poczta.pl");
        if ( existingAdmin1 == null && existingAdmin2 == null) {
            User admin1 = new User();
            admin1.setFirstname("Admin");
            admin1.setLastname("Admin");
            admin1.setEmail("damian.rocha00@gmail.com");
            admin1.setPassword(passwordEncoder.encode("lol123"));
            admin1.setPhone("123456789");
            admin1.setWantsToRate(false);
            admin1.setEnabled(true);
            admin1.setTitle(titleService.getTitle(2));
            admin1.setRole(roleService.getRole(1));
            userService.saveUser(admin1);
        }else{
            System.out.println("Admin1 already exist.");
        }
        if (existingAdmin2 == null) {
            User admin2 = new User("Admin", "Admin", "admin@poczta.pl", "Admin", "123456789", false, true,  titleService.getTitle(2), roleService.getRole(1));
            admin2.setFirstname("Admin");
            admin2.setLastname("Admin");
            admin2.setEmail("admin@poczta.pl");
            admin2.setPassword(passwordEncoder.encode("admin"));
            admin2.setPhone("000000000");
            admin2.setWantsToRate(false);
            admin2.setEnabled(true);
            admin2.setTitle(titleService.getTitle(2));
            admin2.setRole(roleService.getRole(1));
            userService.saveUser(admin2);
        }else{
            System.out.println("Admin2 already exist.");
        }
    }

    private void convertStringToCsv(String data) {
        String[] rows = data.split("\n");
        for (int i = 1; i < rows.length; i++) {
            String[] columns = rows[i].split(";");
            for (int j = 0; j < columns.length; j++) {
                columns[j] = columns[j].replaceAll("\"=\"|\"","");
            }
            if(columns[4].toLowerCase().contains("poradnia") || columns[4].toLowerCase().contains("ośrodek")){
                // Don't create schools with "poradnia" or "ośrodek" in the name
            }else{
                createAndSaveSchools(i, columns);
            }
        }
    }

    private void createAndSaveSchools(int rowIndex, String[] schools){
            try{
                School newSchool = createSchool(schools);
                schoolService.saveSchool(newSchool);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Error processing row: " + rowIndex);
            }
    }

    private School createSchool(String[] cols){
        School school = new School();
        school.setName(cols[4]);
        school.setVoivodeship(cols[10]);
        school.setCounty(cols[11]);
        school.setCommunity(cols[12]);
        school.setCity(cols[13]);
        school.setStreet(cols[15]);
        school.setAddress(cols[16]);
        school.setApartmentNumber(cols[17]);
        school.setZipCode(cols[18]);
        school.setPost(cols[19]);
        school.setPhone(cols[20]);
        school.setEmail(cols[22]);
        school.setHeadmasterFullName(cols[27]);
        return school;
    }

    private String fetchRSPOData() {
        String url = "https://rspo.gov.pl/api/Institution/Csv";
        String jsonBody = "{}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        var response = restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
        return new String(response.getBody(), StandardCharsets.UTF_8);
    }

}
