package de.skycave.economy.repositories

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import de.skycave.economy.models.User
import java.math.BigDecimal
import java.util.UUID

class UserRepository(private val users: MongoCollection<User>) {

    fun hasAccount(uuid: UUID): Boolean {
        return users.countDocuments(Filters.eq("uuid", uuid)) > 0
    }

    fun getBalance(uuid: UUID): Double {
        val user = users.find(Filters.eq("uuid", uuid)).first() ?: return 0.0
        return user.balance.toDouble()
    }

    fun has(uuid: UUID, amount: Double): Boolean {
        val balance = getBalance(uuid)
        return balance >= amount
    }

    fun withdraw(uuid: UUID, amount: Double): Boolean {
        val bson = Filters.eq("uuid", uuid)
        val user = users.find(bson).first() ?: return false
        user.balance.rem(BigDecimal(amount))
        users.replaceOne(bson, user)
        return true
    }

    fun deposit(uuid: UUID, amount: Double) {
        val bson = Filters.eq("uuid", uuid)
        val user = users.find(bson).first()
        if (user == null) {
            users.insertOne(User(uuid, BigDecimal(amount)))
            return
        }
        user.balance.add(BigDecimal(amount))
        users.replaceOne(bson, user)
    }

}