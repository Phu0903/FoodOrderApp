var mongoose = require('mongoose');
var users = new mongoose.Schema({
    _ProductID: 'string',
    _NameProduct: 'string',
    _Price: 'number',
    _Info: 'string',
    _Image: 'string',
    _Category: 'string',
    _Sold: 'number',
    _Favorite:'number'
}, { collection: 'ProductFood' });

module.exports = mongoose.model('ProductFood', users)