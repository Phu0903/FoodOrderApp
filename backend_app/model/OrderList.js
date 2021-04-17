var mongoose = require('mongoose');
var orderList = new mongoose.Schema({
    _OrderID: {
      type:String,
      require:true,
    },
    _ProductID:{
        type:String,
        require:true,
    },
    _quantity:
    {
        type: String,
		default: Date.now
    } ,
}, { collection: 'orderlist' });

module.exports = mongoose.model('orderlist', orderList)