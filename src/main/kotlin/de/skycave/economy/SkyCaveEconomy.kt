package de.skycave.economy

import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import de.skycave.economy.models.Bank
import de.skycave.economy.models.User
import de.skycave.economy.repositories.UserRepository
import de.skycave.skycavelib.annotations.Prefix
import de.skycave.skycavelib.models.SkyCavePlugin
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry

@Prefix("&5Bank &8» ")
class SkyCaveEconomy: SkyCavePlugin() {

    lateinit var userRepository: UserRepository
        private set

    private lateinit var users: MongoCollection<User>
    private lateinit var banks: MongoCollection<Bank>
    private lateinit var mongoClient: MongoClient

    override fun onEnable() {
        super.onEnable()

        // database
        val codecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
            CodecRegistries.fromCodecs(),
            CodecRegistries.fromProviders(),
            MongoClientSettings.getDefaultCodecRegistry()
        )
        val clientSettings = MongoClientSettings.builder().codecRegistry(codecRegistry).build()
        mongoClient = MongoClients.create(clientSettings)
        val db = mongoClient.getDatabase("sc_economy")
        users = db.getCollection("users", User::class.java)
        userRepository = UserRepository(users)
        banks = db.getCollection("banks", Bank::class.java)
    }

    override fun onDisable() {
        super.onDisable()
        mongoClient.close()
    }

}