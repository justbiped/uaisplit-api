package biped.works.tosplit.config

import biped.works.tosplit.auth.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ToSplitControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleIllegalArguments(error: Throwable, request: WebRequest): String? {
        //log something here
        return error.message
    }

    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    fun handleUnauthorizedException(error: Throwable, request: WebRequest): String? {
        //log something here
        return error.message
    }
}