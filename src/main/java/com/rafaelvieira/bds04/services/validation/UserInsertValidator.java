package com.rafaelvieira.bds04.services.validation;


import com.rafaelvieira.bds04.controllers.exceptions.FieldMessage;
import com.rafaelvieira.bds04.dto.UserInsertDTO;
import com.rafaelvieira.bds04.entities.User;
import com.rafaelvieira.bds04.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // Area onde efetua os testes de validação
        User user = repository.findByEmail(dto.getEmail());
        if (user != null) {
            list.add(new FieldMessage("email", "Email já existe"));
        }

        //Implementa no fieldmensage os erros de validação
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
