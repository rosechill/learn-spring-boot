package jiwon.webmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Objects;

@Controller
public class UploadController {

    @PostMapping(path = "/upload/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String upload(
            @RequestParam(name = "name") String name,
            @RequestPart(name = "profile") MultipartFile profile
    ) throws IOException {
        // init path to save
        Path path = Path.of("upload/" + profile.getOriginalFilename());
        // move file to path
        profile.transferTo(path);
        return "Success save profile " + name + " to " + path;
    }

    @PostMapping("/upload/profile-base64")
    public <UploadRequest> String uploadBase64(
            @RequestBody UploadRequest request) throws IOException {

        byte[] imageBytes =
                Base64.getDecoder().decode(Objects.requireNonNull(request.getClass().getResourceAsStream("/upload/profile-base64")).readAllBytes());

        Path path = Path.of("upload/profile.png");

        Files.write(path, imageBytes);

        return "Success";
    }

}
