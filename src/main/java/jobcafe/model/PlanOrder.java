package jobcafe.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PlanOrder {
    @Id
    private String id;
    @NotNull
    private Date deliveryEstimate;
    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;
    @OneToOne
    @JoinColumn(referencedColumnName = "email")
    private JUser transactor;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private SubscriptionPlan plan;

    public PlanOrder(JUser transactor, SubscriptionPlan plan) {
        this.id = "REF" + String.format("%06d", new Random().nextInt(9999));
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        // Add 3 business days
        calendar.add(Calendar.DATE, 3);
        this.deliveryEstimate = calendar.getTime();
        this.transactor = transactor;
        this.plan = plan;
    }
}
