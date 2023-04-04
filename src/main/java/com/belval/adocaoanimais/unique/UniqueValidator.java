package com.belval.adocaoanimais.unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.UsuarioRepository;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {
    @Autowired
    private UsuarioRepository userRepository;

    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        fieldName = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Usuario user = userRepository.findByEmail((String) value);
        return user == null;
    }
}
