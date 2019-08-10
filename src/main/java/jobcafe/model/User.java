package jobcafe.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Email
    private String email;
    @Formula(value = "concat(first_name, ' ', last_name)")
    private String fullName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String password;
    private String phone;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private Profession profession;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private Plan membership;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private PaymentMethod paymentMethod;
    @ColumnDefault("true")
    private Boolean isNewMessageNotified;
    @ColumnDefault("true")
    private Boolean isNewServiceAdvertised;
    @ColumnDefault("true")
    private Boolean subscribed;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createdDate;
    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date updatedDate;
    @ColumnDefault("false")
    private Boolean isStaff;
    private String profilePicture;
}
