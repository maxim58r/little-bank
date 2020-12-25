package com.max.littlebank.service;

import com.max.littlebank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author Serov Maxim
 */

@Service
public class StorageServiceImpl implements StorageService {

    private final UserService userService;

    public StorageServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init() {
    }

    @Override
    public void store(MultipartFile file, long id) {
        User user = userService.findById(id);
        try {
            user.setImage(file.getBytes());
            userService.updateUser(user);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
