var mongoose = require('mongoose');
const OrderList = require('./OrderList').schema;
const Schema = mongoose.Schema;
var order = new mongoose.Schema({
    _OrderID: {
      type:String,
      require:true,
    },
    _email:{
        type:String,
        require:true,
    },
    _createDay:
    {
        type: Date,
	    	default: Date.now
    } ,
    _total: {
        type:String,
        require:true,
    },
    _address:{
      type:String,
      require:true
    },
    _phonenumber:{
         type:String,
         require:true,
    },
    _status: {
        type:String,
        require:true,
    },
    _product:{
        type:[OrderList]
    },
    _name:{
        type:String
    }
}, { collection: 'order' });

module.exports = mongoose.model('order', order)