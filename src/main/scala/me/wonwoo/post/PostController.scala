package me.wonwoo.post

import javax.validation.Valid

import me.wonwoo.exception.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation._

/**
  * Created by wonwoo on 2016. 3. 9..
  */

@RestController
class PostController @Autowired()(postService: PostService, postRepository: PostRepository) {

  @RequestMapping(value = Array("/posts"), method = Array(RequestMethod.GET))
  @ResponseStatus(HttpStatus.OK)
  def posts(pageable: Pageable) = postRepository.findAll(pageable)

  @RequestMapping(value = Array("/post/{id}"), method = Array(RequestMethod.GET))
  @ResponseStatus(HttpStatus.OK)
  def post(@PathVariable id: Long) = {
    postService.findOne(id)
  }

  @RequestMapping(value = Array("/post"), method = Array(RequestMethod.GET))
  @ResponseStatus(HttpStatus.OK)
  def searchPost(@RequestParam("q") q: String, pageable: Pageable) = {
    postService.findByTitleOrContent(q, q, pageable)
  }

  @RequestMapping(value = Array("/post"), method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.CREATED)
  def createPost(@Valid @RequestBody post: Post, bindingResult: BindingResult) = {
    if (bindingResult.hasErrors) throw BadRequestException(bindingResult.getFieldError.getDefaultMessage)
    postService.save(post)
  }

  @RequestMapping(value = Array("/post/{id}"), method = Array(RequestMethod.PATCH))
  @ResponseStatus(HttpStatus.OK)
  def updatePost(@PathVariable id: Long, @Valid @RequestBody post: Post, bindingResult: BindingResult) = {
    if (bindingResult.hasErrors) throw BadRequestException(bindingResult.getFieldError.getDefaultMessage)
    postService.update(id, post)
  }

  @RequestMapping(value = Array("/post/{id}"), method = Array(RequestMethod.DELETE))
  @ResponseStatus(HttpStatus.NO_CONTENT)
  def deletePost(@PathVariable id: Long) = postService.delete(id)
}