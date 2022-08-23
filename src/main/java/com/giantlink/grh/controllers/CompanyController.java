package com.giantlink.grh.controllers;

import java.io.IOException;
import java.util.List;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.entities.CompanyImage;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.CompanyRequest;
import com.giantlink.grh.models.Responses.CompanyImageResponse;
import com.giantlink.grh.models.Responses.CompanyResponse;
import com.giantlink.grh.services.CompanyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.giantlink.grh.services.CompanyService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	@Autowired
	CompanyImageService companyImageService;

	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<List<CompanyResponse>> get() throws NotFoundException {
		return new ResponseEntity<List<CompanyResponse>>(companyService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<CompanyResponse> get(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<>(companyService.get(id), HttpStatus.OK);
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<List<Company>> get(@RequestParam(name = "page")int page , @RequestParam(name = "size")int size  ){
		Pageable pageable = PageRequest.of(page, size);
		return new ResponseEntity<List<Company>>(companyService.get(pageable), HttpStatus.OK);
	}

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<CompanyResponse> add(@Valid @RequestBody CompanyRequest company) throws AlreadyExists{
		return new ResponseEntity<CompanyResponse>(companyService.add(company), HttpStatus.CREATED);
	}

	@PostMapping("/upload/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<CompanyImageResponse> uploadDb(@PathVariable(value = "id")Integer companyId ,
														 @RequestParam(value = "image")MultipartFile image) throws IOException {

		CompanyImageResponse saveCompanyImage = companyImageService.saveDb(image, companyId);
		String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/company")
				.path("/download/").path(saveCompanyImage.getId()).toUriString();
		saveCompanyImage.setImageLink(imageUrl);
		return new ResponseEntity<>(saveCompanyImage,HttpStatus.OK);
	}
	@GetMapping("/download/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<Resource> download(@PathVariable String id) throws NotFoundException{
		CompanyImage companyImage = companyImageService.getImage(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(companyImage.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + companyImage.getImageName())
				.body(new ByteArrayResource(companyImage.getImageFile()));
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<CompanyResponse> update(@PathVariable Integer id ,@Valid @RequestBody CompanyRequest company) throws NotFoundException {
		return new ResponseEntity<>(companyService.update(id,company), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException {
		companyService.delete(id);
		return ResponseEntity.ok("Entity deleted");
	}
}
