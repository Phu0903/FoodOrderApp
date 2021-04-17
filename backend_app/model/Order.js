var mongoose = require('mongoose');
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
        type:Number,
        require:true,
    },
    _status: {
        type:String,
        require:true,
    },
}, { collection: 'order' });

module.exports = mongoose.model('order', order)