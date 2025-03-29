class UserRepository {
    fun getUser(id: Int): String {
        return if (id == 1) "Alice" else "Unknown"
    }
}

class UserService(private val userRepository: UserRepository) {
    fun getUserName(id: Int): String {
        return userRepository.getUser(id)
    }
}

fun main() {
    val userRepository = UserRepository()
    val userService = UserService(userRepository)
    println(userService.getUserName(1))
}