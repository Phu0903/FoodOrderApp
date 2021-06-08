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
    _Image:{
        type:String,
    },
    _NameProduct:{
           type:String,
    },
    _Price:{
        type:String,
    },    
    _quantity:
    {
        type:String,
        required:true
    }
}, { collection: 'cart' });

module.exports = mongoose.model('cart', cart)