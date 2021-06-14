var mongoose = require('mongoose');
var orderList = new mongoose.Schema({
    _NameProduct: {
      type:String,

    },
    _Image:{
     type:String
    },
    _Quantity:
    {
        type: String,
    } ,
    _Price:{
      type:String,
     },   
     
}, { collection: 'orderlist' });

module.exports = mongoose.model('orderlist', orderList)