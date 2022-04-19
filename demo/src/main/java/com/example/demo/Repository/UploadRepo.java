package com.example.demo.Repository;


import com.example.demo.model.Upload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepo extends JpaRepository<Upload, Object> {
    
}
