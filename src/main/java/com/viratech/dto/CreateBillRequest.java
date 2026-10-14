package com.viratech.dto;

import com.viratech.enums.BillCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateBillRequest(
        @NotBlank(message = "name is blank")
        @Size(min = 5, max = 100)
        String name,

        @NotNull(message = "amount is null")
        BigDecimal amount,

        @NotNull(message = "dueDate is null")
        LocalDate dueDate,

        @NotBlank(message = "category is blank")
        BillCategory category
) {
}
