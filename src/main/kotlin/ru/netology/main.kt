package ru.netology

import kotlin.math.roundToInt

enum class TypeOfCard {
    MasterCard, Maestro, Mir, Visa, VKPay
}

const val MAX_MASTER_MAESTRO = 7_500_000
const val COMMISSION_MASTER_MAESTRO = 0.006
const val ADD_COMMISSION_MASTER_MAESTRO = 20_000
const val COMMISSION_MIR_VISA = 0.0075
const val MIN_COMMISSION_MIR_VISA = 3_500

fun main() {
    printCommission(100_000, TypeOfCard.MasterCard, 2_500_000)
    printCommission(200_000, TypeOfCard.Maestro, 7_300_500)
    printCommission(150_000, TypeOfCard.Visa, 500_000)
    printCommission(1_000_000, TypeOfCard.Mir, 700_000)
    printCommission(800_000, TypeOfCard.VKPay, 0)
}

fun commissionCalculation(
    typeOfCard: TypeOfCard = TypeOfCard.VKPay,
    amountOfPreviousTransfers: Int = 0,
    transferAmount: Int
): Int {
    return when (typeOfCard) {
        TypeOfCard.MasterCard, TypeOfCard.Maestro ->
            if (amountOfPreviousTransfers + transferAmount < MAX_MASTER_MAESTRO) {
                0
            } else {
                (transferAmount * COMMISSION_MASTER_MAESTRO +
                        ADD_COMMISSION_MASTER_MAESTRO).roundToInt()
            }
        TypeOfCard.Visa, TypeOfCard.Mir ->
            if (transferAmount * COMMISSION_MIR_VISA < MIN_COMMISSION_MIR_VISA) {
                MIN_COMMISSION_MIR_VISA
            } else {
                (transferAmount * COMMISSION_MIR_VISA).roundToInt()
            }
        else -> 0
    }
}

fun printCommission(
    transferAmount: Int,
    typeOfCard: TypeOfCard,
    amountOfPreviousTransfers: Int,
) {
    println(
        "За перевод ${transferAmount / 100} рублей с карты $typeOfCard комиссия составит " +
                "${
                    commissionCalculation(
                        typeOfCard, amountOfPreviousTransfers,
                        transferAmount
                    ) / 100
                } рублей"
    )
}
