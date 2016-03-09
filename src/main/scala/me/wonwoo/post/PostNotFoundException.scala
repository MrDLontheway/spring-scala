package me.wonwoo.post

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
  * Created by wonwoo on 2016. 3. 9..
  */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
case class PostNotFoundException(message: String) extends RuntimeException(message) {

}
