package kr.or.ddit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
public class FileUploadUtils {
    public static String uploadFolder = "E:\\eGovFrame3.10.0\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";

    //2) 연월일 폴더 생성
    public static String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String str = sdf.format(date);

        return str.replace("-", File.separator);
    }

    //3) 이미지인지 판단
    public static boolean checkImageType(File file) {
        String contentType = null;
        try {
            contentType = Files.probeContentType(file.toPath());

            log.info("contentType={}", contentType);

            return contentType.startsWith("image"); //MIME 타입 정보가 image로 시작하는지 true/false로 리턴
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    //4) 다중업로드 처리
    public static String multiUpload(MultipartFile[] pictures) {
        File uploadPath = new File(FileUploadUtils.uploadFolder, FileUploadUtils.getFolder());
        log.info("uploadPath : {}", uploadPath);
        if(uploadPath.exists()==false) {
            uploadPath.mkdirs();
        }

        for(MultipartFile picture : pictures) {
            log.info("----------------------");
            log.info("파일명 : {}", picture.getOriginalFilename());
            log.info("파일크기 : {}", picture.getSize());
            log.info("MINE타입 : {}", picture.getContentType());

            String uploadFileName = picture.getOriginalFilename();
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);

            UUID uuid = UUID.randomUUID();

            uploadFileName = uuid.toString() + "_" + uploadFileName;
            //File 객체 설계(복사할 대상 경로, 파일명)
            File saveFile = new File(uploadPath, uploadFileName);

            try {
                //원본파일객체.transferTo(설계)
                //파일 복사가 일어남
                picture.transferTo(saveFile);

                if(checkImageType(saveFile)) {
                    //설계
                    FileOutputStream thumbnail = new FileOutputStream(
                            new File(uploadPath, "s_" + uploadFileName)
                    );
                    //썸네일 생성(원본,설계,가로크기,세로크기)
                    Thumbnailator.createThumbnail(picture.getInputStream(),thumbnail,100,100);
                    thumbnail.close();
                }
            }catch (IllegalStateException e) {
                log.error(e.getMessage());
                return "0";
            }catch (IOException e) {
                log.error(e.getMessage());
                return "0";
            }
        }
        return "1";
    }
}