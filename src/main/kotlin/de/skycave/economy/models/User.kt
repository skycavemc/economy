package de.skycave.economy.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.math.BigDecimal
import java.util.UUID

class User {

    @BsonId lateinit var id: ObjectId
    lateinit var uuid: UUID
    lateinit var balance: BigDecimal

    constructor()

    constructor(uuid: UUID, balance: BigDecimal) {
        this.uuid = uuid
        this.balance = balance
    }


}