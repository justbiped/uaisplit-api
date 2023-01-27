package biped.works.tosplit.user

//import com.biped.tosplit.auth.AuthFilter.Companion.UID_KEY
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

//    @GetMapping
//    fun createUser(@RequestAttribute(UID_KEY) uid: String): ResponseEntity<*> {
//        print(uid)
//        return ResponseEntity.ok().build<String>()
//    }
}