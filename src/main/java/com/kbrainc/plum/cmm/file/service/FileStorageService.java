package com.kbrainc.plum.cmm.file.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;

public interface FileStorageService {
    public FileVo storeFile(MultipartFile file, FileGrpVo fileGrpVo);

    public Resource loadFileAsResource(FileVo fileVo) throws Exception;

    public boolean deleteFile(FileVo fileVo);
    
    public String imgToStringByBase64(String filePath) throws Exception;
    
}
