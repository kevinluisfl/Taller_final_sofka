/**
 * configuracion de conexion mongodb
 * @version 1.0.0 2022-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */
const mongoose = require('mongoose');
const { mongodb } = require('./keys');

mongoose
    .connect(mongodb.URI)
    .then((db) => console.log('Database is connected'))
    .catch((err) => console.log(err));
