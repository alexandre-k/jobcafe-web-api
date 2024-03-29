package jobcafe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class SubscriptionPlan {
    @Id
    private String label;
    @NotBlank
    private Double price;
    @NotBlank
    private Double tax;
}
