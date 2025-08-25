require('dotenv').config();
const mongoose = require('mongoose');
mongoose.connect(process.env.MONGO_URI)

const userSchema = mongoose.Schema({
    email: {type: String, required: true },
    password: {type: String, required: true}
});

module.exports = mongoose.model("user", userSchema);