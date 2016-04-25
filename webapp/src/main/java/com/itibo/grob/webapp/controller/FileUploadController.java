package com.itibo.grob.webapp.controller;

import com.itibo.grob.dataaccess.model.Genre;
import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.services.AccountService;
import com.itibo.grob.services.ManagerAccountService;
import com.itibo.grob.webapp.model.FileBucket;
import com.itibo.grob.webapp.validation.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {
    private static final String UPLOAD_LOCATION = "/home/union/temp/";

    @Autowired
    FileValidator fileValidator;

    @Autowired
    AccountService accountService;

    @Autowired
    ManagerAccountService managerAccountService;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "jukebox";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "jukebox";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();
            File dir = new File(UPLOAD_LOCATION + File.separator + SecurityContextHolder.getContext().getAuthentication().getName());

            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileCopyUtils.copy(fileBucket.getFile().getBytes(),
                    new File(dir.getAbsolutePath() + File.separator + fileBucket.getFile().getOriginalFilename()));

            String fileName = multipartFile.getOriginalFilename();

            addTrack(fileName, dir.getAbsolutePath() + File.separator + fileBucket.getFile().getOriginalFilename(),
                    SecurityContextHolder.getContext().getAuthentication().getName());

            System.out.println(dir.getAbsolutePath());
            model.addAttribute("fileName", fileName);
        }
        return "redirect:/pages/jukebox.xhtml";
    }

    private void addTrack(String fileName, String filePath, String login) {
        Genre genre = new Genre("genre");
        Track track = new Track(fileName, "default", genre, "album", "band", filePath.substring(11, filePath.length()));

        managerAccountService.addTrack(accountService.findOneAccountByLogin(login), track);
    }
}
