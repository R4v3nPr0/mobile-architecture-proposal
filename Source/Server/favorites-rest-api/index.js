const express = require('express')
const favorites = require('./favorites/application/FavoritesController')

const app = express()
const port = 80

app.use(express.json())

app.use('/favorites', favorites)

app.listen(port)