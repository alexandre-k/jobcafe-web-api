package jobcafe.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Profession {
    @Id
    public String label;
}
