package com.amaris.business.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ChallengeServiceTest {

    @InjectMocks
    private ChallengeService challengeService;

    @Test
    @DisplayName("calcular salario anual")
    void calculoAnual() {
        BigDecimal salary = new BigDecimal("30000");
        final BigDecimal result = challengeService.calculoAnual(salary);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(new BigDecimal("360000"));
    }

    @Test
    @DisplayName("calcular salario anual null")
    void calculoAnualNull() {
        final BigDecimal result = challengeService.calculoAnual(null);
        assertThat(result).isNull();
    }
}