package com.example.exatinator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Section {
    private int id;


    private List<Questions> questions;


    // Добавьте конструктор, который принимает List<Questions>
    public Section(List<Questions> questions) {
        this.questions = questions;
    }


}
