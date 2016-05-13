package com.itibo.grob.webapp.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("login")
public class LoginValidator implements Validator {
    private static final String LOGIN_PATTERN = "^[a-zA-Z0-9_-]{3,20}$";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Pattern pattern = Pattern.compile(LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(o.toString());
        if (!matcher.matches() || "".equals(o.toString())) {
            throw new ValidatorException(new FacesMessage("Login is not ok"));
        }
    }
}
