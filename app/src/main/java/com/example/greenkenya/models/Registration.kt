package com.example.greenkenya.models


class Registration {
    var name:String = ""
    var estate:String = ""
    var location:String = ""
    var phone:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(name: String, estate: String, location: String ,phone: String, imageUrl: String, id: String) {
        this.name = name
        this.estate = estate
        this.location = location
        this.phone = phone
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}