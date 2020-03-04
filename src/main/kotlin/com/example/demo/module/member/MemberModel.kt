package com.example.demo.module.member

import com.example.demo.data.Member
import tornadofx.ItemViewModel

class MemberModel(member: Member) : ItemViewModel<Member>(member) {
    val name = bind(Member::nameProperty)
    val surname = bind(Member::surnameProperty)
    val patronymicName = bind(Member::patronymicNameProperty)
    val email = bind(Member::emailProperty)
    val phone = bind(Member::phoneProperty)
    val snils = bind(Member::snilsProperty)
    val dateEntry = bind(Member::dateEntryProperty)
    val dateDeparture = bind(Member::dateDepartureProperty)
    val lpy = bind(Member::lpyProperty)
    var position = bind(Member::positionProperty)
    val yearlyFee = bind(Member::yearlyFeeProperty)
    val entranceFee = bind(Member::entranceFeeProperty)
    val participateEvents = bind(Member::participateEventsProperty)
    val note = bind(Member::noteProperty)
}