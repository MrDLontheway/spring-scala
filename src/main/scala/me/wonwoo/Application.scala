package me.wonwoo

import org.springframework.boot.SpringApplication

/**
  * Created by wonwoo on 2016. 3. 9..
  */
object Application {
  def main(args: Array[String]): Unit =
    SpringApplication.run(classOf[SpringBootConfig])
}

