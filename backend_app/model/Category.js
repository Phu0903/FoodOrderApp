var mongoose = require('mongoose');

var Category = new mongoose.Schema({
    _NameCategory:
     {
         type: String,
         required:true,
         unique:true,
        
     },
     _ImageCategory:
    {
        type:String,
        required:true
    },
}, { collection: 'Category' });

module.exports = mongoose.model('Category', Category)