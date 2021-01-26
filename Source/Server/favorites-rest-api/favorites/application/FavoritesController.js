const express = require('express')
const mongoose = require('mongoose')
const router = express.Router()

const FavoritesSchema = new mongoose.Schema({
    accountNumber: String,
    accountType: Number,
    name: String
})

router.delete('/:id', function (request, response) {
    mongoose.connect('mongodb://127.0.0.1:27017/favorites')

    const connection = mongoose.connection

    const FavoritesModel = connection.model('Favorite', FavoritesSchema)

    FavoritesModel.deleteOne(
        {
            _id: request.params.id
        },
        function (error, result) {
            var r = {}

            r.success = result.ok ? true : false

            connection.close()

            console.log(r)

            response.json(r)
        }
    )
})

router.get('/', function (request, response) {
    mongoose.connect('mongodb://127.0.0.1:27017/favorites')

    const connection = mongoose.connection

    const FavoritesModel = connection.model('Favorite', FavoritesSchema)

    FavoritesModel.find(
        {},
        function (error, result) {
            var favorites = {
                favorites: []
            }

            for (index = 0; index < result.length; index++) {
                favorites.favorites.push({
                    id: result[index]._id,
                    accountNumber: result[index].accountNumber,
                    accountType: result[index].accountType,
                    name: result[index].name
                })
            }

            connection.close()

            console.log(favorites)

            response.json(favorites)
        }
    )
})

router.get('/:id', function (request, response) {
    mongoose.connect('mongodb://127.0.0.1:27017/favorites')

    const connection = mongoose.connection

    const FavoritesModel = connection.model('Favorite', FavoritesSchema)

    FavoritesModel.findById(
        request.params.id,
        function (error, result) {
            var favorite = {}

            favorite.id = result._id
            favorite.accountNumber = result.accountNumber
            favorite.accountType = result.accountType
            favorite.name = result.name

            connection.close()

            console.log(favorite)

            response.json(favorite)
        }
    )
})

router.post('/', function (request, response) {
    mongoose.connect('mongodb://127.0.0.1:27017/favorites')

    const connection = mongoose.connection

    const FavoritesModel = connection.model('Favorite', FavoritesSchema)

    var model = new FavoritesModel({
        accountNumber: request.body.accountNumber,
        accountType: request.body.accountType,
        name: request.body.name
    })

    model.save(function (error, result) {
        var r = {}

        r.favorite = {}
        r.favorite.id = result._id
        r.success = true

        connection.close()

        console.log(r)

        response.json(r)
    })
})

router.put('/:id', function (request, response) {
    mongoose.connect('mongodb://127.0.0.1:27017/favorites')

    const connection = mongoose.connection

    const FavoritesModel = connection.model('Favorite', FavoritesSchema)

    FavoritesModel.findById(
        request.params.id,
        function (error, result) {
            if (typeof request.body.accoundNumber !== 'undefined') {
                result.accountNumber = request.body.accountNumber
            }

            if (typeof request.body.accountType !== 'undefined') {
                result.accountType = request.body.accountType
            }

            if (typeof request.body.name !== 'undefined') {
                result.name = request.body.name
            }

            result.save(function (error, result) {
                var r = {}

                r.success = true

                connection.close()

                console.log(r)

                response.json(r)
            })
        }
    )
})

module.exports = router