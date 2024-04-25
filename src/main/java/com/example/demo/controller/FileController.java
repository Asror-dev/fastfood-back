package com.example.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {
    @GetMapping("/get")
    public void getFile(HttpServletResponse response, @RequestParam String path) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(
                path
        );
        FileCopyUtils.copy(inputStream,outputStream);
    }
}
