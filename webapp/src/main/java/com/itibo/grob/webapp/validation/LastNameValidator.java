package com.itibo.grob.webapp.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("lastName")
public class LastNameValidator implements Validator {
    private static final String LAST_NAME_PATTERN = "^[a-z]{3,20}$";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Pattern pattern = Pattern.compile(LAST_NAME_PATTERN);
        Matcher matcher = pattern.matcher(o.toString());
        if (!matcher.matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "LAST name is not ok", null));
        }
    }
}
