package com.example.greenkenya.models

class Account {

    var date: String = ""
    var name:String = ""
    var location:String = ""
    var estate:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(name: String, location: String, estate: String,imageUrl: String, id: String, date: String) {
        this.name = name
        this.location = location
        this.estate= estate
        this.imageUrl = imageUrl
        this.id = id
        this.date = date
    }

    constructor()

}