package com.viratech.dto;

import com.viratech.enums.BillCategory;
import com.viratech.enums.BillStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateBillRequest(
        @NotBlank(message = "name is blank")
        @Size(min = 5, max = 100)
        String name,

        @NotNull(message = "amount is null")
        BigDecimal amount,

        @NotNull(message = "dueDate is null")
        LocalDate dueDate,

        @NotBlank(message = "category is blank")
        BillCategory category,

        @NotBlank(message = "status is blank")
        BillStatus status,

        @NotNull
        @Min(0)
        BigDecimal paidAmount,

        LocalDate paidAt

) {
}
