package com.restapi.restapidemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodeMustStartWithConstraintValidator implements ConstraintValidator<CodeMustStartWith, String> {

    private String warehousePrefix;

    @Override
    public void initialize(CodeMustStartWith theWarehouseCode) {
        warehousePrefix = theWarehouseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;

        if (theCode != null) {
            result = theCode.startsWith(warehousePrefix);
        }
        else {
            result = true;
        }

        return result;
    }
}
