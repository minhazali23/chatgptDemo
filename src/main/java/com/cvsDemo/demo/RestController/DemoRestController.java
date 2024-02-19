package com.cvsDemo.demo.RestController;

import com.cvsDemo.demo.DTO.DemoDTO;
import com.cvsDemo.demo.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cvsDemo")
public class DemoRestController {

    @Autowired
    DemoService demoService;

    @GetMapping("getOne/{drug1}/{drug2}")
    public DemoDTO getProduct(@PathVariable(value = "drug1") String drug1,
                              @PathVariable(value = "drug2") String drug2){

        return demoService.getByName(drug1,drug2);
    }
}
