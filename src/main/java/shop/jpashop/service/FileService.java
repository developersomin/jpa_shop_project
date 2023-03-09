package shop.jpashop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
@Slf4j
public class FileService {
    // 1-1. @Value 어노테이션(org.springframework.beans.factory.annotation.Value) -> application.properties에 설정한 "itemImgLocation" 프로퍼티 값을 읽어옵니다.
    @Value("${itemImgLocation}")
    private String itemImgLocation;



    public String getFullPath(String filename) {
        return itemImgLocation+filename;
    }

    public String createStoreFilename(String originalFilename) {
        UUID uuid = UUID.randomUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return uuid + extension;
    }

    public String uploadFile(String originalFilename,MultipartFile itemImgFile) throws IOException {

        String storeFilename = createStoreFilename(originalFilename);
        itemImgFile.transferTo(new File(getFullPath(storeFilename)));

        return storeFilename;
    }

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);
        if (deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일 삭제했습니다");
        } else {
            log.info("파일 존재하지 않습니다");
        }
    }


}

