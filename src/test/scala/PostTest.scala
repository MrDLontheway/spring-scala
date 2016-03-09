import java.util.Date

import com.fasterxml.jackson.databind.ObjectMapper
import me.wonwoo.SpringBootConfig
import me.wonwoo.account.Account
import me.wonwoo.post.Post
import org.hamcrest.Matchers._
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.junit.{Before, FixMethodOrder, Test}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders._
import org.springframework.test.web.servlet.result.MockMvcResultHandlers._
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext;

/**
  * Created by wonwoo on 2016. 3. 10..
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(Array(classOf[SpringBootConfig]))
@WebAppConfiguration
@FixMethodOrder(MethodSorters.JVM)
class PostTest {

  var objectMapper: ObjectMapper = _

  var mockMvc: MockMvc = _

  @Autowired
  var wac: WebApplicationContext = _

  @Before
  def before = {
    objectMapper = new ObjectMapper()
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
  }

  @Test
  def postsTest: Unit = mockMvc.perform(get("/posts")).andDo(print()).andExpect(status.isOk)

  @Test
  def postTest: Unit = mockMvc.perform(get("/post/1")).andDo(print())
    .andExpect(jsonPath("$.title", is("post1")))
    .andExpect(status.isOk)

  @Test
  def createPostTest: Unit = {
    val createPost = new Post()
    createPost.setTitle("create post1")
    createPost.setContent("spring boot scala create post test")
    mockMvc.perform(post("/post").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(createPost)))
      .andDo(print())
      .andExpect(status.isCreated)
      .andExpect(jsonPath("$.title", is("create post1")))
      .andExpect(jsonPath("$.content", is("spring boot scala create post test")))

  }

  @Test
  def updatePostTest: Unit = {
    val updatePost = new Post()
    updatePost.setTitle("good scala")
    updatePost.setContent("good spring and scala")
    mockMvc.perform(patch("/post/2").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatePost)))
      .andDo(print())
      .andExpect(status.isOk)
      .andExpect(jsonPath("$.title",is("good scala")))
      .andExpect(jsonPath("$.content", is("good spring and scala")))
  }
  @Test
  def deletePostTest : Unit = mockMvc.perform(delete("/post/2")).andDo(print()).andExpect(status.isNoContent)

  @Test
  def postNotFoundExceptionTest : Unit = mockMvc.perform(get("/post/10")).andDo(print()).andExpect(status.isBadRequest)

  @Test
  def postBadRequestExceptionTest : Unit = {
    val createPost = new Post()
    createPost.setContent("post bad request test")
    mockMvc.perform(post("/post").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(createPost)))
      .andDo(print())
      .andExpect(status.isBadRequest)
  }
}
