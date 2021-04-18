var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/product');
var usersRouter = require('./routes/users');
var cartRouter =  require('./routes/cart');
var orderRouter =  require('./routes/order');

var mongoose = require('mongoose');


var app = express();
mongoose.connect('mongodb+srv://Orderfood:1@cluster0.bu9we.mongodb.net/dbFood?retryWrites=true&w=majority',
{  useNewUrlParser: true,
  useFindAndModify: false,
  useUnifiedTopology: true});
// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/indexRouter', indexRouter);
app.use('/cartRouter', cartRouter);
app.use('/usersRouter', usersRouter);
app.use('/orderRouter', orderRouter);


// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
