package com.example.contestbackend.dto.projections;

import com.example.contestbackend.model.Form;
import com.example.contestbackend.model.Title;
import com.example.contestbackend.model.Voivodeship;
import org.springframework.beans.factory.annotation.Value;

import javax.naming.Name;

public interface SchoolClassTableView {
    Integer getId();
    String getName();
    NameView getLanguage();
    FormVerifyView getForm();
//
//    String getName();
////    @Value("#{target.title.name + ' ' + target.firstname + ' ' + target.lastname}")
////    String getTeacher();
//

}
