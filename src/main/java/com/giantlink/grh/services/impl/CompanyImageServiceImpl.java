package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.entities.CompanyImage;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.mappers.CompanyImageMapper;
import com.giantlink.grh.models.Responses.CompanyImageResponse;
import com.giantlink.grh.repositories.CompanyImageRepository;
import com.giantlink.grh.repositories.CompanyRepository;
import com.giantlink.grh.services.CompanyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class CompanyImageServiceImpl implements CompanyImageService {
    private final Path pathLocation = Paths.get("uploads");

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyImageRepository companyImageRepository;


    @Override
    public CompanyImageResponse saveDb(MultipartFile image, Integer companyId) throws IOException {
        Optional<Company> findById = companyRepository.findById(companyId);
        CompanyImage companyImg=null;
        if(findById.isPresent()) {
           companyImg = CompanyImage.builder().company(findById.get())
                                                            .imageName(image.getOriginalFilename())
                                                            .imageType(image.getContentType())
                                                            .imageFile(image.getBytes()).build();
        }
        return CompanyImageMapper.INSTANCE.entityToResponse(companyImageRepository.save(companyImg));
    }
    @Override
    public CompanyImageResponse saveLocal(MultipartFile file, Integer companyId) throws IOException {
        return null;
    }

    @Override
    public CompanyImage getImage(String imageId) throws NotFoundException{
        Optional<CompanyImage> findById = companyImageRepository.findById(imageId);
        if(!findById.isPresent()) {
            throw new NotFoundException("Image not found");
        }
        return findById.get();
    }


}
