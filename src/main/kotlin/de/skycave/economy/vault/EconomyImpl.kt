package de.skycave.economy.vault

import de.skycave.economy.SkyCaveEconomy
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.text.DecimalFormat
import java.util.*

class EconomyImpl(private val main: SkyCaveEconomy): Economy {

    override fun isEnabled(): Boolean {
        return main.isEnabled
    }

    override fun getName(): String {
        return "SkyCaveEconomy"
    }

    override fun hasBankSupport(): Boolean {
        return true
    }

    override fun fractionalDigits(): Int {
        return 2
    }

    override fun format(amount: Double): String {
        return DecimalFormat("0.##").format(amount)
    }

    override fun currencyNamePlural(): String {
        return "Dollar"
    }

    override fun currencyNameSingular(): String {
        return "Dollar"
    }

    @Deprecated("Deprecated in Java")
    override fun hasAccount(playerName: String?): Boolean {
        playerName ?: return false
        val player = Bukkit.getOfflinePlayerIfCached(playerName) ?: return false
        return hasAccount(player)
    }

    override fun hasAccount(player: OfflinePlayer?): Boolean {
        player ?: return false
        return main.userRepository.hasAccount(player.uniqueId)
    }

    @Deprecated("Deprecated in Java")
    override fun hasAccount(playerName: String?, worldName: String?): Boolean {
        playerName ?: return false
        val player = Bukkit.getOfflinePlayerIfCached(playerName) ?: return false
        return hasAccount(player)
    }

    override fun hasAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        player ?: return false
        return hasAccount(player)
    }

    @Deprecated("Deprecated in Java")
    override fun getBalance(playerName: String?): Double {
        playerName ?: return 0.0
        val player = Bukkit.getOfflinePlayerIfCached(playerName) ?: return 0.0
        return getBalance(player)
    }

    override fun getBalance(player: OfflinePlayer?): Double {
        player ?: return 0.0
        return main.userRepository.getBalance(player.uniqueId)
    }

    @Deprecated("Deprecated in Java")
    override fun getBalance(playerName: String?, world: String?): Double {
        playerName ?: return 0.0
        val player = Bukkit.getOfflinePlayerIfCached(playerName) ?: return 0.0
        return getBalance(player)
    }

    override fun getBalance(player: OfflinePlayer?, world: String?): Double {
        player ?: return 0.0
        return getBalance(player)
    }

    @Deprecated("Deprecated in Java")
    override fun has(playerName: String?, amount: Double): Boolean {
        playerName ?: return false
        val player = Bukkit.getOfflinePlayerIfCached(playerName) ?: return false
        return has(player, amount)
    }

    override fun has(player: OfflinePlayer?, amount: Double): Boolean {
        player ?: return false
        return main.userRepository.has(player.uniqueId, amount)
    }

    @Deprecated("Deprecated in Java")
    override fun has(playerName: String?, worldName: String?, amount: Double): Boolean {
        playerName ?: return false
        val player = Bukkit.getOfflinePlayerIfCached(playerName) ?: return false
        return has(player, amount)
    }

    override fun has(player: OfflinePlayer?, worldName: String?, amount: Double): Boolean {
        player ?: return false
        return has(player, amount)
    }

    @Deprecated("Deprecated in Java")
    override fun withdrawPlayer(playerName: String?, amount: Double): EconomyResponse {
        playerName ?: return withdrawPlayer(player = null, amount)
        val player = Bukkit.getOfflinePlayerIfCached(playerName) ?: return withdrawPlayer(player = null, amount)
        return withdrawPlayer(player, amount)
    }

    override fun withdrawPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        player ?: return EconomyResponse(0.0, 0.0,
            EconomyResponse.ResponseType.FAILURE, "Player must not be null")
        if (!hasAccount(player)) return EconomyResponse(0.0, 0.0,
            EconomyResponse.ResponseType.FAILURE, "Player has no account")
        if (amount < 0) return EconomyResponse(0.0, 0.0,
            EconomyResponse.ResponseType.FAILURE, "Amount may not be negative")
        if (!has(player, amount)) return EconomyResponse(0.0, 0.0,
            EconomyResponse.ResponseType.FAILURE, "Not enough money")
        if (main.userRepository.withdraw(player.uniqueId, amount)) return EconomyResponse(amount, getBalance(player),
            EconomyResponse.ResponseType.SUCCESS, "")
        return EconomyResponse(amount, getBalance(player),
            EconomyResponse.ResponseType.FAILURE, "Unable to complete transaction")
    }

    @Deprecated("Deprecated in Java")
    override fun withdrawPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        playerName ?: return withdrawPlayer(player = null, amount)
        val player = Bukkit.getOfflinePlayerIfCached(playerName) ?: return withdrawPlayer(player = null, amount)
        return withdrawPlayer(player, amount)
    }

    override fun withdrawPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        player ?: return withdrawPlayer(player = null, amount)
        return withdrawPlayer(player, amount)
    }

    @Deprecated("Deprecated in Java")
    override fun depositPlayer(playerName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun depositPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun depositPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun depositPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun createBank(name: String?, player: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun createBank(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun deleteBank(name: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun bankBalance(name: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun bankHas(name: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun bankWithdraw(name: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun bankDeposit(name: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun isBankOwner(name: String?, playerName: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun isBankOwner(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun isBankMember(name: String?, playerName: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun isBankMember(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun getBanks(): MutableList<String> {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun createPlayerAccount(playerName: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun createPlayerAccount(player: OfflinePlayer?): Boolean {
        TODO("Not yet implemented")
    }

    @Deprecated("Deprecated in Java")
    override fun createPlayerAccount(playerName: String?, worldName: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun createPlayerAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        TODO("Not yet implemented")
    }

}