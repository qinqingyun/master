package com.meituan.food.web;

import com.meituan.food.mapper.GitPOMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/git")
public class GitController {

    @Resource
    private GitPOMapper gitPOMapper;

    @GetMapping("/delete")
    public String deleteGitData(@RequestParam("id") int id){
        gitPOMapper.deleteLessPrimaryKey(id);
        return "OK!";
    }

}
