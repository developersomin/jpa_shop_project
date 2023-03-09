package shop.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import shop.jpashop.entity.ItemImg;
import shop.jpashop.repository.ItemImgRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;



    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException {

        String originalFilename = itemImgFile.getOriginalFilename();
        String imgUrl = "";
        String imgName = "";

        if (!originalFilename.isEmpty()) {
            imgName = fileService.uploadFile(originalFilename,itemImgFile);
            imgUrl = "/images/item/" + imgName;
        }


        itemImg.updateItemImg(originalFilename,imgName,imgUrl);
        itemImgRepository.save(itemImg);
    }





}
