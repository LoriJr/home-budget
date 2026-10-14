package com.viratech.models;

import com.viratech.enums.BillCategory;
import com.viratech.enums.BillStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "bills" )
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "bill_category", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private BillCategory billCategory;
    @Enumerated(EnumType.STRING)
    @Column(name = "bill_status", nullable = false, length = 30)
    private BillStatus billStatus;

    @Column(name = "paid_amount")
    private BigDecimal paidAmount;
    @Column(name = "paid_at")
    private LocalDate paidAt;
    @Column(name = "created_at")
    private Instant createdAt;

    @PrePersist
    void prePersist(){
        if(createdAt == null) createdAt = Instant.now();
    }

}
