var express = require('express');
var router = express.Router();
var ProductFood = require('../model/ProductFood');
const { route } = require('./order');

//Xuất danh sách sản phẩm 
router.get('/getFood', async (req, res) => {
 try {
  ProductFood.find({}, function (err, dulieu) {
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

//Thêm sản phẩm
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

//Xem sản phẩm theo category
router.get('/:Category',async (req,res)=>{
  try{
    ProductFood.find({'_Category': req.params.Category},function(err,data)
    {
      // res.render('ViewProduct',{title:'Xem dữ liệu', products:data})
      res.status(201).json(data);
    })
  }catch (error) {
    res.status(500).json({
      success: false,
      message: err.message
  });
}
})


//Xem từng chi tiết sản phẩm theo ID
router.get('/:IDProduct',async (req,res)=>{
  try{
    ProductFood.find({'_ProductID': req.params.IDProduct},function(err,data)
    {
      // res.render('ViewProduct',{title:'Xem dữ liệu', products:data})
      res.status(201).json(data);
    })
  }catch (error) {
    res.status(500).json({
      success: false,
      message: err.message
  });
}
})


//sắp xếp theo số lượng còn trong kho.
router.get('/getSold/Sold', async (req, res) => {
  try {
    var mysort = { _Sold: 1 };
   ProductFood.find({}).sort(mysort).exec(function(err,docs){ //Sắp xếp
    if (err) throw err;
    res.json(docs);
  })
  } catch (err) {
    console.log(err);
   res.status(500).json({
     success: false,
     message: err.message
 });
  }
 })


 //
 router.get('/getPrice/Price', async (req, res) => {
  try {
    var mysort = { _Price: 1 };
   ProductFood.find({}).sort(mysort).exec(function(err,docs){ //Sắp xếp
    if (err) throw err;
    res.json(docs);
  })
  } catch (err) {
    console.log(err);
   res.status(500).json({
     success: false,
     message: err.message
 });
  }
 })
module.exports = router;
