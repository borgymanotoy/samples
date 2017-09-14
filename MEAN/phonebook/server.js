var express    = require('express'),
    mongoose   = require('mongoose'),
    bodyParser = require('body-parser'),
    __appRoot  = require('app-root-path'),
    Contact    = require('./api/models/contactModel'),
    app        = express(),
    port       = process.env.PORT || 8800;


// mongoose instance connection url connection
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/db_phonebook', {useMongoClient: true});


app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


var routes = require('./api/routes/phoneBookRoutes');
routes(app);

app.listen(port);

app.use(express.static('public'));

app.use(function(request, response) {
    response.status(404).send({url: request.originalUrl + ' not found'})
});


console.log('Phonebook RESTful API server started on: ' + port);