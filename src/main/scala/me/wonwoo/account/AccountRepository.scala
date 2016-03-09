package me.wonwoo.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
  * Created by wonwoo on 2016. 3. 9..
  */
@Repository
trait AccountRepository extends JpaRepository[Account, java.lang.Long] {
  def findByName(name: String): Account
}
