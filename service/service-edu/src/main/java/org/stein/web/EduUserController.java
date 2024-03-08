package org.stein.web;

import org.springframework.web.bind.annotation.*;
import org.stein.result.Result;

/**
 * @author stein
 * @date 2024/3/7
 */
@CrossOrigin
@RequestMapping("/edu/service/users")
@RestController
public class EduUserController {
    @PostMapping("/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public Result getInfo() {
        return Result
                .ok()
                .data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://gravatar.com/avatar/54eeae315bf7afebe8ec9b0382e3db95?s=400&d=robohash&r=x");
    }
}
