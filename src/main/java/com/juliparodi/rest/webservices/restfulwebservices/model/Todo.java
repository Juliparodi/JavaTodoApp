package com.juliparodi.rest.webservices.restfulwebservices.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {

     @Id
     @GeneratedValue
     Long id;
     String username;
     String description;
     LocalDate targetDate;
     boolean isDone;

     @Override
     public boolean equals(Object o) {
          if (this == o) {
               return true;
          }
          if (o == null || getClass() != o.getClass()) {
               return false;
          }
          Todo todo = (Todo) o;
          return Objects.equals(id, todo.id);
     }

     @Override
     public int hashCode() {
          return Objects.hash(id);
     }
}
