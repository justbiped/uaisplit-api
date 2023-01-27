package biped.works.tosplit

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class IndexController {

    @GetMapping
    fun toSplitVersion(): ResponseEntity<String> {
        return ResponseEntity.ok().body("To split version: 0.1")
    }
}