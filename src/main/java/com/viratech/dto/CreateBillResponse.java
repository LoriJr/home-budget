package com.viratech.dto;

import com.viratech.enums.BillCategory;
import com.viratech.enums.BillStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record CreateBillResponse(

         Long id,
         String name,
         BigDecimal amount,
         LocalDate dueDate,

         BillCategory category,
         BillStatus status,
         BigDecimal paidAmount,
         LocalDate paidAt,
         Instant createdAt
) {
}
