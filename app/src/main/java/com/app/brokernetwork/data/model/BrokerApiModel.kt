package com.app.brokernetwork.data.model

import com.google.gson.annotations.SerializedName

data class BrokerApiModel(val cards: List<CardsResponse>)

data class CardsResponse (
    @SerializedName("data" ) val data : CardDataResponse
)

data class CardDataResponse(
    @SerializedName("total_matches_count")
    val totalCount: Int,

    @SerializedName("main_post")
    val mainPost: CardInfoResponse,

    @SerializedName("horizontal_cards")
    val horizontalCards: List<CardInfoResponse>,
)

data class CardInfoResponse(
    val title: String,
    val type: CardTypeResponse,
    @SerializedName("sub_info")
    val subInfo: List<SubInfoResponse>,
    val price: Int,
    @SerializedName("match_count")
    val matchCount: Int,
    @SerializedName("rent_expected")
    val rentExpected: Int,
    val info: String,
    @SerializedName("max_budget")
    val maxBudget: String,
    @SerializedName("max_rent")
    val maxRent: String?,
    @SerializedName("assigned_to")
    val assignedTo: AssignedToResponse
)

data class CardTypeResponse(val name: String, val id: String)

data class SubInfoResponse(
    val text: String,
    @SerializedName("perfect_match")
    val perfectMatch: Boolean
)

data class AssignedToResponse(
    @SerializedName("profile_pic_url")
    val profilePicUrl: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("org_name")
    val orgName: String,
    val name: String
)