package me.wonwoo.post


import java.util.Date

import me.wonwoo.account.AccountNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Pageable, Page}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
  * Created by wonwoo on 2016. 3. 9..
  */

@Service
@Transactional
class PostService @Autowired()(val postRepository: PostRepository) {

  @Transactional(readOnly = true)
  def findOne(id: Long) = Option(postRepository.findOne(id)) getOrElse (throw PostNotFoundException(s"post id $id  not found"))

  @Transactional(readOnly = true)
  def findByTitleOrContent(title: String, content: String, pageable: Pageable) : Page[Post] = postRepository.findByTitleStartingWithOrContentStartingWith(title, content,pageable)

  def save(post: Post) = {
    post.setRegDate(new Date)
    postRepository.save(post)
  }

  def update(id: Long, post: Post) = {

    val oldPost = this.findOne(id)
    post.id = oldPost.id
    if (!Option(post.getTitle).exists(_.nonEmpty))
      post.setTitle(oldPost.getTitle)
    if (!Option(post.getContent).exists(_.nonEmpty))
      post.setContent(oldPost.getContent)

    postRepository.save(post)
  }

  def delete(id: Long) = postRepository.delete(id)

}
