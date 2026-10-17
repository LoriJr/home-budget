package com.viratech.service;

import com.viratech.dto.CreateBillRequest;
import com.viratech.dto.CreateBillResponse;
import com.viratech.dto.UpdateBillRequest;
import com.viratech.mapper.BillMapper;
import com.viratech.models.Bill;
import com.viratech.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;
    private final BillMapper mapper;

    public CreateBillResponse createBill(CreateBillRequest request){
        Bill bill = mapper.createBillRequestDto(request);
        return mapper.createBillResponseDto(repository.save(bill));
    }

    public CreateBillResponse findBill(Long id){
        Bill bill = getBill(id);
        return mapper.createBillResponseDto(bill);
    }

    public List<CreateBillResponse> listBill(){
        List<Bill> bills = repository.findAll();
        return mapper.billResponseList(bills);
    }

    public void deleteBill(Long id){
        Bill bill = getBill(id);
        repository.delete(bill);
    }

    public CreateBillResponse updateBill(Long id, UpdateBillRequest request){
        Bill bill = getBill(id);

        bill.setName(request.name());
        bill.setAmount(request.amount());
        bill.setDueDate(request.dueDate());
        bill.setBillCategory(request.category());
        bill.setBillStatus(request.status());
        bill.setPaidAmount(request.paidAmount());
        bill.setPaidAt(request.paidAt());

        return mapper.createBillResponseDto(repository.save(bill));
    }



    private Bill getBill(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Resource not found."));
    }
}
