package com.malpan.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.malpan.file.mapper.FileMapper;
import com.malpan.file.model.File;
import com.malpan.file.service.FileService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}