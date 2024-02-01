package com.example.contestbackend.dto.projections;

import com.example.contestbackend.model.SchoolClass;
import com.example.contestbackend.model.Task;
import com.example.contestbackend.model.Voivodeship;

import java.math.BigDecimal;

public interface TaskTableView {
    Integer getId();
    String getName();
    BigDecimal getScore();
    SchoolClassTableView getSchoolClass();

}
