package jobcafe.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Order {

    @Id
    private String id;
    @NotBlank
    private Date deliveryEstimate;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(referencedColumnName = "email")
    private User transactor;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private Plan plan;

    public Order(Date time, User email, Plan plan) {
        this.deliveryEstimate = time;
        this.transactor = email;
        this.plan = plan;
    }
}
