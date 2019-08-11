package jobcafe.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    public PlanOrder() {}

    public PlanOrder(String transactorEmail, SubscriptionPlan plan) {
        this.id = "REF" + String.format("%06d", new Random().nextInt(9999));
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        // Add 3 business days
        calendar.add(Calendar.DATE, 3);
        this.deliveryEstimate = calendar.getTime();
        this.transactorEmail = transactorEmail;
        this.plan = plan;
    }
}
