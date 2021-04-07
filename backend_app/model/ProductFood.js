var mongoose = require('mongoose');
var users = new mongoose.Schema({
    _ProductID: 'string',
    _NameProduct: 'string',
    _Price: 'string',
    _Info: 'string',
    _Image: 'string',
    _Category: 'string',
    _Sold: 'number'
}, { collection: 'ProductFood' });

module.exports = mongoose.model('ProductFood', users)