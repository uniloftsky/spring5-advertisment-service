package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final String UPLOAD_PATH = System.getProperty("user.dir") + "/resources/";

    @Override
    public void setProfileImage(User user, MultipartFile file) throws IOException {
        if (file != null) {
            File uploadDir = new File(UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
                File uploadItemsDir = new File("resources/profile_images");
                if (!uploadItemsDir.exists()) {
                    uploadItemsDir.mkdir();
                }
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = "profile_images/" + uuidFile + "-" + file.getOriginalFilename();
            file.transferTo(new File(UPLOAD_PATH + resultFilename));
            user.setImg(resultFilename);
        }
    }
}
