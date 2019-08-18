package jobcafe.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(referencedColumnName = "issuer")
    private CreditCardIssuer card;
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String cardholderName;
    @NotNull
    private Integer cvv;
    @NotNull
    @JsonFormat(pattern="YYYY/mm")
    private Date expirationDate;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String stateProvince;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String country;
    @OneToOne(targetEntity = JUser.class)
    @JoinColumn(referencedColumnName = "email")
    JUser payer;

}
