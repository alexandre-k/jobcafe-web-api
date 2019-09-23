package jobcafe.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.sql.Timestamp;
import javax.persistence.Column;
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
public class JUser {
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
    private Integer passwordLength;
    private String phone;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private Profession profession;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private SubscriptionPlan membership;
    @ColumnDefault("true")
    private Boolean isNewMessageNotified;
    @ColumnDefault("true")
    private Boolean isNewServiceAdvertised;
    @ColumnDefault("true")
    private Boolean subscribed;
    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;
    @Column(name = "updated_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedDate;
    @ColumnDefault("false")
    private Boolean isStaff;
    private String profilePicture;
}
