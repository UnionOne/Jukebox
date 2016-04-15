package com.itibo.grob.webapp.validation;

import com.itibo.grob.webapp.model.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        FileBucket fileBucket = (FileBucket) o;

        if (fileBucket.getFile() != null) {
            if (fileBucket.getFile().getSize() == 0) {
                errors.rejectValue("file", "missiing.file");
            }
        }
    }
}
