package com.juliparodi.rest.webservices.restfulwebservices.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    String description;
    LocalDate targetDate;
    String username;

}
