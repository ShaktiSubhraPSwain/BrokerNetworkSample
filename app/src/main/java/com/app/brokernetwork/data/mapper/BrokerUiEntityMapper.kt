package com.app.brokernetwork.data.mapper

import com.app.brokernetwork.data.model.*
import com.app.brokernetwork.domain.model.*

fun BrokerApiModel.toBrokerUiEntity(): BrokerUiEntity {
    return BrokerUiEntity(
        cards = cards.map { it.toCards() }
    )
}

fun CardsResponse.toCards(): Cards {
    return Cards(
        data = data.toCardData()
    )
}

fun CardDataResponse.toCardData(): CardData {
    return CardData(
        totalCount,
        mainPost.toCardInfo(),
        horizontalCards.map { it.toCardInfo() }
    )
}

fun CardInfoResponse.toCardInfo(): CardInfo {
    return CardInfo(
        title,
        type.toCardType(),
        subInfo.map { it.toSubInfo() },
        price,
        matchCount,
        rentExpected,
        info,
        maxBudget,
        maxRent,
        assignedTo.toAssignedTo()
    )
}

fun CardTypeResponse.toCardType(): CardType {
    return CardType(
        name, id
    )
}

fun SubInfoResponse.toSubInfo(): SubInfo {
    return SubInfo(
        text, perfectMatch
    )
}

fun AssignedToResponse.toAssignedTo(): AssignedTo {
    return AssignedTo(
        profilePicUrl, phoneNumber, orgName, name
    )
}