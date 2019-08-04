package jobcafe.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TicketCategory {
    @Id
    private String label;
}
