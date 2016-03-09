package me.wonwoo.post

import org.springframework.data.domain.{Pageable, Page}
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
  * Created by wonwoo on 2016. 3. 9..
  */
@Repository
trait PostRepository extends JpaRepository[Post, java.lang.Long] {

  def findByTitleStartingWithOrContentStartingWith(title: String, content: String, pageable: Pageable): Page[Post]

}
