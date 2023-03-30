package com.belval.adocaoanimais.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.belval.adocaoanimais.dto.TrocaSenhaUsuarioDto;

public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, Object> {

    @Override
    public void initialize(MatchPassword constraintAnnotation) {}

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        TrocaSenhaUsuarioDto trocaSenhaUsuarioDto = (TrocaSenhaUsuarioDto) obj;
        return trocaSenhaUsuarioDto.getNovaSenha().equals(trocaSenhaUsuarioDto.getConfirmarNovaSenha());
    }

}
