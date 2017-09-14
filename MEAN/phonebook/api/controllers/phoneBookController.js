'use strict';

var mongoose = require('mongoose'),
    appRoot  = require('app-root-path'),
    Contact  = mongoose.model('Contacts');


exports.home = function(request, response) {
    response.sendFile(appRoot + '/views/index.html');
};

exports.list_contacts = function(request, response) {
    Contact.find({}, function(err, contacts) {
        if (err) response.send(err);
        response.json(contacts);
    });
};

exports.get_contact = function(request, response) {
    Contact.findById(request.query.username, function(err, contact) {
      if (err) response.send(err);
        response.json(contact);
    });
};

exports.search_contact = function(request, response) {
    Contact.find({"$text":{"$search":request.query.searchKey}}, function(err, contacts) {
        if (err) response.send(err);
        response.json(contacts);
    });
};

exports.add_contact = function(request, response) {
    var nwContact = new Contact(request.body);
    var error = nwContact.validateSync();
    if(error){
        var str_error = error.message.replace(error._message + ": ","");
        console.log('[validation error]:' + str_error);
        response.send(error.errors);
    }
    else {
        nwContact.save(function(err, result) {
            if (err) response.send(err);
            console.log('result: ' + result)
            response.json({ message: 'Contact successfully added!' });
        });
    }
};

exports.update_contact = function(request, response) {
    console.log(request.body);
    if(request.body._id){
        Contact.findByIdAndUpdate(request.body._id, {$set: request.body}, function(err, result){
            if(err) response.send(err);
            console.log("RESULT: " + result);
            response.json({ message: 'Contact successfully updated!' });
        });
    }
    else
        response.json({ message: 'ID is Blank!' });
};

exports.remove_contact = function(request, response) {
    var _id = request.query.username;
    if(_id){
        Contact.findByIdAndRemove(_id, function(err, contact) {
            if (err) response.send(err);
            console.log("Contact: " + contact);
            response.json({ message: 'Contact successfully deleted!' });
        })
    }

};