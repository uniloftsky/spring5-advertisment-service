package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void setProfileImage(User user, MultipartFile file) throws IOException;

}
