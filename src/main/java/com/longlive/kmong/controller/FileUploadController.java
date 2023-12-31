package com.longlive.kmong.controller;

//import com.amazonaws.services.s3.AmazonS3Client;
import com.longlive.kmong.DTO.FileUploadDTO;
import com.longlive.kmong.config.auth.PrincipalDetails;
//import com.longlive.kmong.service.S3UploadService;
import com.longlive.kmong.service.S3UploadService;
import com.longlive.kmong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final UserService userService;
//    private final AmazonS3Client amazonS3Client;
    private final S3UploadService s3UploadService;
    @PostMapping("/file-upload")
    public String uploadFile(@RequestPart("file") MultipartFile file ,@AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        System.out.println(s3UploadService.saveFile(file,principalDetails));

       return s3UploadService.saveFile(file,principalDetails);

//         파일을 저장하고 저장된 이미지 URL을 반환하는 로직 구현

//        String fileDir = "C:\\Users\\윤민수\\Downloads\\kmongimage\\"; // 프로젝트 내부의 static/img 디렉토리 경로
//        UUID uuid = UUID.randomUUID();
//        String fileName = uuid.toString()+"_"+file.getOriginalFilename();
//
//        Map<String,String> map = new HashMap<>();
//        map.put("email",principalDetails.getDto().getUser_email());
//        map.put("image",fileName);
//            userService.updateImage(map);
//           principalDetails.getDto().setUser_image(fileName);
//        try {
//            // 디렉토리 생성 (없는 경우)
//            File directory = new File(fileDir);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // 파일 저장
//            file.transferTo(new File(fileDir + fileName));
//
//            // 이미지 URL 생성 (예: "/static/img/파일명")
//            String imageUrl = "/resources/user/" + fileName;
//
//            return "{\"imageUrl\": \"" + fileName + "\"}";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "{\"error\": \"파일 업로드 실패\"}";
//        }
    }


    @PostMapping("/file-upload2")
    @ResponseBody
    public FileUploadDTO uploadFile2(@RequestPart("upload") MultipartFile file , @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        // 파일을 저장하고 저장된 이미지 URL을 반환하는 로직 구현


        try {

            String fileName = s3UploadService.uploadFile(file);
            // 이미지 URL 생성 (예: "/static/img/파일명")
            String imageUrl = "/resources/user/" + fileName;

            return FileUploadDTO.builder().uploaded(true).fileName(fileName).url(imageUrl).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
//        String fileDir = "C:\\Users\\윤민수\\Downloads\\kmongimage\\"; // 프로젝트 내부의 static/img 디렉토리 경로
//        String fileName = file.getOriginalFilename();
//
//
//        try {
//            // 디렉토리 생성 (없는 경우)
//            File directory = new File(fileDir);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // 파일 저장
//            file.transferTo(new File(fileDir + fileName));
//
//            // 이미지 URL 생성 (예: "/static/img/파일명")
//            String imageUrl = "/resources/user/" + fileName;
//
//            return FileUploadDTO.builder().uploaded(true).fileName(fileName).url(imageUrl).build();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

}
