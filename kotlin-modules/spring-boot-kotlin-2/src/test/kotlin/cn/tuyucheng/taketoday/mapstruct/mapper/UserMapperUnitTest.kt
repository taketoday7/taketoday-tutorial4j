package cn.tuyucheng.taketoday.mapstruct.mapper

import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UserMapperUnitTest {
   private var userMapper: cn.tuyucheng.taketoday.mapstruct.mapper.UserMapper = Mappers.getMapper(cn.tuyucheng.taketoday.mapstruct.mapper.UserMapper::class.java)

//   @Test
//   fun `should map entity to dto`() {
//      val user = cn.tuyucheng.taketoday.mapstruct.entity.User(
//            id = 1,
//            name = "John Doe",
//            createdAt = Date().apply {
//               time = 1679682600000 // 2023-03-24 18:30:00
//            },
//            address = cn.tuyucheng.taketoday.mapstruct.entity.Address(
//                  streetAddress = "123 Main St",
//                  zipCode = "12345"
//            ),
//            status = "UNKNOWN"
//      )
//      val userDto = userMapper.toDto(user)
//      assertEquals(user.id, userDto.id)
//      assertEquals(user.name, userDto.name)
//      assertEquals("2023-03-24 18:30:00", userDto.createdOn)
//      assertEquals(user.address.zipCode, userDto.address.zipCode)
//      assertEquals("ACTIVE", userDto.status)
//   }

   @Test
   fun `should map dto to entity`() {
      val userDto = cn.tuyucheng.taketoday.mapstruct.dto.UserDto(
            id = 1,
            name = "John Doe",
            address = cn.tuyucheng.taketoday.mapstruct.dto.AddressDto(
                  streetAddress = "123 Main St",
                  zipCode = "12345"
            )
      )
      val user = userMapper.toBean(userDto)
      assertEquals(userDto.id, user.id)
      assertEquals(userDto.name, user.name)
      assertNotNull(user.createdAt)
      assertEquals(userDto.address.zipCode, user.address.zipCode)
      assertEquals("ACTIVE", user.status)
   }
}