package com.hana.onemoim.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;

@RequiredArgsConstructor
@Service
public class S3uploader {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName; // S3 버켓 이름
    private final AmazonS3Client amazonS3Client; // S3 클라이언트

    public String Uploader(MultipartFile multipartFile) throws IOException {
        // 파일 이름 생성
        String fileName = CommonUtils.buildFileName(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize(); // 파일 크기

        // 이미지 리사이즈
        BufferedImage image = resizeImage(multipartFile, 800);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType()); // MIME 타입 설정
        objectMetadata.setContentLength(size); // 파일 크기 설정

        String contentType = multipartFile.getContentType();
        // 이미지를 InputStream으로 변환
        ByteArrayInputStream inputStream = imageToInputStream(image, contentType, objectMetadata);

        // S3에 파일 업로드 및 public read 권한 설정
        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        // S3 업로드된 파일의 URL 가져오기
        String imageUrl = amazonS3Client.getUrl(bucketName, fileName).toString();
        // 한글 깨짐 방지용 URL 디코드
        imageUrl = URLDecoder.decode(imageUrl, "UTF-8");
        return imageUrl;
    }

    // 이미지 리사이징 메서드
    private BufferedImage resizeImage(MultipartFile multipartFile, int targetWidth) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());

        // 이미지가 아닌 경우 예외 발생
        if (bufferedImage == null) {
            throw new IllegalArgumentException("잘못된 요청");
        }

        // 이미지 크기가 목표보다 작으면 그대로 반환
        if (bufferedImage.getWidth() < targetWidth) {
            return bufferedImage;
        }

        // 이미지 리사이즈 후 반환
        return Scalr.resize(bufferedImage, targetWidth);
    }

    // BufferedImage를 InputStream으로 변환
    private ByteArrayInputStream imageToInputStream(BufferedImage bufferedImage, String contentType, ObjectMetadata objectMetadata) throws IOException {
        String fileExtension = contentType.split("/")[1];

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 이미지 데이터를 byteArrayOutputStream에 쓰기
        ImageIO.write(bufferedImage, fileExtension, byteArrayOutputStream);

        // 메타데이터 설정
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(byteArrayOutputStream.size());

        // ByteArrayInputStream 반환
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}