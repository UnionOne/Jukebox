package com.itibo.grob.webapp.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("email")
public class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN = "^([a-z0-9_-]+)@([a-z0-9_-]+)\\.([a-z]{2,6})$";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(o.toString());
        if (!matcher.matches() || "".equals(o.toString())) {
            throw new ValidatorException(new FacesMessage("Email is not ok"));
        }
    }
}
