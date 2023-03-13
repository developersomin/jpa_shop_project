package shop.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.jpashop.entity.ItemImg;
import shop.jpashop.repository.ItemImgRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

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

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
        if (!itemImgFile.isEmpty()) {
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId).orElseThrow(() -> new EntityNotFoundException());


            if (!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(fileService.getFullPath(savedItemImg.getImgName()));
            }

            String originalFilename = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(originalFilename,itemImgFile);
            String imgUrl = "/images/item/" + imgName;

            savedItemImg.updateItemImg(originalFilename,imgName,imgUrl);
        }
    }





}
