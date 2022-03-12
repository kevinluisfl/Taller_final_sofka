const mongoose = require('mongoose');
const bcrypt = require('bcrypt-nodejs');
const { Schema } = mongoose;

const userSchema = new Schema({
    email: String,
    password: String,
});

userSchema.methods.encryptPass = (password) => {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(5));
};

userSchema.methods.comparePass = function (password) {
    return bcrypt.compareSync(password, this.password);
};

module.exports = mongoose.model('users', userSchema);
