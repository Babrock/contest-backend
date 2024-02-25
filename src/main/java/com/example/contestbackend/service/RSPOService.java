package com.example.contestbackend.service;

import com.example.contestbackend.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RSPOService {
    private final SchoolService schoolService;
    private final UserService userService;
    private final TitleService titleService;
    private final RoleService roleService;
    private final VoivodeshipService voivodeshipService;
    private final CountyService countyService;
    private final CommunityService communityService;
    private final PasswordEncoder passwordEncoder;
    private final UtilsDataService utilsDataService;

    @PostConstruct
    public void updateData() {
        if (!utilsDataService.isSchoolsDownloaded()) {
            createAdmins();
            List<CSVRecord> records = fetchRecordsFromRSPO();
            saveSchools(records);
            saveVoivodeships(records);
            saveCounties(records);
            saveCommunities(records);
            utilsDataService.setIsSchoolsDownloaded(true);
        }
    }

    private void createAdmins() {
        User existingAdmin1 = userService.getUserByEmail("damian.rocha00@gmail.com");
        User existingAdmin2 = userService.getUserByEmail("admin@poczta.pl");
        if (existingAdmin1 == null && existingAdmin2 == null) {
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
        } else {
            System.out.println("Admin1 already exist.");
        }
        if (existingAdmin2 == null) {
            User admin2 = new User("Admin", "Admin", "admin@poczta.pl", "Admin", "123456789", false, true, titleService.getTitle(2), roleService.getRole(1));
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
        } else {
            System.out.println("Admin2 already exist.");
        }
    }

    private List<CSVRecord> fetchRecordsFromRSPO() {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .setHeader()
                .setDelimiter(';')
                .setQuote(null)
                .build();
        try {
            return csvFormat.parse(fetchRSPOData2()).getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveSchools(List<CSVRecord> records) {
        records.forEach(record -> {
            Optional<School> school = createSchool(record.values());
            school.ifPresent(schoolService::saveSchool);
        });
    }

    private void saveVoivodeships(List<CSVRecord> records) {
        Set<String> voivodeships = new HashSet<>();
        for (CSVRecord record : records) {
            voivodeships.add(record.get("Województwo").replaceAll("\"|\"", ""));
        }
        voivodeships.stream()
                .filter(voivodeship -> !voivodeship.matches("\\d+"))
                .map(Voivodeship::new)
                .forEach(voivodeshipService::saveVoivodeship);
    }

    private void saveCounties(List<CSVRecord> records) {
        Map<String, Integer> voivodeshipIdMap = new HashMap<>();
        List<Voivodeship> voivodeships = voivodeshipService.getVoivodeships();
        for (Voivodeship voivodeship : voivodeships) {
            voivodeshipIdMap.put(voivodeship.getName(), voivodeship.getId());
        }
        Set<String> counties = new HashSet<>();
        for (CSVRecord record : records) {
            String countyName = record.get("Powiat").replaceAll("\"|\"", "");
            String voivodeshipName = record.get("Województwo").replaceAll("\"|\"", "");
            counties.add(countyName);
            Integer voivodeshipId = voivodeshipIdMap.get(voivodeshipName);

            try{
                Voivodeship voivodeship = voivodeshipService.getVoivodeshipById(voivodeshipId);
                County county = new County();
                county.setName(countyName);
                county.setVoivodeship(voivodeship);
                countyService.saveCounty(county);
            } catch (NullPointerException e){
                // Handle the case where voivodeshipId is null (log a warning, throw an exception, or handle it accordingly)
                // Example: logger.warn("Voivodeship ID not found for voivodeshipName: {}", voivodeshipName);
            }
        }
    }

    private void saveCommunities(List<CSVRecord> records) {
        Map<String, Integer> countyIdMap = new HashMap<>();
        List<County> counties = countyService.getCounties();
        for (County county : counties) {
            countyIdMap.put(county.getName(), county.getId());
        }
        Set<String> communities = new HashSet<>();
        for (CSVRecord record : records) {
            String communityName = record.get("Gmina").replaceAll("\"|\"", "");
            String countyName = record.get("Powiat").replaceAll("\"|\"", "");
            communities.add(communityName);
            Integer countyId = countyIdMap.get(countyName);
            try {
                County county = countyService.getCountyById(countyId);
                Community community = new Community();
                community.setName(communityName);
                community.setCounty(county);
                communityService.saveCommunity(community);
            } catch (NullPointerException e){
                // Handle the case where countyId is null (log a warning, throw an exception, or handle it accordingly)
            }
        }
    }

    private Optional<School> createSchool(String[] cols) {
        try {
            School school = new School();
            school.setName(cols[4].replaceAll("\"=\"|\"", ""));
            school.setVoivodeship(cols[10].replaceAll("\"=\"|\"", ""));
            school.setCounty(cols[11].replaceAll("\"=\"|\"", ""));
            school.setCommunity(cols[12].replaceAll("\"=\"|\"", ""));
            school.setCity(cols[13].replaceAll("\"=\"|\"", ""));
            school.setStreet(cols[15].replaceAll("\"=\"|\"", ""));
            school.setAddress(cols[16].replaceAll("\"=\"|\"", ""));
            school.setApartmentNumber(cols[17].replaceAll("\"=\"|\"", ""));
            school.setZipCode(cols[18].replaceAll("\"=\"|\"", ""));
            school.setPost(cols[19].replaceAll("\"=\"|\"", ""));
            school.setPhone(cols[20].replaceAll("\"=\"|\"", ""));
            school.setEmail(cols[22].replaceAll("\"=\"|\"", ""));
            school.setHeadmasterFullName(cols[27].replaceAll("\"=\"|\"", ""));
            return Optional.of(school);
        } catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
            return Optional.empty();
        }
    }

    private Reader fetchRSPOData2() {
        long start = System.currentTimeMillis();
        String url = "https://rspo.gov.pl/api/Institution/Csv";
        String jsonBody = "{}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        var response = restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return new StringReader(new String(response.getBody(), StandardCharsets.UTF_8));
    }

}
