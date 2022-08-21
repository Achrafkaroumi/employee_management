package com.giantlink.grh.services;

import com.giantlink.grh.entities.CompanyImage;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Responses.CompanyImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CompanyImageService {
    CompanyImageResponse saveDb(MultipartFile file , Integer companyId) throws IOException;
    CompanyImageResponse saveLocal(MultipartFile file , Integer companyId) throws IOException;
    CompanyImage getImage(String imageId) throws NotFoundException;
}
