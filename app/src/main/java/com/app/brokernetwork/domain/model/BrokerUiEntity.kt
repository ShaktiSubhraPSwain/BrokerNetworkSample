package com.app.brokernetwork.domain.model

data class BrokerUiEntity(val cards: List<Cards>)

data class Cards(
    val data: CardData
)

data class CardData(
    val totalCount: Int,
    val mainPost: CardInfo,
    val horizontalCards: List<CardInfo>,
)

data class CardInfo(
    val title: String?,
    val type: CardType?,
    val subInfo: List<SubInfo>?,
    val price: Int?,
    val matchCount: Int?,
    val rentExpected: Int?,
    val info: String?,
    val maxBudget: String?,
    val maxRent: String?,
    val assignedTo: AssignedTo?
)

data class CardType(val name: String, val id: String)

data class SubInfo(
    val text: String,
    val perfectMatch: Boolean
)

data class AssignedTo(
    val profilePicUrl: String,
    val phoneNumber: String,
    val orgName: String,
    val name: String
)