package com.ssafy.ssafience.controller;
 
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
 import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import com.ssafy.ssafience.model.BasicResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.ssafy.ssafience.model.SingleResponse;
import org.springframework.util.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.*;
@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin("*")
@RestController
@RequestMapping("/api/upload")
@Api(tags = "File : 파일")
public class FileUpload {
    // private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
    
    @ApiOperation(value = "파일 올리기")
    @PostMapping("/uploadFile")
    public ResponseEntity<SingleResponse<String>> uploadFile(@RequestParam("file") MultipartFile file) {
        final SingleResponse<String> result = new SingleResponse<String>();
        System.out.println(file);
        try {
			String baseUri = "/home/ubuntu/ssafytimes/s03p13c208/images/";
            String fileName = RandomStringUtils.randomAlphabetic(10)+file.getOriginalFilename();;
			String filePath = baseUri + fileName;
			file.transferTo(new File(filePath));
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/resources/")
                                .path(fileName)
                                .toUriString();
			result.result = SUCCESS;
            result.status = HttpStatus.OK;
            result.setData(fileDownloadUri);

            return new ResponseEntity<SingleResponse<String>>(result, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
            result.result = FAIL;
            result.status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

        return new ResponseEntity<SingleResponse<String>>(result, HttpStatus.OK);
    }
}
 
