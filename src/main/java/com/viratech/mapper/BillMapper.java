package com.viratech.mapper;

import com.viratech.dto.CreateBillRequest;
import com.viratech.dto.CreateBillResponse;
import com.viratech.models.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {

    public CreateBillResponse createBillResponseDto(Bill entity){
        return new CreateBillResponse(
                entity.getId(),
                entity.getName(),
                entity.getAmount(),
                entity.getDueDate(),
                entity.getBillCategory(),
                entity.getBillStatus(),
                entity.getPaidAmount(),
                entity.getPaidAt(),
                entity.getCreatedAt()
        );
    }

    public Bill createBillRequestDto(CreateBillRequest request){
        return Bill.builder()
                .name(request.name())
                .amount(request.amount())
                .dueDate(request.dueDate())
                .billCategory(request.category())
                .build();
    }

}
