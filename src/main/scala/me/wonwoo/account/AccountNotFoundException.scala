package me.wonwoo.account

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
  * Created by wonwoo on 2016. 3. 9..
  */
@ResponseStatus(HttpStatus.BAD_REQUEST)
case class AccountNotFoundException(message: String) extends RuntimeException(message)
