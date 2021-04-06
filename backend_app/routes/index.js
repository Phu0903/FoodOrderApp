var express = require('express');
var router = express.Router();
var ProductFood = require('../model/ProductFood')

/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('index', { title: 'Express' });
});
//Lấy dữ liệu đồ ăn
// router.get('/getFood', function (req, res, next) {
//   ProductFood.find({}, function (err, dulieu) {
//     console.log(dulieu);
//     //res.json(dulieu);
//     res.render('ViewProduct', { title: 'xem dữ liệu', products: dulieu })
//   })
// });
//Xuất danh sách sản phẩm 
router.get('/getFood', async (req, res) => {
 try {
  ProductFood.find({}, function (err, dulieu) {
    //res.json(dulieu);
    res.render('ViewProduct', { title: 'xem dữ liệu', products: dulieu })
  })
 } catch (error) {
  res.status(500).json({
    success: false,
    message: err.message
});
 }
})


router.get('/AddProduct', function (req, res, next) {
  res.render('AddProduct', {})
});
router.post('/AddProduct', function (req, res, next) {


  var phantu = {
    '_ProductID': req.body._ProductID,
    '_NameProduct': req.body._NameProduct,
    '_Price': req.body._Price,
    '_Info': req.body._Info,
    '_Image': req.body._Image,
    '_Category': req.body._Category,
    '_Sold': req.body._Sold,

  }
  if (phantu === null) {
    res.send(null)
  }
  else {
    var dulieu = new ProductFood(phantu);
    dulieu.save();

    res.redirect('/AddProduct');
  }
});






module.exports = router;
