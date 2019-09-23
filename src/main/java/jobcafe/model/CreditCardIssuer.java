package jobcafe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="credit_card_issuer")
public class CreditCardIssuer {
    @Id
    private String issuer;
}
