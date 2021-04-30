var express = require('express');
var CategoryRouter = express.Router();
var Category = require('../model/Category');


//Xuất danh sách sản phẩm 
CategoryRouter.get('/getCategory', async (req, res) => {
 try {
    Category.find({}, function (err, dulieu) {
    res.status(201).json(dulieu);
    //res.render('ViewProduct', { title: 'xem dữ liệu', products: dulieu })
  })
 } catch (error) {
  res.status(500).json({
    success: false,
    message: err.message
});
 }
})
module.exports = CategoryRouter;