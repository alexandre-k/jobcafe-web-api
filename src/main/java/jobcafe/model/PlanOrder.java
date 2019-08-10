package jobcafe.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class PlanOrder {
    @Id
    private String id;
    @NotBlank
    private Date deliveryEstimate;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createdDate;
    private String transactorEmail;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private SubscriptionPlan plan;
}
