package me.wonwoo.account


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
  * Created by wonwoo on 2016. 3. 9..
  */
@Service
@Transactional
class AccountService @Autowired()(accountRepository: AccountRepository) {

  @Transactional(readOnly = true)
  def account(id: Long): Account = {
    Option(accountRepository.findOne(id)) getOrElse (throw AccountNotFoundException(s"account id $id  not found"))
  }

  def save(account: Account) = {
    accountRepository.save(account)
  }

  def update(id: Long, account: Account) = {
    val oldAccount = this.account(id)
    account.id = oldAccount.id
    if (!Option(account.getName).exists(_.nonEmpty))
      account.setName(oldAccount.getName)
    if (!Option(account.getPassword).exists(_.nonEmpty))
      account.setPassword(oldAccount.getPassword)
    accountRepository.save(account)
  }

  def delete(id: Long) {
    accountRepository.delete(id)
  }
}
