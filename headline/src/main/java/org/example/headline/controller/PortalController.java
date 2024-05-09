package org.example.headline.controller;

import org.example.headline.service.HeadlineService;
import org.example.headline.utils.Result;
import org.example.headline.vo.PortalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private HeadlineService headlineService;


    @PostMapping("findNewsPage")
    public  Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result=headlineService.findNewsPage(portalVo);
        return result;
    }
}
