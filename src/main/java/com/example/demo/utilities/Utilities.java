package com.example.demo.utilities;


import com.example.demo.constants.Constants;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class Utilities {

    private Utilities() {}


    public static void fileValidationDriver(final MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null || file.getOriginalFilename().isBlank() || file.getSize() == 0)
            throw new IllegalArgumentException(Constants.FILE_IS_EMPTY_ERROR);
        if (!Arrays.asList("csv","parquet").contains(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1 ).toLowerCase()))
            throw new IllegalArgumentException(Constants.WRONG_EXTENSION);
    }


}
