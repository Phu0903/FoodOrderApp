var mongoose = require('mongoose');
var user = require('../model/User')
var cart = new mongoose.Schema({
    _email:
     {
         type: String,
         required:true,
         unique:true,
         ref: 'users'
     },
     _ProductID:
    {
        type:String,
        required:true
    },
    _quantity:
    {
        type:String,
        required:true
    }
}, { collection: 'cart' });

module.exports = mongoose.model('cart', cart)