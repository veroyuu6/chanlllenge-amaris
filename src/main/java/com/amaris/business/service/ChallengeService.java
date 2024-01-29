package com.amaris.business.service;


import com.amaris.business.dto.EmployeeResponseDto;
import com.amaris.infrastructure.client.ChallengeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeClient client;

    public EmployeeResponseDto getAll() throws Exception {
        return client.getAll();
    }

    public EmployeeResponseDto getById(long id) throws Exception {

        return client.getById(id);
    }

    public BigDecimal calculoAnual(BigDecimal salario) {
        if (salario != null) {
            return salario.multiply(BigDecimal.valueOf(12));
        } else {
            return null;
        }

    }


}
