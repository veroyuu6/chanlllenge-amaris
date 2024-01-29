package com.amaris.view.controller;


import com.amaris.business.dto.EmployeeDto;
import com.amaris.business.service.ChallengeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@Named
@ViewScoped
public class HomeController implements Serializable {

    @Getter
    @Setter
    private List<EmployeeDto> items;

    @Getter
    @Setter
    private Integer idInput;

    private static final String MESSAGE_VALIDATION = "Error al consumir el servicio";


    @Autowired
    private ChallengeService challengeService;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }


    public void loadComponent() throws InterruptedException {
        final PrimeFaces current = PrimeFaces.current();
        current.executeScript("buttonEnable()");
        current.ajax().update("loadId");
        TimeUnit.SECONDS.sleep(1);
    }

    public void refresh() throws InterruptedException {
        loadRefresh();
        loadComponent();

    }

    public void findById() {
        try {
            if (idInput == null) {
                items = challengeService.getAll().getData();
            } else {
                items = challengeService.getById(idInput).getData();
                idInput = null;
            }
            loadComponent();
        } catch (Exception e) {
            sentMessage(MESSAGE_VALIDATION, FacesMessage.SEVERITY_ERROR);
        }

    }

    private void sentMessage(final String detail, final FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(severity, MESSAGE_VALIDATION, detail));
    }

    private void loadRefresh() {
        try {
            items = challengeService.getAll().getData();
        } catch (Exception e) {
            sentMessage(MESSAGE_VALIDATION, FacesMessage.SEVERITY_ERROR);
        }
    }

    public BigDecimal calculoAnual(BigDecimal salario) {
        if (salario != null) {
            return challengeService.calculoAnual(salario);
        }
        return null;

    }

}
