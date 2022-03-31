/**
 * configuracion de schema usuario
 * @version 1.0.0 2022-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */
const mongoose = require('mongoose');
const bcrypt = require('bcrypt-nodejs');
const { Schema } = mongoose;
/**
 * Schema para modelar usuario
 */
const userSchema = new Schema({
    email: String,
    password: String,
});

/**
 * metodo para encriptar password
 * @param {*} password 
 * @returns hash encriptado
 */
userSchema.methods.encryptPass = (password) => {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(5));
};

/**
 * metodo para comparar el password al intentar iniciar sesión
 * @param {*} password 
 * @returns 
 */
userSchema.methods.comparePass = function (password) {
    return bcrypt.compareSync(password, this.password);
};

module.exports = mongoose.model('users', userSchema);
